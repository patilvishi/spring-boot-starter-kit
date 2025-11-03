package com.example.starterkit.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.starterkit.controller..*(..)) || execution(* com.example.starterkit.service..*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("Entering: {} with args {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* com.example.starterkit.controller..*(..)) || execution(* com.example.starterkit.service..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Completed: {} with result {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "execution(* com.example.starterkit..*(..))", throwing = "exception")
    public void logAfterException(JoinPoint joinPoint, Exception exception) {
        log.error("Exception in {} -> {}", joinPoint.getSignature(), exception.getMessage());
    }
}
