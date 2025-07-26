package com.example.backend.Controller;


import com.example.backend.Controller.dto.Logindto;
import com.example.backend.Controller.request.LoginRequest;
import com.example.backend.Entity.User;
import com.example.backend.Service.UserService;
import org.apache.ibatis.annotations.Mapper;
import com.example.backend.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//标识，以json格式返回数据，同样用@Autowired声明Service接口
//@getMapping返回给前端
//浏览器要配置插件才能显示json数据

@CrossOrigin
@RestController//标明该类的格式
@RequestMapping("/user")
public class UserController {       //以json格式返回传给浏览器，对外暴露api
    @Autowired      //注入服务接口，让他调用服务类
    UserService userService;

    @RequestMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest) {
       Logindto logindto = userService.login(loginRequest);
        return Result.success(logindto);
    }
}
