package com.example.server.controller;

import com.example.server.pojo.Bo.UserLoginParam;
import com.example.server.pojo.RespBean;
import com.example.server.service.IUsersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    private IUsersService usersService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request){
        return usersService.login(userLoginParam.getUsername(),userLoginParam.getPassword(),userLoginParam.getCode(),userLoginParam.getIp(),request);
    }

}
