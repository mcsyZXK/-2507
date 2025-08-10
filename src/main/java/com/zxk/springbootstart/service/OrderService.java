package com.zxk.springbootstart.service;

import com.zxk.springbootstart.pojo.Order;

public interface OrderService {
    Order[] getOrderById(Integer id);
    Order[] getAllOrder();
    Integer updateOrder(Integer orderId,String status);
    Order getOrderByOrderId(Integer orderId);
    Integer InsertOrder(Order order);
    Integer reset(Integer orderId);
}
