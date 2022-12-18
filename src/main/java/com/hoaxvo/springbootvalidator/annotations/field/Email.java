package com.hoaxvo.springbootvalidator.annotations.field;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
    String message() default "Email invalid";


    String code() default "";
}
