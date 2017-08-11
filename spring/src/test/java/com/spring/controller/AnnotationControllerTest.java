package com.spring.controller;

import com.spring.Application;
import com.spring.MessageService;
import com.spring.dao.TestDao;
import com.spring.service.TestService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * AnnotationController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
@Configuration
@ComponentScan
public class AnnotationControllerTest {

    protected ApplicationContext context;

    @Before
    public void before() throws Exception {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationControllerTest.class);
        this.context = context;
    }

    @After
    public void after() throws Exception {


    }

    @Bean
    TestDao testDao() {
        return new TestDao() {
            public String hello() {
                return "nihao";
            }
        };
    }

    @Test
    public void test() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(TestService.class);
        TestService testService = context.getBean(TestService.class);
        System.out.println(testService.hello());
    }


} 
