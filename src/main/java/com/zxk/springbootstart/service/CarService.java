package com.zxk.springbootstart.service;

import com.zxk.springbootstart.pojo.Car;

public interface CarService {
    Car[] getAllcar();
    Integer updateCar(String cheNum);
}
