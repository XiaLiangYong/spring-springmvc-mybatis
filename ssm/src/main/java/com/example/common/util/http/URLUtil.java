package com.example.common.util.http;

import com.example.common.exception.SystemException;
import com.example.common.util.string.StringUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class URLUtil {
    public URLUtil() {
    }

    public static final String getURIWithoutSuffix(String uri) {
        if (uri != null && !"".equals(uri.trim())) {
            int pointIndex = uri.indexOf(".");
            return pointIndex == -1 ? uri : uri.substring(0, pointIndex);
        } else {
            return uri;
        }
    }

    public static final String getURIWithoutSuffix(String url, String contextPath) {
        if (url != null && !"".equals(url.trim())) {
            int contextPathPonit = url.indexOf(contextPath);
            if (contextPathPonit != -1) {
                url = url.substring(contextPathPonit);
            }

            int pointIndex = url.indexOf(".");
            return pointIndex == -1 ? url : url.substring(0, pointIndex);
        } else {
            return url;
        }
    }

    public static final String getURLWithoutContextPath(String uri, HttpServletRequest request) {
        if (uri != null && !"".equals(uri.trim())) {
            String contextPath = request.getContextPath();
            if (contextPath != null && !"".equals(contextPath.trim())) {
                if (!uri.startsWith(contextPath)) {
                    throw new SystemException("url does not contain contextPath !");
                } else {
                    return uri.substring(contextPath.length());
                }
            } else {
                return uri;
            }
        } else {
            return uri;
        }
    }

    public static final boolean isAjaxUrl(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int suffixIndex = uri.lastIndexOf(".");
        String format;
        if (suffixIndex != -1) {
            format = uri.substring(suffixIndex + 1);
            if ("json".equals(format) || "jsonp".equals(format)) {
                return true;
            }
        }

        format = request.getParameter("format");
        if (!"json".equals(format) && !"jsonp".equals(format)) {
            String accept = request.getHeader("Accept");
            if (StringUtil.isEmpty(accept) || !accept.contains("application/json") && !accept.contains("application/jsonp")) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        String accept = "";
        if (!StringUtil.isEmpty(accept) && (accept.contains("application/json") || accept.contains("application/jsonp"))) {
            System.out.println();
        }

    }

    public static final boolean isJsonp(HttpServletRequest request) {
        String format = request.getParameter("format");
        if ("jsonp".equals(format)) {
            return true;
        } else {
            String accept = request.getHeader("Accept");
            return !StringUtil.isEmpty(accept) && accept.contains("application/jsonp");
        }
    }

    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.length() != 0) {
            if (ip != null && ip.indexOf(",") != -1) {
                ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
            }

            return String.valueOf(ip);
        } else {
            ip = request.getHeader("X-Real-IP");
            if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            } else {
                ip = request.getHeader("X-Forwarded-For");
                if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                    int index = ip.indexOf(44);
                    return index != -1 ? ip.substring(0, index) : ip;
                } else {
                    return request.getRemoteAddr();
                }
            }
        }
    }
}

