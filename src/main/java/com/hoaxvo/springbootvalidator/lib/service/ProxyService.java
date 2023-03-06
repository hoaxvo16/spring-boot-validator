package com.hoaxvo.springbootvalidator.lib.service;


import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import com.hoaxvo.springbootvalidator.lib.service.delegate.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.hoaxvo.springbootvalidator.lib.constant.BeanName.SERVICE_NAME;

@Service
@RequiredArgsConstructor
public class ProxyService {
    private final ApplicationContext applicationContext;

    public void delivery(Field field, Object value,
                         ValidationError validationError,
                         Class<? extends Annotation> annotation,
                         String serviceBeanName) {
        String beanName;

        if (StringUtils.hasText(serviceBeanName)) {
            beanName = serviceBeanName;
        } else {
            beanName = annotation.getSimpleName().toLowerCase().concat(SERVICE_NAME);
        }

        ValidationService validationService = (ValidationService) applicationContext.getBean(beanName);
        validationService.handleValidation(field, value, validationError);
    }
}
