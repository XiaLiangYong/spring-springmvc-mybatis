package com.example.common.handler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.common.domain.Result;
import com.example.common.domain.ResultCode;
import com.example.common.exception.BusinessException;
import com.example.common.exception.SystemException;
import com.example.common.util.http.URLUtil;
import com.example.common.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonExceptionHandler implements HandlerExceptionResolver {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String ERROR_VIEW = "error";
    private static final String EXPREFIX = "Failed to check the status of the service";
    private static final String EXPREFIX1 = "Failed to invoke the method";
    private static final String exClassName = "com.rrkd.se.common.exception.BusinessException";

    public CommonExceptionHandler() {
    }

    private String rpcCommonExcetion(String exp) {
        String className;
        Pattern p;
        Matcher m;
        String application;
        if (StringUtil.isNotEmpty(exp) && exp.contains("Failed to check the status of the service")) {
            p = Pattern.compile("application=(.*?)&(.*?)interface=(.*?)&");
            m = p.matcher(exp);
            application = null;
            className = null;
            if (m.find() && m.groupCount() == 3) {
                application = m.group(1).trim();
                className = m.group(3).trim();
            }

            exp = "访问RPC服务：（" + application + "：" + className + "）不可用";
        } else if (StringUtil.isNotEmpty(exp) && exp.contains("Failed to invoke the method")) {
            p = Pattern.compile("method:(.*?),(.*?)application=(.*?)&(.*?)interface=(.*?)&");
            m = p.matcher(exp);
            application = null;
            className = null;
            String methodName = "";
            if (m.find() && m.groupCount() == 5) {
                methodName = m.group(1).trim();
                application = m.group(4).trim();
                className = m.group(5).trim();
            }

            exp = "访问RPC服务：（" + application + "：" + className + "#" + methodName + "超时";
        } else if (StringUtil.isNotEmpty(exp) && exp.contains("com.rrkd.se.common.exception.BusinessException")) {
            String regex = "com.rrkd.se.common.exception.BusinessException:(.*?)\ncom.rrkd.se.common.exception.BusinessException:";
            p = Pattern.compile(regex);
            m = p.matcher(exp);
            if (m.find()) {
                exp = m.group(1).trim();
            }
        }

        return exp;
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Result result = null;
        if (BusinessException.class.isAssignableFrom(ex.getClass())) {
            BusinessException bex = (BusinessException) ex;
            result = new Result(ResultCode.COMMON_BUSINESS_EXCEPTION, false);
            result.setDescription(this.rpcCommonExcetion(bex.getMessage()));
            result.setResultMap(bex.getResultMap());
        } else if (SystemException.class.isAssignableFrom(ex.getClass())) {
            SystemException sex = (SystemException) ex;
            result = new Result(ResultCode.COMMON_SYSTEM_EXCEPTION, false);
            result.setDescription(sex.getMessage());
            this.log.error("CommonExceptionHandler catche the System Exception, ", ex);
        } else if (RuntimeException.class.isAssignableFrom(ex.getClass()) && StringUtil.isNotEmpty(ex.getMessage()) && ex.getMessage().contains("com.rrkd.se.common.exception.BusinessException")) {
            RuntimeException bex = (RuntimeException) ex;
            result = new Result(ResultCode.COMMON_BUSINESS_EXCEPTION, false);
            result.setDescription(this.rpcCommonExcetion(bex.getMessage()));
        } else {
            result = new Result(ResultCode.COMMON_SYSTEM_ERROR, false);
            result.setDescription(ex.getMessage());
            this.log.error("CommonExceptionHandler catche the System Error, ", ex);
        }

        String requestHeader = request.getHeader("Accept");
        response.setCharacterEncoding("UTF-8");
        if (requestHeader != null && !URLUtil.isAjaxUrl(request)) {
            response.setContentType("text/html;charset=UTF-8");
            request.setAttribute("code", result.getCode());
            request.setAttribute("description", result.getDescription());
            return new ModelAndView("error");
        } else {
            try {
                response.setContentType("application/json;charset=UTF-8");
                StringBuffer responseSb = new StringBuffer();
                if (URLUtil.isJsonp(request)) {
                    String callback = request.getParameter("callback");
                    responseSb.append("(").append(callback).append(this.toJSONString(result)).append(")");
                } else {
                    responseSb.append(this.toJSONString(result));
                }

                response.getWriter().println(responseSb.toString());
            } catch (Exception var9) {
                this.log.error("Response write exception", var9);
            }

            return new ModelAndView();
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

    public static void main(String[] args) {
        String cont = "Failed to invoke the method queryCommonInviteInfo in the service com.rrkd.commuser.rpc.OtherCommonUserQueryRpcService. Tried 1 times of the providers [172.16.20.244:20882] (1/1) from the registry 172.16.60.16:2181 on the consumer 192.168.56.1 using the dubbo version 2.4.9. Last error is: Invoke remote method timeout. method: queryCommonInviteInfo, provider: dubbo://172.16.20.244:20882/com.rrkd.commuser.rpc.OtherCommonUserQueryRpcService?application=RrkdCourierService&check=false&dubbo=2.4.9&interface=com.rrkd.commuser.rpc.OtherCommonUserQueryRpcService&methods=queryUserPenalized,queryCommonInviteInfo&pid=7104&retries=0&revision=1.1.30&side=consumer&timeout=3000&timestamp=1469258257566&version=1.0.1, cause: Waiting server-side response timeout. start time: 2016-07-23 15:35:08.873, end time: 2016-07-23 15:35:11.875, client elapsed: 0 ms, server elapsed: 3002 ms, timeout: 3000 ms, request: Request [id=225, version=2.0.0, twoway=true, event=false, broken=false, data=RpcInvocation [methodName=queryCommonInviteInfo, parameterTypes=[class java.lang.String, class java.lang.Short], arguments=[13880799327, 1], attachments={path=com.rrkd.commuser.rpc.OtherCommonUserQueryRpcService, interface=com.rrkd.commuser.rpc.OtherCommonUserQueryRpcService, timeout=3000, version=1.0.1}]], channel: /172.16.20.244:58525 -> /172.16.20.244:20882";
        Pattern p = Pattern.compile("method:(.*?),(.*?)application=(.*?)&(.*?)interface=(.*?)&");
        Matcher m = p.matcher(cont);
        if (m.find() && m.groupCount() == 5) {
            System.out.println(m.groupCount());
            System.out.println(m.group(0).trim());
            System.out.println(m.group(1).trim());
            System.out.println(m.group(2).trim());
            System.out.println(m.group(3).trim());
            System.out.println(m.group(4).trim());
            System.out.println(m.group(5).trim());
        }

    }
}
