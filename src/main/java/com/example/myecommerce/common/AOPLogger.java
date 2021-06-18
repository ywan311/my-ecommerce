package com.example.myecommerce.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;


@Slf4j
@Aspect
@Component
public class AOPLogger {

    @Pointcut("within(com.example.myecommerce.Web.apiController..*)")
    public void onApiRequest(){
        //api controller 들어오는 모든요청
    }

    @Around("com.example.myecommerce.common.AOPLogger.onApiRequest()")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable{
        HttpServletRequest servletRequest = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        Map<String,String[]> paramMap = servletRequest.getParameterMap();

        long start = System.currentTimeMillis();
        try {
            return pjp.proceed(pjp.getArgs()); // 6
        } finally {
            long end = System.currentTimeMillis();
            log.info("Request : {} ,{} , {} , ({} ms) ,Remote : {} , {}",servletRequest.getRequestURI(), servletRequest.getMethod(),paramMapString(paramMap),end-start,servletRequest.getRemoteHost(),servletRequest.getHeader("User-Agent"));
        }
    }

    private String paramMapString(Map<String,String[]> paramMap){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(String key : paramMap.keySet()){
            builder.append(key);
            builder.append(":");
            builder.append(Arrays.toString(paramMap.get(key)));
        }
        builder.append("]");
        return builder.toString();
    }
}
