package com.hoaxvo.springbootvalidator.lib.service;


import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import com.hoaxvo.springbootvalidator.lib.store.AnnotationStore;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidationService {

    private final DeliveryService deliveryService;


    public void handleValidate(Field field, Object value, ValidationError validationError) {
        if (Objects.isNull(validationError))
            return;


        List<Class<? extends Annotation>> annotations = AnnotationStore.getAnnotations();

        annotations.forEach(annotation -> {
            if (field.isAnnotationPresent(annotation) && !validationError.isPresent()) {
                deliveryService.delivery(field, value, validationError, annotation);
            }
        });


    }

    @PostConstruct
    public void initStore() {
        AnnotationStore.init();
    }


}
