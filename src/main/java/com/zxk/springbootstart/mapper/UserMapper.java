package com.zxk.springbootstart.mapper;

import com.zxk.springbootstart.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//sql语句编写
@Mapper
public interface UserMapper {
    @Select("select * from user where email = #{email}")
    User findByEmail(String email);
    @Select("select * from user where id = #{id}")
    User findById(Integer id);
    @Select("SELECT EXISTS(SELECT 1 FROM user WHERE username = #{username} OR email = #{email})")
    boolean userExist(String username, String email);
    @Select("select * from user where username =#{username}")
    User findByUsername(String username);
    @Insert("insert into user(username,password,email)" +
            "values(#{username},#{password},#{email})")
    void register(String username,String password,String email);
    @Update("update user set nickname=#{nickname},email=#{email} where id=#{id}")
    void update(User user);
    @Update("update user set picture_url=#{pictureUrl} where id =#{id}")
    void updatePicture(String pictureUrl,Integer id);
    @Update("update user set password=#{hashPassword} where id =#{id}")
    void updatePassword(String hashPassword);
}
