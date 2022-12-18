package com.hoaxvo.springbootvalidator.lib.store;


import com.hoaxvo.springbootvalidator.lib.annotations.field.Email;
import com.hoaxvo.springbootvalidator.lib.annotations.field.NotNull;
import com.hoaxvo.springbootvalidator.lib.annotations.field.Size;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AnnotationStore {

    private final static List<Class<? extends Annotation>> annotations = new ArrayList<>();

    public static void init() {
        log.info("Init validation annotation store");
        annotations.add(NotNull.class);
        annotations.add(Size.class);
        annotations.add(Email.class);
    }

    public static List<Class<? extends Annotation>> getAnnotations() {
        return annotations;
    }
}
