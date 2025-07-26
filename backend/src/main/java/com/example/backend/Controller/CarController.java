package com.example.backend.Controller;

import com.example.backend.Controller.request.CarPageRequest;
import com.example.backend.Entity.Car;
import com.example.backend.Service.CarService;
import com.example.backend.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin            //解决跨域问题
@RestController
@RequestMapping("/Car")
public class CarController {

    @Autowired
    CarService carService;

    @DeleteMapping("/delete/{id}")
    public Result deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result updateCar(@RequestBody Car car){
        carService.updateCar(car);
        return Result.success();
    }

    @PostMapping("/add")//增加用户的前端接口
    public Result addCar(@RequestBody Car car) {//将json转为实体对象
        carService.addCar(car);
        return Result.success();    //告诉前端接受到了请求，请求成功
    }

    @GetMapping("/List")
    public Result SelectCars(){
        List<Car> cars = carService.SelectCars();
        return Result.success(cars);
    }
    @GetMapping("/carStatus")
    public Result SelectcarStatus(){
        List<Integer> carStatus = carService.SelectcarStatus();
        return Result.success(carStatus);
    }
    @GetMapping("/Page")
    public Result Page(CarPageRequest carPageRequest) {
        return Result.success(carService.page(carPageRequest));
    }
}
