package com.example.backend.Service.Impl;

import com.example.backend.Controller.request.CarPageRequest;
import com.example.backend.Entity.Car;
import com.example.backend.Mapper.CarMapper;
import com.example.backend.Service.CarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarMapper carMapper;

    @Override
    public List<Car> SelectCars() {
        return carMapper.SelectCars();
    }

    @Override
    public Object page(CarPageRequest carPageRequest) {
        PageHelper.startPage(carPageRequest.getPageNum(), carPageRequest.getPageSize());
        List<Car> cars = carMapper.listByCondition(carPageRequest);
        return new PageInfo<>(cars);
    }

    @Override
    public void deleteCar(String id) {
        carMapper.deleteCar(id);
    }

    @Override
    public List<Integer> SelectcarStatus(){
        carMapper.SelectcarStatus();
        return carMapper.SelectcarStatus();
    }
    @Override
    public void updateCar(Car car) {
        carMapper.updateCar(car);
    }

    @Override
    public void addCar(Car car) {
        carMapper.addCar(car);
    }
}
