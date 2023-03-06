package com.hoaxvo.springbootvalidator.lib.container;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Annotation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContainerMember {
    private Class<? extends Annotation> annotation;
    private String serviceBeanName;
}
