package net.sparkminds.service.implement;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.service.LogoutService;
import net.sparkminds.service.RedisService;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LogoutServiceImplement implements LogoutService {
    
    private final RedisService redisService;
    
    private final RedisTemplate<String, Object> template;
    @Override
    public String logout(String jwt) {
        
        redisService.cacheJwt(jwt);
        
        return "Logout Success";
    }

    @Override
    public boolean checkJwtExistedRedis(String jwt) {
        
        if(template.opsForValue().get(jwt) == null) {
            return false;
        }
        return true;
    }
    
    

}
