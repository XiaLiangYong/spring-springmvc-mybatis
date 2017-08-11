package com.spring.service;

import com.spring.dao.TestDao;


/**
 * Created by alibeibei on 2017/8/11.
 */
public class TestService {

    private TestDao testDao;

    public String hello() {

        return "hello";
    }
}
