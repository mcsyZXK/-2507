package com.zxk.springbootstart.service.impl;

import com.zxk.springbootstart.mapper.CarMapper;
import com.zxk.springbootstart.mapper.OrderMapper;
import com.zxk.springbootstart.pojo.Order;
import com.zxk.springbootstart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CarMapper carMapper;
    @Override
    public Order[] getOrderById(Integer id) {
        return orderMapper.getByID(id);
    }
    @Override
    public Order[] getAllOrder() {
        return orderMapper.getAll();
    }

    @Override
    public Integer updateOrder(Integer orderId, String status) {
        return orderMapper.update(orderId, status);
    }

    @Override
    public Order getOrderByOrderId(Integer orderId) {
        return orderMapper.getOrderByID(orderId);
    }

    @Override
    public Integer InsertOrder(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public Integer reset(Integer orderId) {
        return orderMapper.reset(orderId);
    }
}
