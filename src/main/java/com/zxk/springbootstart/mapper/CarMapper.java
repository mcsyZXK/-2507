package com.zxk.springbootstart.mapper;

import com.zxk.springbootstart.pojo.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CarMapper {
    @Select("select * from che ")
    Car[] getAllCar();
    @Update("UPDATE che SET is_zl = 1 WHERE che_num =#{cpNum}")
    Integer updateCar(String cheNum);
}
