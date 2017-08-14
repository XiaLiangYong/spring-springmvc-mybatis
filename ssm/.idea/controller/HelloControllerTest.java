package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"spring-mybatis.xml"})
public class HelloControllerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Resource
    private UserService userService;

    @Test
    public void test() {
        User userInfo = new User();
        userInfo.setUsername("alibeibei");
        userInfo.setPassword("19910222");
        userService.insert(userInfo);
        System.out.println("test");
    }


} 
