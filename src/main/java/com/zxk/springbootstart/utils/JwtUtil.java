package com.zxk.springbootstart.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private static final String KEY = "MCSY";
    private final RedisTemplate<String, String> redisTemplate;
    @Autowired //
    public JwtUtil(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256(KEY));
    }
    public Map<String,Object> parseToken(String token, StringRedisTemplate redisTemplate) {
        if (redisTemplate != null && redisTemplate.hasKey("blacklist:" + token)) {
            throw new RuntimeException("Token 已注销");
        }
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
    public void addToBlacklist(String token) {
        Date expiry = getExpiryDate(token);
        long ttl = expiry.getTime() - System.currentTimeMillis();
        if (ttl > 0) {
            redisTemplate.opsForValue().set(
                    "blacklist:" + token,
                    "1",
                    ttl,
                    TimeUnit.MILLISECONDS
            );
        }
    }
    private Date getExpiryDate(String token) {
        return JWT.decode(token).getExpiresAt();
    }
}
