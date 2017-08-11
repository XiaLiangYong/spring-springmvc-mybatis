package com.spring.controller;

import com.spring.service.HelloService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * HelloController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
public class HelloControllerTest {
    ClassPathXmlApplicationContext applicationContext = null;

    @Before
    public void before() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:bean.xml");
        this.applicationContext = applicationContext;
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: index()
     */
    @Test
    public void register1() throws Exception {
        System.out.println("testIndex");
        HelloService helloService = new HelloService();
        System.out.println(helloService.getName("register1"));
    }

    //Bean的作用域
//    最常用 singleton 和 prototype 两种
//    Singleton （单例）: 在一个BeanFactory对象中，引用唯一的一个目标实例
//    Prototype （多例）: 每次通过工厂执行getBean时，返回不同实例对象
//    Request （请求范围） : 创建对象保存在request范围，如果request销毁，对象销毁
//    Session （会话范围）： 创建对象保存Session中， 如果session销毁，对象销毁
//* globalSession （全局会话 ） ：分布式系统，全局会话的概念， 一次登录，应用多个系统
    @Test
    public void register2() {
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        System.out.println(helloService.getName("register2"));
        HelloService helloService1 = (HelloService) applicationContext.getBean("helloService");
        System.out.println(helloService1.getName("register2"));
    }


    @Test
    public void register3() {
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        System.out.println(helloService.getName("register3"));
        System.out.println(helloService.toString());
        HelloService helloService1 = (HelloService) applicationContext.getBean("helloService");
        System.out.println(helloService1.toString());
        System.out.println(helloService1.getName("register3"));
    }
} 
