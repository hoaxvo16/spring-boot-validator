package com.hoaxvo.springbootvalidator.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import com.hoaxvo.springbootvalidator.lib.service.ValidationEngine;
import com.hoaxvo.springbootvalidator.lib.utils.ObjectUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AuditAspect {
    private final HttpServletRequest httpServletRequest;
    private final ObjectMapper objectMapper;

    @Around("@annotation(com.hoaxvo.springbootvalidator.annotation.Audit)")
    public Object processAuditLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String auditTraceId = UUID.randomUUID().toString();
        MDC.put("traceId", auditTraceId);
        Method method = signature.getMethod();
        Object[] args = proceedingJoinPoint.getArgs();
        Audit audit = method.getAnnotation(Audit.class);
        String payload = objectMapper.writeValueAsString(args);
        String functionName = audit.functionName();
        return proceedingJoinPoint.proceed();
    }

    private String getCollectionName() {
        String url = httpServletRequest.getRequestURI();
        String httpMethod = httpServletRequest.getMethod();
        String[] strings = url.split("/");
        if (strings.length == 1 || StringUtils.isEmpty(strings[1])) {
            throw new RuntimeException("Cannot get collection name in url for audit purpose the url must be pattern /{collectionname}/**");
        }
        return strings[1];
    }


}