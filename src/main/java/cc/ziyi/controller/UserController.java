package cc.ziyi.controller;

import cc.ziyi.pojo.Result;
import cc.ziyi.pojo.User;
import cc.ziyi.service.UserService;
import cc.ziyi.utils.JwtUtil;
import cc.ziyi.utils.Md5Util;
import cc.ziyi.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;  // need to call methods from service-layer

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @PostMapping("/register")  // path: /user/register;  method: POST; request sample: username=alice&password=123456
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){

        // find users (with Service-method)
        User u = userService.findByUserName(username);
        if( u == null ){
            // username is available, can be registered
            userService.register(username,password);
            return Result.success();
        } else {
            // username is unavailable
            return Result.error("This username has been taken!");
        }

    }

    @PostMapping("/login")   // path:/user/login;  method: POST; request sample: username=alice&password=123456
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){

        // 1. find users according to the username user input (with Service-method)
        User loginUser = userService.findByUserName(username);

        // 2. check whether the user exists
        if(loginUser == null) {
            return Result.error("Wrong username");
        }

        // 3. Check whether the password the user input is correct
        // Compare the encrypted password with the password stored in the DB for the user
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            // successfully login
            Map<String,Object> claims = new HashMap<>(); // create claims-map for holding "id" and "username"
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);  // set claims as param in genToken() for generating a token

            // store token into redis
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

            // key:token; value:token; timeout: must be same as the configuration in JwtUtil
            operations.set(token,token,1, TimeUnit.HOURS);
            return Result.success(token);  // type of data-field in the response is jwt-string
        }
        return Result.error("Wrong password");
    }

    @GetMapping("/userInfo")  // path:/user/userInfo;  method: GET; no params
    public Result<User> userInfo( /*@RequestHeader(name = "Authorization") String token*/ ) {

//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");

        // introduce ThreadLocal for reducing parameters passed & sharing data in the same thread
        Map<String, Object> map = ThreadLocalUtil.get(); // claims is a Map in ThreadLocalUtil.set(claims)
        String username = (String) map.get("username");

        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")  // path:/user/update;  method: PUT ;  params: application/json
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    // PatchMapping : Update one part of resources
    @PatchMapping("updateAvatar")  // path:/user/updateAvatar;  method:PATCH; param: avatarUrl
    public Result updateAvatar(@RequestParam @URL String avatarUrl) { // @URL ensure param is an legal URL
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")  // /user/updatePwd;  method:PATCH; param: application/json
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token){
        // 1. Check parameters manually (Validation cannot do this)
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        // Make sure all parameters are successfully passed in
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("Missing required parameters");
        }

        // check whether oldPwd matches the original password registered in the DB
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username"); // get logged-in user's username from ThreadLocal
        User loginUser = userService.findByUserName(username); // get current user info through userService by userName
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){  // then compare it with encrypted old_pwd
            return Result.error("The original password is incorrect!");
        }

        // check if newPwd and rePwd are the same
        if (!rePwd.equals(newPwd)){
            return Result.error("The password is different from the new password you just set");
        }

        // 2. Call service to complete password update
        userService.updatePwd(newPwd);

        // get token from the value of RequestHeader"Authorization"-field
        // delete the current token in Redis when pwd updated
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();
    }

}
