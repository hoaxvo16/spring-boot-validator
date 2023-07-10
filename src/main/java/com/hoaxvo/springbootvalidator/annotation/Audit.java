package com.hoaxvo.springbootvalidator.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @params functionName: purpose of the current function
 * @implNote The url of controller must be follow rule /{collectionName}/** for this annotation work correctly,
 * If you use this annotation please do not use SLF4J's MDC for trace log because the annotation already done it
 **/
@Retention(RUNTIME)
@Target(METHOD)
public @interface Audit {
    String functionName() default "";
}
