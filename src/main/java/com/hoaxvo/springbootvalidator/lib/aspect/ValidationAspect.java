package com.hoaxvo.springbootvalidator.lib.aspect;

import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import com.hoaxvo.springbootvalidator.lib.service.ValidationEngine;
import com.hoaxvo.springbootvalidator.lib.utils.ObjectUtils;
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
    private final ValidationEngine validationEngine;

    @Around("@annotation(com.hoaxvo.springbootvalidator.lib.annotations.Validated)")
    public Object executeValidate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();

        ValidationError validationError = findValidationError(args);
        int index = 0;
        for (Object param : args) {
            if (param.getClass() != ValidationError.class)
                for (Field field : param.getClass().getDeclaredFields()) {
                    Object fieldValue;
                    boolean isPrimitive = ObjectUtils.isPrimitiveValue(param);
                    if (ObjectUtils.isPrimitiveValue(param)) {
                        fieldValue = param;
                    } else {
                        field.setAccessible(true);
                        fieldValue = field.get(param);
                    }
                    validationEngine.handleValidate(field, fieldValue, validationError,
                            proceedingJoinPoint, isPrimitive, index);
                }
            index++;
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
