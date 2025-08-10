package com.zxk.springbootstart.mapper;

import com.zxk.springbootstart.pojo.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AnnouncementMapper {
    @Select("select * from announcements")
    Announcement[] GetAll();
    @Update("UPDATE announcements SET like_count = like_count + #{increment} WHERE id = #{id}")
    void incrementCount(Integer id, Long increment);
}
