package com.zxk.springbootstart.controller;

import com.zxk.springbootstart.pojo.Car;
import com.zxk.springbootstart.pojo.Result;
import com.zxk.springbootstart.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {
    @Autowired
    private CarService carService;
    @RequestMapping("/getAll")
    public Result getAllcars(){return Result.success(carService.getAllcar());}
}
