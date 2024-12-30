package com.tomzxy.webQuiz.util;

import com.tomzxy.webQuiz.exception.GlobalExceptionHandler;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermissionUtil {
    public static Set<String> getAllPermissions(List<Class<?>> classes) throws IllegalAccessException {
        Set<String> permissions = new HashSet<>();
        for (Class<?> clazz: classes) {
            permissions.addAll(getPermissionsFromClass(clazz));
        }
        return permissions;
    }

    private static Set<String> getPermissionsFromClass(Class<?> clazz) {
        Set<String> permissions = new HashSet<>();
        for (Field field : clazz.getDeclaredFields()) {
            try {
                Object value = field.get(null); // Lấy giá trị của hằng số
                if (value instanceof String) {
                    permissions.add((String) value);
                }
            } catch (IllegalAccessException e) {
                throw new ResourceNotFoundException("Error Illegal");
            }
        }
        for (Class<?> innerClass : clazz.getDeclaredClasses()) {
            permissions.addAll(getPermissionsFromClass(innerClass)); // Đệ quy vào các class con

        }
        return permissions;
    }

}