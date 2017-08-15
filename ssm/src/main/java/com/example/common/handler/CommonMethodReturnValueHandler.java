package com.example.common.handler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.http.HttpServletResponse;

import com.example.common.domain.Result;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CommonMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    public CommonMethodReturnValueHandler() {
    }

    public boolean supportsReturnType(MethodParameter returnType) {
        return Result.class.isAssignableFrom(returnType.getParameterType());
    }

    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        if (returnValue != null) {
            Result result = (Result) returnValue;
            HttpServletResponse response = (HttpServletResponse) webRequest.getNativeResponse(HttpServletResponse.class);
            response.setCharacterEncoding("UTF-8");
            if (this.isAjaxUrl(webRequest)) {
                response.setContentType("application/json;charset=UTF-8");
            } else {
                response.setContentType("text/html;charset=UTF-8");
            }

            StringBuffer responseSb = new StringBuffer();
            if (this.isJsonp(webRequest)) {
                String callback = webRequest.getParameter("callback");
                responseSb.append("(").append(callback).append(this.toJSONString(result)).append(")");
            } else {
                responseSb.append(this.toJSONString(result));
            }

            response.getWriter().println(responseSb.toString());
            mavContainer.setRequestHandled(true);
        }

    }

    private boolean isAjaxUrl(NativeWebRequest webRequest) {
        String format = webRequest.getParameter("format");
        if (!"json".equals(format) && !"jsonp".equals(format)) {
            String accept = webRequest.getHeader("Accept");
            return accept.contains("application/json") || accept.contains("application/jsonp");
        } else {
            return true;
        }
    }

    private boolean isJsonp(NativeWebRequest webRequest) {
        String format = webRequest.getParameter("format");
        if ("jsonp".equals(format)) {
            return true;
        } else {
            String accept = webRequest.getHeader("Accept");
            return accept.contains("application/jsonp");
        }
    }

    private String toJSONString(Result result) {
        if (result.isUseDateFormat()) {
            JSON.DEFFAULT_DATE_FORMAT = result.getDateFormat();
            return JSON.toJSONString(result, new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat});
        } else {
            return JSON.toJSONString(result);
        }
    }
}

