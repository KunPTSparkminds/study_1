package net.sparkminds.service.implement;

import java.util.Date;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.config.JwtTokenUtil;
import net.sparkminds.service.LogoutService;
import net.sparkminds.service.RedisService;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LogoutServiceImplement implements LogoutService {
    
    private final RedisService redisService;
    
    private final RedisTemplate<String, Object> template;
    
    private final JwtTokenUtil jwtTokenUtil;
    @Override
    public String logout(String jwt) {
        
        Long timeout = jwtTokenUtil.getExpirationDateFromToken(jwt).getTime() - new Date().getTime();
        
        redisService.cacheJwt(jwt, timeout);
        
        return "Success Logout";
    }

    @Override
    public boolean checkJwtExistedRedis(String jwt) {
        return template.opsForValue().get(jwt) != null;
    }
    
    

}
