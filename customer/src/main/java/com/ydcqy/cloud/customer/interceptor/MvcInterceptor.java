package com.ydcqy.cloud.customer.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by lenovo on 2018/1/22.
 */
@Slf4j
@Component
public class MvcInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String scheme = request.getScheme();
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String contextPath = request.getContextPath();
        String authType = request.getAuthType();
        Cookie[] cookies = request.getCookies();
        Enumeration<String> headerNames = request.getHeaderNames();
        String method = request.getMethod();
        String pathInfo = request.getPathInfo();
        String pathTranslated = request.getPathTranslated();
        String queryString = request.getQueryString();
        String remoteUser = request.getRemoteUser();
        String requestedSessionId = request.getRequestedSessionId();
        String servletPath = request.getServletPath();
        String characterEncoding = request.getCharacterEncoding();
        Enumeration<String> attributeNames = request.getAttributeNames();
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("----拦截请求----");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
