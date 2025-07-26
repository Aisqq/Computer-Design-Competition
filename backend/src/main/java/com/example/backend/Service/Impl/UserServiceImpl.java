package com.example.backend.Service.Impl;

//实现接口的方法
//可以用@Autowire导入UserMapper,并且用@override重写方法

import com.example.backend.Exception.ServiceException;
import com.example.backend.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import com.example.backend.Controller.dto.Logindto;
import com.example.backend.Controller.request.LoginRequest;
import com.example.backend.Entity.User;
import com.example.backend.Mapper.UserMapper;
import com.example.backend.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Slf4j
@Service//标注为spring boot的组件，方便调用
public class UserServiceImpl implements UserService {

    @Autowired              //调用Mapper查出数据
    UserMapper userMapper;

    @Override
    public List<User> ListUsers() {         //实现方法impl，并通过他返回出去给controller接口用
        return userMapper.listUsers();      //可能报错用try catch
    }

    @Override
    public Logindto login(LoginRequest loginRequest) {
        User user=userMapper.getByusernameAndPassword(loginRequest);
        if(user==null){
            throw new ServiceException("用户名或密码错误");
        }
        Logindto logindto=new Logindto();   //有一个问题，如果没有数据返回空，仍然显示状态为成功
        BeanUtils.copyProperties(user,logindto);//将user属性赋值给logindto，只复制名字同的属性
        return logindto;
    }
}
