package com.example.starterkit.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* com.example.starterkit.controller..*(..)) || execution(* com.example.starterkit.service..*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            long elapsed = System.currentTimeMillis() - start;
            log.info("[{}] executed in {} ms", joinPoint.getSignature(), elapsed);
        }
        return result;
    }
}
