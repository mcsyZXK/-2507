package com.zxk.springbootstart.mapper;

import com.zxk.springbootstart.pojo.Order;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO `order`(user_id, che_num, order_id, request_date, status) " +
            "VALUES(#{userId}, #{cheNum}, #{orderId}, #{requestDate}, #{status})")
    Integer insert(Order order);
    @Select("select * from `order` where user_id = #{userId}")
    Order[] getByID(Integer userId);
    @Select("select * from `order`")
    Order[] getAll();
    @Update("UPDATE `order` SET status = #{status} WHERE order_id = #{orderId}")
    Integer update(Integer orderId, String status);
    @Select("SELECT * from `order`where order_id = #{orderId}")
    Order getOrderByID(Integer orderId);
    @Update("UPDATE `order` SET status = 'CANCELLED' WHERE order_id =#{orderId}")
    Integer reset(Integer orderId);
}
