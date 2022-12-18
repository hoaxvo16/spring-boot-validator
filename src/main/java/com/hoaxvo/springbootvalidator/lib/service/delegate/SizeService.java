package com.hoaxvo.springbootvalidator.lib.service.delegate;

import com.hoaxvo.springbootvalidator.lib.annotations.field.Size;
import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class SizeService implements DelegateService {
    @Override
    public void handleValidation(Field field, Object value, ValidationError validationError) {
        Size annotation = field.getAnnotation(Size.class);
        String stringValue = String.valueOf(value);

        if (annotation.max() < stringValue.length())
            validationError.makeError(annotation.code(), annotation.message());

        if (annotation.min() > stringValue.length())
            validationError.makeError(annotation.code(), annotation.message());
    }
}

