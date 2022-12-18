package com.hoaxvo.springbootvalidator.lib.aspect;

import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import com.hoaxvo.springbootvalidator.lib.service.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ValidationAspect {
    private final ValidationService validationService;


    @Around("@annotation(com.hoaxvo.springbootvalidator.lib.annotations.Validated)")
    public Object executeValidate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();

        ValidationError validationError = findValidationError(args);

        for (Object param : args) {
            if (param.getClass() != ValidationError.class)
                for (Field field : param.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    Object fieldValue = field.get(param);
                    validationService.handleValidate(field, fieldValue, validationError);
                }
        }

        return proceedingJoinPoint.proceed();
    }


    private ValidationError findValidationError(Object[] args) {
        if (Objects.nonNull(args)) {
            for (Object arg : args) {
                if (arg instanceof ValidationError) {
                    return (ValidationError) arg;
                }
            }
        }

        return null;
    }


}
