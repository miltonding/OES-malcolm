package com.malcolm.oes.advice;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import com.malcolm.oes.AppContext;

public class LogMethodTimeAspect {

    private Logger log = Logger.getLogger(LogMethodTimeAspect.class);

    public void doBefore(JoinPoint jp) {
        log.info("Log Begining method : " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        // The method is executed.
        Object returnValue = pjp.proceed();
        String methodName = pjp.getSignature().getName();
        long endTime = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        sb.append(((HttpServletRequest) AppContext.getContext().getObject("APP_CONTEXT_REQUEST")).getRemoteAddr());
        sb.append(":");
        sb.append(AppContext.getContext().getUserName());
        sb.append(":");
        sb.append(pjp.getTarget().getClass().getSimpleName());
        sb.append(":");
        sb.append(methodName);
        sb.append(":");
        sb.append(endTime - startTime);
        log.info(sb.toString());
        return returnValue;
    }

    public void doAfter(JoinPoint jp) {
        log.info("Log ending method : " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    public void doThrowing(JoinPoint jp, Throwable ex) {
        log.error("method" + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName()
                + "throw exception");
        log.error(ex.getMessage());
    }
}
