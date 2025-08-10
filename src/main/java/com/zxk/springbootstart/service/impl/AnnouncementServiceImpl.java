package com.zxk.springbootstart.service.impl;

import com.zxk.springbootstart.mapper.AnnouncementMapper;
import com.zxk.springbootstart.pojo.Announcement;
import com.zxk.springbootstart.pojo.Result;
import com.zxk.springbootstart.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementMapper announcementMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();

    private static final String REDIS_LIKE_KEY_PREFIX = "announcement:likes:";

    @Override
    public Long updateRedis(Integer id, Long increment) {
        String key = REDIS_LIKE_KEY_PREFIX + id;
        return ((Number) Objects.requireNonNull(redisTemplate.opsForValue().increment(key, increment))).longValue();
    }

    @Override
    public void increment(Integer id, Long increment) {
        virtualThreadExecutor.execute(() -> {
            try {
                String key = REDIS_LIKE_KEY_PREFIX + id;
                Object value = redisTemplate.opsForValue().get(key);
                long pendingCount = value != null ? ((Number) value).longValue() : 0L;

                if (pendingCount > 0) {
                    announcementMapper.incrementCount(id, pendingCount);
                    redisTemplate.opsForValue().decrement(key, pendingCount);
                }
            } catch (Exception e) {
                log.error("Failed to sync likes for announcement {}", id, e);
            }
        });
    }

    @Override
    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void scheduledSyncLikes() {
        try {
            Set<String> keys = redisTemplate.keys(REDIS_LIKE_KEY_PREFIX + "*");
            keys.forEach(key -> {
                try {
                    Object value = redisTemplate.opsForValue().get(key);
                    long pendingCount = value != null ? ((Number) value).longValue() : 0L;

                    if (pendingCount > 0) {
                        Integer id = Integer.parseInt(key.substring(REDIS_LIKE_KEY_PREFIX.length()));
                        announcementMapper.incrementCount(id, pendingCount);
                        redisTemplate.opsForValue().set(key, 0L);
                    }
                } catch (Exception e) {
                    log.error("Failed to process key {}", key, e);
                }
            });
        } catch (Exception e) {
            log.error("Scheduled sync failed", e);
        }
    }

    @Override
    public Announcement[] GetAll() {
        return announcementMapper.GetAll();
    }
}