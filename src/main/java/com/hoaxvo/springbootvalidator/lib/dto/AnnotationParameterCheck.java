package com.hoaxvo.springbootvalidator.lib.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Parameter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnotationParameterCheck {
    private Parameter parameter;
    private boolean isAnnotationExist;
}
