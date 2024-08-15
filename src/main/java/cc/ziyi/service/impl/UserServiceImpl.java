package cc.ziyi.service.impl;

import cc.ziyi.mapper.UserMapper;
import cc.ziyi.pojo.User;
import cc.ziyi.service.UserService;
import cc.ziyi.utils.Md5Util;
import cc.ziyi.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service  // business logic
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;  // need to call methods from mapper-layer


    @Override
    public User findByUserName(String username) {
        // call method in Mapper-layer with SQL and return User-object
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        // Encryption processing -- MD5
        // Put the plain text password as argument and return an encrypted string
        String md5String = Md5Util.getMD5String(password);

        // add new username and encrypted string
        userMapper.add(username,md5String);

    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now()); // update the updateTime-attribute
        userMapper.update(user);  // call method in Mapper
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();  // get user-info through ThreadLocal
        Integer id = (Integer) map.get("id");   // find userId from the use-info
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        // must update password based on userId
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id); // encrypt the newPwd with MD5
    }
}
