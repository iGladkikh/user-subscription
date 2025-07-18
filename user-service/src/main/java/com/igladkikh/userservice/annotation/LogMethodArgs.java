package com.igladkikh.userservice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннтотация используется для маркировки методов, пождлежащих централизованному логированию.
 * Используется классом {@link com.igladkikh.userservice.aspect.LoggingAspect LoggingAspect}.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LogMethodArgs {
}

