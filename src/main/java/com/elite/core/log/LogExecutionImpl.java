package com.elite.core.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LogExecutionImpl {

    @Before("@annotation(LogExecution)")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String parameters = Arrays.stream(args).sequential().map(Object::toString).collect(Collectors.joining());
        log.info("Elite start method execution: {}.{} with parameters: {}", className, methodName, parameters);
    }

    @After("@annotation(LogExecution)")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("Elite end method execution: {}.{}", className, methodName);
    }
}
