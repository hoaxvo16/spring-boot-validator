package com.hoaxvo.springbootvalidator.lib.service.delegate;

import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.Objects;

@Slf4j
public abstract class ValidationService {
    public abstract void handleValidation(Field field, Object value, Parameter parameter, ValidationError validationError);

    public <A extends Annotation> A getAnnotation(Class<A> annotationClass, Parameter parameter, Field field) {
        A annotation = field.getAnnotation(annotationClass);
        if (Objects.nonNull(annotation))
            return annotation;
        Annotation[] annotations = parameter.getDeclaredAnnotations();
        for (Annotation a : annotations) {
            if (a.annotationType().equals(annotationClass))
                return (A) a;
        }
        log.error("Annotation {} not found", annotationClass);
        return null;
    }
}
