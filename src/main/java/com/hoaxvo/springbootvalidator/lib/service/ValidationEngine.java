package com.hoaxvo.springbootvalidator.lib.service;


import com.hoaxvo.springbootvalidator.lib.container.AnnotationContainer;
import com.hoaxvo.springbootvalidator.lib.container.ContainerMember;
import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
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
public class ValidationEngine {

    private final ProxyService proxyService;


    public void handleValidate(Field field, Object value, ValidationError validationError) {
        if (Objects.isNull(validationError))
            return;

        List<ContainerMember> containerMembers = AnnotationContainer.getAnnotations();

        containerMembers.forEach(containerMember -> {
            Class<? extends Annotation> annotation = containerMember.getAnnotation();
            String serviceBeanName = containerMember.getServiceBeanName();
            if (field.isAnnotationPresent(annotation) && !validationError.isPresent()) {
                proxyService.delivery(field, value, validationError, annotation, serviceBeanName);
            }
        });


    }
}
