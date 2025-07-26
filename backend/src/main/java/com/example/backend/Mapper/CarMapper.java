package com.example.backend.Mapper;

import com.example.backend.Controller.request.CarPageRequest;
import com.example.backend.Entity.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {
    List<Car> SelectCars();
    List<Car> listByCondition(CarPageRequest carPageRequest);
    List<Integer> SelectcarStatus();
    void deleteCar(String id);

    void updateCar(Car car);

    void addCar(Car car);
}
