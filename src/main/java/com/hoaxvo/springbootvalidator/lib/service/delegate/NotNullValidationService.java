package com.hoaxvo.springbootvalidator.lib.service.delegate;

import com.hoaxvo.springbootvalidator.lib.annotations.field.NotNull;
import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Objects;


@Service
@Slf4j
public class NotNullValidationService implements ValidationService {
    @Override
    public void handleValidation(Field field, Object value, ValidationError validationError) {
        if (Objects.isNull(value)) {
            log.info("Execute not null validate");
            NotNull annotation = field.getAnnotation(NotNull.class);
            validationError.makeError(annotation.code(), annotation.message());
        }
    }
}
