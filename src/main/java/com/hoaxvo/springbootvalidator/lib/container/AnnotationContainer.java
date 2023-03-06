package com.hoaxvo.springbootvalidator.lib.container;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AnnotationContainer {

    private final static Map<String, ContainerMember> annotationMap = new HashMap<>();

    public static void addMember(ContainerMember containerMember) {
        String annotationName = containerMember.getAnnotation().getSimpleName();
        if (annotationMap.containsKey(annotationName)) {
            log.warn("Annotation {} has been already add to container", annotationName);
            return;
        }
        annotationMap.put(annotationName, containerMember);
    }

    public static void addMember(List<ContainerMember> containerMembers) {
        containerMembers.forEach(containerMember -> {
            String annotationName = containerMember.getAnnotation().getSimpleName();
            if (annotationMap.containsKey(annotationName)) {
                log.warn("Annotation {} has been already add to container", annotationName);
                return;
            }
            annotationMap.put(annotationName, containerMember);
        });
        log.info("Add {} member to validation container", containerMembers.size());

    }

    public static List<ContainerMember> getAnnotations() {
        return annotationMap.values().stream().toList();
    }

}
