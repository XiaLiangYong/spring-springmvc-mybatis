package com.spring.controller;

import com.spring.common.Fun;
import com.spring.domain.Car;
import com.spring.domain.CollectionBean;
import com.spring.domain.Employee;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * BeanHelloController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
public class BeanHelloControllerTest {

    ClassPathXmlApplicationContext applicationContext = null;

    @Before
    public void before() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:bean.xml");
        this.applicationContext = applicationContext;
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void employee() throws IOException {

        Employee employee = (Employee) applicationContext.getBean("employee");
        System.out.println("employee" + Fun.objectToJson(employee));
    }

    @Test
    public void car() throws IOException {

        Car car = (Car) applicationContext.getBean("car");
        System.out.println("car=" + Fun.objectToJson(car));
    }

    @Test
    public void employee2() throws IOException {
        Employee employee = (Employee) applicationContext.getBean("employee2");
        System.out.println("employee2=" + Fun.objectToJson(employee));
    }

    @Test
    public void employee3() throws IOException {
        Employee employee = (Employee) applicationContext.getBean("employee3");
        System.out.println("employee3=" + Fun.objectToJson(employee));
    }

    @Test
    public void collectionBean() throws IOException {
        CollectionBean collectionBean = (CollectionBean) applicationContext.getBean("collectionBean");
        System.out.println("collectionBean=" + Fun.objectToJson(collectionBean));
    }


}
