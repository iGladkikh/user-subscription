package com.igladkikh.usersubscription.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Класс предназначен для централизованного логирования.
 * Целевые методы для логирования должны иметь аннотацию
 * {@link com.igladkikh.usersubscription.annotation.LogMethodArgs @LogMethodArgs}
 */

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("@annotation(com.igladkikh.usersubscription.annotation.LogMethodArgs)")
    static void loggableMethodArgs() {
    }

    @Before("loggableMethodArgs()")
    public void debugLoggingMethodArgs(JoinPoint point) {
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        log.debug("Executing method: {}() with args: {}", methodName, Arrays.toString(args));
    }
}