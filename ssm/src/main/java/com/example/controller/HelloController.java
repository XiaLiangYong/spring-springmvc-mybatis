package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by alibeibei on 2017/8/14.
 */
@Controller
@RequestMapping("hello")
public class HelloController {
    @Resource
    private UserService userService;

    @RequestMapping("index")
    @ResponseBody
    public String index() {
        User user = new User();
        user.setUsername("夏天");
        user.setPassword("100112");
        userService.insert(user);
        return "hello world";
    }
}
