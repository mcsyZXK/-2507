package com.zxk.springbootstart.mapper;

import com.zxk.springbootstart.pojo.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface ReplyMapper {
    @Select("select * from replies")
    Reply[] getAllReply();
    @Insert("insert into replies(anid,retext,nickname) values(#{anId},#{retext},#{nickname})")
    Integer insertReply(Reply reply);
}
