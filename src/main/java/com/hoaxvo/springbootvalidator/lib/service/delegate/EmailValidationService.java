package com.hoaxvo.springbootvalidator.lib.service.delegate;

import com.hoaxvo.springbootvalidator.lib.annotations.field.Email;
import com.hoaxvo.springbootvalidator.lib.constant.Regex;
import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.regex.Matcher;

@Service
public class EmailValidationService implements ValidationService {
    @Override
    public void handleValidation(Field field, Object value, ValidationError validationError) {
        Matcher matcher = Regex.VALID_EMAIL_ADDRESS_REGEX.matcher(String.valueOf(value));
        Email annotation = field.getAnnotation(Email.class);
        if (!matcher.find()) {
            validationError.makeError(annotation.code(), annotation.message());
        }
    }
}
