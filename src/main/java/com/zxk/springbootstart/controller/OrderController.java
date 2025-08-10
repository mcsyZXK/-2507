package com.zxk.springbootstart.controller;

import com.zxk.springbootstart.anno.NeedLogin;
import com.zxk.springbootstart.mapper.CarMapper;
import com.zxk.springbootstart.pojo.Order;
import com.zxk.springbootstart.pojo.Result;
import com.zxk.springbootstart.pojo.User;
import com.zxk.springbootstart.service.CarService;
import com.zxk.springbootstart.service.OrderService;
import com.zxk.springbootstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;

    @NeedLogin
    @PostMapping("/getByID")
    public Result OrderList(@RequestBody Map<String, Object> requestBody) {
        Integer userId =(Integer) requestBody.get("userId");
        User user = userService.findById(userId);
        if(user.getRole() == User.Role.ADMIN){
            return Result.success(orderService.getAllOrder());
        } else {
            return Result.success(orderService.getOrderById(user.getId()));
        }
    }
    @NeedLogin
    @PostMapping("/update")
    public Result UpdateOrder(Integer orderId,String orderStatus) {
        Integer num = orderService.updateOrder(orderId,orderStatus);
        if(orderStatus.equals("APPROVED")){
            Order order = orderService.getOrderByOrderId(orderId);
            String cheNum = order.getCheNum();
            carService.updateCar(cheNum);
        }
        return Result.success(num);
    }
    @NeedLogin
    @PostMapping("/zl")
    public Result zlCar(@RequestBody Map<String, Object> params) {
        String cheNum=(String) params.get("cheNum");
        Integer userId=(Integer) params.get("userId");
        Order order = new Order();
        order.setCheNum(cheNum);
        order.setUserId(userId);
        order.setStatus(Order.Status.PENDING);
        Integer num = orderService.InsertOrder(order);
        carService.updateCar(cheNum);
        return Result.success(num);
    }
    @NeedLogin
    @PostMapping("/reset")
    public Result reSetOrder(@RequestBody Map<String,Object>requestBody) {
        Integer orderId=(Integer) requestBody.get("orderId");
        return Result.success(orderService.reset(orderId));
    }
}
