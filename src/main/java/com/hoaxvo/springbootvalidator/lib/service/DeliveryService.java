package com.hoaxvo.springbootvalidator.lib.service;


import com.hoaxvo.springbootvalidator.lib.annotations.field.Email;
import com.hoaxvo.springbootvalidator.lib.annotations.field.NotNull;
import com.hoaxvo.springbootvalidator.lib.annotations.field.Size;
import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import com.hoaxvo.springbootvalidator.lib.service.delegate.EmailService;
import com.hoaxvo.springbootvalidator.lib.service.delegate.NotNullService;
import com.hoaxvo.springbootvalidator.lib.service.delegate.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final NotNullService notNullService;
    private final EmailService emailService;

    private final SizeService sizeService;

    public void delivery(Field field, Object value, ValidationError validationError, Class<? extends Annotation> anotation) {
        if (anotation == NotNull.class) {
            notNullService.handleValidation(field, value, validationError);
            return;
        }


        if (anotation == Email.class) {
            emailService.handleValidation(field, value, validationError);
            return;
        }

        if (anotation == Size.class) {
            sizeService.handleValidation(field, value, validationError);
        }

    }
}
