package com.StudentManage.service.Impl;

import com.StudentManage.config.ContextHolder;
import com.StudentManage.mapper.UserMapper;
import com.StudentManage.pojo.User;
import com.StudentManage.service.UserService;
import com.StudentManage.utils.BeanCopyUtils;
import com.StudentManage.utils.CommonResult;
import com.StudentManage.utils.JwtUtil;
import com.StudentManage.vo.LoginVo;
import com.StudentManage.vo.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean register(User user) {
        //条件构造器
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //查询输入的用户名和表中用户名相同的用户数据
        queryWrapper.eq(User::getUsername, user.getUsername());
        List<User> selectedList = userMapper.selectList(queryWrapper);
        if (!selectedList.isEmpty()) {
            throw new RuntimeException("注册失败，该用户名已存在");
        }
        // 密码加密
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return save(user);
    }

    @Override
    public CommonResult<?> login(User user) {
        //条件构造器
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //查询输入的用户名和表中用户名相同的用户数据
        queryWrapper.eq(User::getUsername, user.getUsername());
        List<User> selectedList = userMapper.selectList(queryWrapper);
        if (selectedList.isEmpty()) {
            throw new RuntimeException("登录失败，账号不存在");
        }
        User selected = selectedList.get(0);
        String encodedPassword = selected.getPassword();
        // 判断密码是否正确
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean result = passwordEncoder.matches(user.getPassword(), encodedPassword);
        if (!result) {
            throw new RuntimeException("登录失败，用户密码错误");
        }
        // 生成令牌
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("userId", selected.getId());
        String token = JwtUtil.generateToken(map);
        //Bean拷贝,返回值字段优化
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(selected, UserInfoVo.class);
        LoginVo loginVo = new LoginVo(token, userInfoVo);
        return CommonResult.okResult(loginVo);
    }

    @Override
    public CommonResult<?> changePassword(User user) {
        // 密码加密
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        //修改完的用户信息
        User updateUserInfo = new User();
        updateUserInfo.setPassword(encodedPassword);
        // 从上下文对象里面获取用户id，而不是用户传过来的
        Long userId = ContextHolder.getUserId();
        updateUserInfo.setId(userId);
        return CommonResult.okResult(updateById(updateUserInfo));
    }

}
