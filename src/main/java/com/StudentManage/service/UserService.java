package com.StudentManage.service;

import com.StudentManage.pojo.User;
import com.StudentManage.utils.CommonResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {

    Boolean register(User user);

    CommonResult<?> login(User user);

    CommonResult<?> changePassword(User user);
}
