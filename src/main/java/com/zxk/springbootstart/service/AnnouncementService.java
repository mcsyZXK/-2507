package com.zxk.springbootstart.service;

import com.zxk.springbootstart.pojo.Announcement;
import com.zxk.springbootstart.pojo.Result;

public interface AnnouncementService {
    Announcement[] GetAll();
    void increment(Integer id,Long increment);
    Long updateRedis(Integer id, Long increment);
    void scheduledSyncLikes();
}
