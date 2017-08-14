package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by alibeibei on 2017/8/14.
 */
@Controller
@RequestMapping("hello")
public class HelloController {
    @RequestMapping("index")
    @ResponseBody
    public String index() {
        return "hello world";
    }
}
