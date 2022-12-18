package com.hoaxvo.springbootvalidator.service;


import com.hoaxvo.springbootvalidator.annotations.field.Email;
import com.hoaxvo.springbootvalidator.annotations.field.NotNull;
import com.hoaxvo.springbootvalidator.constant.Regex;
import com.hoaxvo.springbootvalidator.dto.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.regex.Matcher;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidationService {


    public void handleValidate(Field field, Object value, ValidationError validationError) {
        if (Objects.isNull(validationError))
            return;

        if (field.isAnnotationPresent(NotNull.class) && Objects.isNull(value)) {
            NotNull annotation = field.getAnnotation(NotNull.class);
            validationError.setCode(annotation.code());
            validationError.setMessage(annotation.message());
        }

        if (field.isAnnotationPresent(Email.class)) {
            Matcher matcher = Regex.VALID_EMAIL_ADDRESS_REGEX.matcher(String.valueOf(value));
            Email annotation = field.getAnnotation(Email.class);
            if (!matcher.find()) {
                validationError.setCode(annotation.code());
                validationError.setMessage(annotation.message());
            }
        }

    }
}
