package com.StudentManage.controller;

import com.StudentManage.pojo.User;
import com.StudentManage.service.UserService;
import com.StudentManage.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public CommonResult<?> register(@RequestBody User user) {
        return CommonResult.okResult(userService.register(user));
    }

    @PostMapping("/login")
    public CommonResult<?> login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/changePassword")
    public CommonResult<?> changePassword(@RequestBody User user) {
        return userService.changePassword(user);
    }

}
