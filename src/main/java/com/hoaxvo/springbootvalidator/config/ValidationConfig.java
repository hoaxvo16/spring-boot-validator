package com.hoaxvo.springbootvalidator.config;

import com.hoaxvo.springbootvalidator.annotation.Size;
import com.hoaxvo.springbootvalidator.lib.annotations.field.Email;
import com.hoaxvo.springbootvalidator.lib.container.AnnotationContainer;
import com.hoaxvo.springbootvalidator.lib.container.ContainerMember;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ValidationConfig {
    @PostConstruct
    public void annotationContainer() {
        AnnotationContainer.addMember(Arrays.asList(
                ContainerMember
                        .builder()
                        .serviceBeanName("myCustomSizeService")
                        .annotation(Size.class)
                        .build(),
                ContainerMember
                        .builder()
                        .annotation(Email.class)
                        .build()));
    }
}
