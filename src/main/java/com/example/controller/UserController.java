package com.example.controller;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title:
 * @auther: raohr
 * @Date: 2020/9/30 16:20
 * @param:
 * @Description:
 * @return:
 * @throws:
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


}
