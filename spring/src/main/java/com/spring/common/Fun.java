package com.spring.common;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by alibeibei on 2017/8/11.
 */
public class Fun {
    /**
     * 对象转json
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public static String objectToJson(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        return json;
    }
}
