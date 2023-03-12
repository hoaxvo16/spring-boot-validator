package com.hoaxvo.springbootvalidator.service;

import com.hoaxvo.springbootvalidator.annotation.Size;
import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import com.hoaxvo.springbootvalidator.lib.service.delegate.ValidationService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

@Service("myCustomSizeService")
public class SizeService extends ValidationService {
    @Override
    public void handleValidation(Field field, Object value, Parameter parameter, ValidationError validationError) {
        Size annotation = getAnnotation(Size.class, parameter, field);
        String stringValue = String.valueOf(value);

        if (annotation.max() < stringValue.length())
            validationError.makeError(annotation.code(), annotation.message());

        if (annotation.min() > stringValue.length())
            validationError.makeError(annotation.code(), annotation.message());
    }
}

