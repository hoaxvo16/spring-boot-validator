package com.hoaxvo.springbootvalidator.lib.utils;

public class ObjectUtils {

    public static boolean isPrimitiveValue(Object value) {
        if (value instanceof String)
            return true;
        if (value instanceof Long)
            return true;
        if (value instanceof Integer)
            return true;
        if (value instanceof Character)
            return true;
        if (value instanceof Byte)
            return true;

        return false;
    }
}
