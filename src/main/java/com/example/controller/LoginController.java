package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.commons.ResultInfo;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @Title:
 * @auther: raohr
 * @Date: 2020/10/9 10:47
 * @param:
 * @Description:
 * @return:
 * @throws:
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    private UserMapper userMapper;

    @PostMapping("login")
    public ResultInfo login(String name, String password){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        User user = userMapper.selectOne(queryWrapper);
        if (user!=null){
            if (user.getPassword().equals(password)){
                return ResultInfo.success("登录成功");
            }
        }
        return ResultInfo.sysError("登录失败");
    }

}
