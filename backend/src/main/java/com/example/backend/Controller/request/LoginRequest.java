package com.example.backend.Controller.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String userPwd;
}
