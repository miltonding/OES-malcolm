package com.malcolm.oes.advice;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.malcolm.oes.AppContext;
import com.malcolm.oes.service.impl.UserServiceImpl;

public class LogMethodTime implements MethodInterceptor {

    private Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        // The method is executed.
        Object returnValue = invocation.proceed();
        String methodName = invocation.getMethod().getName();
        long endTime = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        sb.append(((HttpServletRequest) AppContext.getContext().getObject("APP_CONTEXT_REQUEST")).getRemoteAddr());
        sb.append(":");
        sb.append(AppContext.getContext().getUserName());
        sb.append(":");
        sb.append(invocation.getMethod().getDeclaringClass().getSimpleName());
        sb.append(":");
        sb.append(methodName);
        sb.append(":");
        sb.append(endTime - startTime);
        logger.info(sb.toString());
        return returnValue;
    }
}
