package com.luhan.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 〈注解相关的工具类〉<br>
 *
 * @author luHan
 * @create 2019-09-26 18:06
 * @since 1.0.0
 */
public class AnnotationUtil {
    /**
     * 修改类指定注解的指定属性的值
     * @param obj 实例
     * @param annotation 注解类
     * @param propertyName 注解属性
     * @param value 修改后的值
     */
    public static void updateAnnotationValue(Class obj, Class annotation, String propertyName, Object value) throws NoSuchFieldException, IllegalAccessException {
        updateAnnotationValue(obj.getAnnotation(annotation),propertyName, value);
    }

    /**
     * 修改类方法指定的注解的值
     * @param method 方法
     * @param annotation 注解类
     * @param propertyName 注解属性
     * @param value 修改后的值
     */
    public static void updateMethodAnnotationValue(Method method , Class annotation, String propertyName, Object value) throws NoSuchFieldException, IllegalAccessException {
        updateAnnotationValue(method.getAnnotation(annotation),propertyName, value);
    }

    /**
     * 修改类属性指定的注解的值
     * @param field 属性
     * @param annotation 注解类
     * @param propertyName 注解属性
     * @param value 修改后的值
     */
    public static void updateFieldAnnotationValue(Field field , Class annotation, String propertyName, Object value) throws NoSuchFieldException, IllegalAccessException {
        updateAnnotationValue(field.getAnnotation(annotation),propertyName, value);
    }

    /**
     * 修改指定注解的值
     * @param annotation 注解实例
     * @param propertyName 属性名称
     * @param value 修改后的值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    public static void updateAnnotationValue(Annotation annotation,String propertyName, Object value) throws NoSuchFieldException, IllegalAccessException {
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        // 过去私有 memberValues 属性
        Field f = invocationHandler.getClass().getDeclaredField("memberValues");
        f.setAccessible(true);
        // 获取实例的属性map
        Map<String, Object> memberValues = (Map<String, Object>) f.get(invocationHandler);
        // 修改属性值
        memberValues.put(propertyName, value);
    }
}
