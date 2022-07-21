package net.sparkminds.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
    
    private final RedisTemplate<String, Object> redisTemplate;
    
    public void cacheJwt(String jwt) {
        redisTemplate.opsForValue().set( jwt , jwt);
    }

}
