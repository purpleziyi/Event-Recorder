package cc.ziyi.interceptors;

import cc.ziyi.utils.JwtUtil;
import cc.ziyi.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component  // Inject the current interceptor-object into the IoC container
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override  // Intercept within this method
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // verify token
        // get token through the request-object(Header)
        String token = request.getHeader("Authorization");

        // parse token (the logic is the same as the code in ArticleController
        try {
            // get the same token from Redis
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if(redisToken == null){  // check whether the redisToken exist
                // when token has expired, system throws exception, then been caught by catch-method
                throw new RuntimeException();
            }

            /** parsing token, get business data */
            Map<String, Object> claims = JwtUtil.parseToken(token);  // login-method create claims-Map for holding userInfo(id and username)

            /** store the business data into ThreadLocal */
            ThreadLocalUtil.set(claims);

            // allow the data pass
            return true;
        } catch (Exception e) {
            // exception in logging, HTTP-status code = 401
            response.setStatus(401);
            // intercept the data
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // clear the data in the ThreadLocal
        ThreadLocalUtil.remove();
    }
}
