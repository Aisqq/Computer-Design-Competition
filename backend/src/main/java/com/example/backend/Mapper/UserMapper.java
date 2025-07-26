package com.example.backend.Mapper;

//这个包存放api、数据库访问方法供调用

//通过ibaits的select方法来注解写sql语句并返回数据

//@Select("select * from User")静态，动态用xml文件

import com.example.backend.Controller.request.LoginRequest;
import com.example.backend.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper         //找不到bean，记得加注解
public interface UserMapper { //查询数据
    List<User> listUsers();     //通过此方法返回数据

    User getByusernameAndPassword(LoginRequest loginRequest);
}
