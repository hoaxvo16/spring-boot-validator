package com.hoaxvo.springbootvalidator.lib.service;


import com.hoaxvo.springbootvalidator.lib.container.AnnotationContainer;
import com.hoaxvo.springbootvalidator.lib.container.ContainerMember;
import com.hoaxvo.springbootvalidator.lib.dto.AnnotationParameterCheck;
import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidationEngine {

    private final ProxyService proxyService;


    public void handleValidate(Field field, Object value,
                               ValidationError validationError,
                               ProceedingJoinPoint proceedingJoinPoint,
                               boolean isPrimitive,
                               Integer paramIndex) {
        if (Objects.isNull(validationError))
            return;

        List<ContainerMember> containerMembers = AnnotationContainer.getAnnotations();

        containerMembers.forEach(containerMember -> {
            Class<? extends Annotation> annotation = containerMember.getAnnotation();
            String serviceBeanName = containerMember.getServiceBeanName();
            boolean isAnnotationExist;
            Parameter parameter = null;
            if (isPrimitive) {
                AnnotationParameterCheck annotationExist = isAnnotationExist(proceedingJoinPoint, annotation, paramIndex);
                isAnnotationExist = annotationExist.isAnnotationExist();
                parameter = annotationExist.getParameter();
            } else {
                isAnnotationExist = field.isAnnotationPresent(annotation);
            }
            if (isAnnotationExist && !validationError.isPresent()) {
                proxyService.delivery(field, value, parameter, validationError, annotation, serviceBeanName);
            }
        });

    }

    private AnnotationParameterCheck isAnnotationExist(ProceedingJoinPoint proceedingJoinPoint,
                                                       Class<? extends Annotation> annotation,
                                                       int paramIndex) {
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Parameter[] parameters = method.getParameters();
        return AnnotationParameterCheck.builder().
                parameter(parameters[paramIndex]).
                isAnnotationExist(parameters[paramIndex].isAnnotationPresent(annotation)).
                build();
    }
}
