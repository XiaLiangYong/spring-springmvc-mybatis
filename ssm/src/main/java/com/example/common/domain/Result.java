package com.example.common.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.common.dataaccess.mysql.PageQuery;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {
    private static final long serialVersionUID = 1998428067166208629L;
    private String code;
    private String description;
    private boolean isSuccess;
    private Map<String, Object> resultMap = new HashMap();
    @JSONField(
            serialize = false
    )
    private boolean useDateFormat = false;
    @JSONField(
            serialize = false
    )
    private String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public Result() {
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.description = resultCode.getDescription();
    }

    public Result(ResultCode resultCode, boolean isSuccess) {
        this.code = resultCode.getCode();
        this.description = resultCode.getDescription();
        this.isSuccess = isSuccess;
    }

    public void setPage(Integer size, Collection<?> e) {
        this.setProperty("iTotalDisplayRecords", size);
        this.setProperty("rows", e);
    }

    public void setPage(Integer size, Collection<?> e, PageQuery page) {
        Boolean first = Boolean.valueOf(false);
        Boolean end = Boolean.valueOf(false);
        if (size.intValue() != 0) {
            Integer pageNo = Integer.valueOf(page.getPageNo());
            Integer pageSize = Integer.valueOf(page.getPageSize());
            Integer pageSum = Integer.valueOf(size.intValue() / pageSize.intValue());
            Integer y = Integer.valueOf(size.intValue() % pageSize.intValue());
            if (y.intValue() != 0) {
                pageSum = Integer.valueOf(pageSum.intValue() + 1);
            }

            if (pageNo.intValue() > 1) {
                first = Boolean.valueOf(true);
            }

            if (pageNo.intValue() < pageSum.intValue()) {
                end = Boolean.valueOf(true);
            }
        }

        this.setProperty("isFirst", first);
        this.setProperty("isEnd", end);
        this.setProperty("rows", e);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getResultMap() {
        return this.resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isUseDateFormat() {
        return this.useDateFormat;
    }

    public void setUseDateFormat(boolean useDateFormat) {
        this.useDateFormat = useDateFormat;
    }

    public Object getProperty(String propertyName) {
        return this.resultMap.get(propertyName);
    }

    public Object setProperty(String propertyName, Object propertyValue) {
        return this.resultMap.put(propertyName, propertyValue);
    }

    public void removeProperty(String propertyName) {
        this.resultMap.remove(propertyName);
    }

    public void removeAllProperty() {
        this.resultMap.clear();
    }

    public void setCode(ResultCode resultCode) {
        this.setCode(resultCode.getCode());
        this.setDescription(resultCode.getDescription());
    }

    public String getDateFormat() {
        return this.dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String toString() {
        return "Result [code=" + this.code + ", description=" + this.description + ", isSuccess=" + this.isSuccess + ", resultMap=" + this.resultMap + ", useDateFormat=" + this.useDateFormat + ", dateFormat=" + this.dateFormat + "]";
    }
}

