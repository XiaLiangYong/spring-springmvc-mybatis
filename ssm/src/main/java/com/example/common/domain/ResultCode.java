package com.example.common.domain;

/**
 * Created by alibeibei on 2017/8/15.
 */
public enum ResultCode {
    COMMON_SUCCESS("J000000", "成功"),
    COMMON_BUSINESS_EXCEPTION("J000997", "业务异常"),
    COMMON_SYSTEM_EXCEPTION("J000998", "系统异常"),
    COMMON_SYSTEM_ERROR("J000999", "系统错误");

    private String code;
    private String description;

    private ResultCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}
