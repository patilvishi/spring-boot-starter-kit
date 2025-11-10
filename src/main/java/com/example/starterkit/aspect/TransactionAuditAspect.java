package com.example.starterkit.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TransactionAuditAspect {

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeTransaction(JoinPoint joinPoint) {
        log.info("[TX-BEGIN] Transaction started for method: {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void afterCommit(JoinPoint joinPoint) {
        log.info("[TX-COMMIT] Transaction committed for method: {}", joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(pointcut = "@annotation(org.springframework.transaction.annotation.Transactional)", throwing = "exception")
    public void afterRollback(JoinPoint joinPoint, Throwable exception) {
        log.error("[TX-ROLLBACK] Transaction rolled back in method: {} due to: {}", 
                  joinPoint.getSignature().toShortString(), exception.getMessage());
    }
}
