package com.hoaxvo.springbootvalidator.lib.service.delegate;

import com.hoaxvo.springbootvalidator.lib.annotations.field.Email;
import com.hoaxvo.springbootvalidator.lib.constant.Message;
import com.hoaxvo.springbootvalidator.lib.constant.Regex;
import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.Objects;
import java.util.regex.Matcher;

@Service
public class EmailValidationService extends ValidationService {
    @Override
    public void handleValidation(Field field, Object value, Parameter parameter, ValidationError validationError) {
        Matcher matcher = Regex.VALID_EMAIL_ADDRESS_REGEX.matcher(String.valueOf(value));
        Email annotation = getAnnotation(Email.class, parameter, field);
        if (Objects.isNull(annotation))
            throw new RuntimeException(Message.ANNOTATION_NOT_FOUND);
        if (!matcher.find()) {
            validationError.makeError(annotation.code(), annotation.message());
        }
    }
}
