package net.sparkminds.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
    
    private final RedisTemplate<String, Object> redisTemplate;
    
    public void cacheJwt(String jwt, Long timeout) {
        redisTemplate.opsForValue().set( jwt , jwt, timeout, TimeUnit.MILLISECONDS);
    }

}
