package com.example.backend.Exception;

//服务层处理异常,告诉前端是业务层异常了
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
