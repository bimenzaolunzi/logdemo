package com.itheima.logdemo.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ⽇日志切⾯面
 */
@Aspect
@Component
public class LogAspect {
    private final static Logger logger = LoggerFactory.getLogger("kafka");

    @Pointcut("@annotation(com.itheima.logdemo.utils.LogInfo)")
    public void log() {
    }

    /**
     * 环绕通知
     */
    @Around(value = "log()")
    public Object arround(ProceedingJoinPoint pjp) {
        try {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            String className = pjp.getTarget().getClass().getSimpleName();
            String methodName = signature.getName();
            LogBean logBean = LogBean.logBeanThreadLocal.get();
            logBean.setMessage("before " + className + "." + methodName);
            logger.info(logBean.toString());
            //方法执⾏行行
            Object o = pjp.proceed();
            logBean.setMessage("after " + className + "." + methodName);
            logger.info(logBean.toString());
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
