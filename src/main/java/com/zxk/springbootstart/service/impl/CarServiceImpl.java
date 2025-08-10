package com.zxk.springbootstart.service.impl;

import com.zxk.springbootstart.mapper.CarMapper;
import com.zxk.springbootstart.pojo.Car;
import com.zxk.springbootstart.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;
    @Override
    public Car[] getAllcar(){
        return carMapper.getAllCar();
    }
    public Integer updateCar(String cheNum){return  carMapper.updateCar(cheNum);}
}
