package com.example.common.exception;

import java.util.HashMap;
import java.util.Map;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -2000433217414115838L;

    private Map<String, Object> resultMap = new HashMap();

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message, Map<String, Object> resultMap) {
        super(message);
        this.resultMap = resultMap;
    }

    public BusinessException(String message, Map<String, Object> resultMap, Throwable cause) {
        super(message, cause);
        this.resultMap = resultMap;
    }

    public Map<String, Object> getResultMap() {
        return this.resultMap;
    }
}

