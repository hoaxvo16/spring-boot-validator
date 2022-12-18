package com.hoaxvo.springbootvalidator.lib.annotations.field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Size {

    String message() default "Size not valid";

    String code() default "";

    int max() default Integer.MAX_VALUE;

    int min() default 0;
}
