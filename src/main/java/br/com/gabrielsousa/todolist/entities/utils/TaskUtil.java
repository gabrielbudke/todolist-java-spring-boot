package br.com.gabrielsousa.todolist.entities.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.BeanFactory;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskUtil {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        System.out.println("[BeanWrapper] " + src);
        PropertyDescriptor[] properties = src.getPropertyDescriptors();
        System.out.println("[properties] " + Arrays.toString(properties));
        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor property : properties) {
            Object sourceValue = src.getPropertyValue(property.getName());
            if(sourceValue == null) {
                emptyNames.add(property.getName());
            }
        }

        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);
    }
}
