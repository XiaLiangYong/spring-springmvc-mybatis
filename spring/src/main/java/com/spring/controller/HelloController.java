package com.spring.controller;

import com.spring.service.HelloService;

/**
 * Created by alibeibei on 2017/8/10.
 */
public class HelloController {
    public void index() {
        HelloService helloService = new HelloService();
        System.out.println(helloService.getName("world"));
    }
}
