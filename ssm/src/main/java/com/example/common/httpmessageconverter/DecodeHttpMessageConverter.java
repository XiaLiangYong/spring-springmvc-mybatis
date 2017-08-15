package com.example.common.httpmessageconverter;


import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.common.exception.BusinessException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("decodeHttpMessageConverter")
public class DecodeHttpMessageConverter extends FastJsonHttpMessageConverter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) {
        logger.error("test");
        try {
            String content = IOUtils.toString(inputMessage.getBody(), "UTF-8");

            String username = inputMessage.getHeaders().getFirst("USERNAME");
            String token = inputMessage.getHeaders().getFirst("TOKEN");
            String timestamp = inputMessage.getHeaders().getFirst("TIMESTAMP");
            System.out.println("username=" + username);
            return inputMessage;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("没有权限");
        }
    }
}


