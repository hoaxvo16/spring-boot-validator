package com.hoaxvo.springbootvalidator.lib.service.delegate;

import com.hoaxvo.springbootvalidator.lib.annotations.field.NotNull;
import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.Objects;


@Service
@Slf4j
public class NotNullValidationService extends ValidationService {
    @Override
    public void handleValidation(Field field, Object value, Parameter parameter, ValidationError validationError) {
        if (Objects.isNull(value)) {
            log.info("Execute not null validate");
            NotNull annotation = getAnnotation(NotNull.class, parameter, field);
            validationError.makeError(annotation.code(), annotation.message());
        }
    }
}
