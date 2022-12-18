package com.hoaxvo.springbootvalidator.lib.service.delegate;

import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;

import java.lang.reflect.Field;

public interface DelegateService {
    void handleValidation(Field field, Object value, ValidationError validationError);
}
