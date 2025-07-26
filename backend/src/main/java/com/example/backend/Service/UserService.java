package com.example.backend.Service;
//Service一般提供接口和实现方法来实现操作
//接口提供对应mapper里的方法，返回给mapper层

import com.example.backend.Controller.dto.Logindto;
import com.example.backend.Controller.request.LoginRequest;
import com.example.backend.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<User> ListUsers();

    Logindto login(LoginRequest loginRequest);
}
