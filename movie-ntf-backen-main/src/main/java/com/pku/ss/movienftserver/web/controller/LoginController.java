/**
 * @(#)LoginController.java, 8月 12, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.web.controller;

import com.pku.ss.movienftserver.annotation.PassToken;
import com.pku.ss.movienftserver.annotation.UserLoginToken;
import com.pku.ss.movienftserver.constant.MovieNftServerConstant;
import com.pku.ss.movienftserver.data.enums.UserType;
import com.pku.ss.movienftserver.web.logic.LoginLogic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyan
 */
@Slf4j
@RestController
@RequestMapping(MovieNftServerConstant.API)
@Api(value = "用户登陆注册API" , tags = "")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginLogic loginLogic;

    @PassToken
    @GetMapping("/login")
    @ApiOperation("用户登陆接口")
    public String login(String account, String password , int userType){
        return loginLogic.login(account, password, UserType.findByInt(userType).orElse(null));
    }

    @PassToken
    @PostMapping("/register")
    @ApiOperation("用户注册接口")
    public int register(String account, String userName, String password, int userType){
        return loginLogic.register(account,userName,password,UserType.findByInt(userType).orElse(null));
    }

    @UserLoginToken
    @GetMapping("/test-token")
    @ApiOperation("测试Token接口")
    public String testToken(){
        return "OJBK";
    }
}