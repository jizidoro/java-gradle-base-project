package com.comrades.core.bases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionHelper {

	private static final String CLASS_PROPERTY = "class";
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionHelper.class);
	private static List<String> ignoredFields;

	static {
		ignoredFields = new ArrayList<>();
		ignoredFields.add("class");
		ignoredFields.add("log");
	}

	public static List<PropertyDescriptor> getPropertyDescriptors(Class clazz) {
		try {
			return Arrays.stream(Introspector.getBeanInfo(clazz).getPropertyDescriptors())
					.filter(a -> !a.getName().equals(CLASS_PROPERTY)).collect(Collectors.toList());
		} catch (IntrospectionException e) {
			LOGGER.error(e.getMessage(), e);
			return new ArrayList<>();
		}
	}

	public static List<Field> getFields(Class clazz) {
		List<Field> fields = new ArrayList<>();
		while (clazz != null) {
			Collections.addAll(fields, clazz.getDeclaredFields());
			clazz = clazz.getSuperclass();
		}
		return fields;
	}

	private static Object tryGetValueByProperty(Field field, Object source) {
		List<PropertyDescriptor> properties = getPropertyDescriptors(source.getClass());
		PropertyDescriptor p = properties.stream().filter(a -> a.getName().equals(field.getName())).findAny()
				.orElse(null);

		if (p == null) {
			return null;
		}

		Method read = p.getReadMethod();
		try {
			return read.invoke(source);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.info(e.getMessage(), e);
		}
		return null;
	}

	public static Object getValue(Field field, Object source) {

		if (source == null) {
			return null;
		}

		Object value = tryGetValueByProperty(field, source);
		if (value != null) {
			return value;
		}

		if (!field.isAccessible()) {
			field.setAccessible(true);
		}

		try {
			return field.get(source);
		} catch (IllegalAccessException e) {
			LOGGER.info(e.getMessage(), e);
			return null;
		}
	}

	public static Field tryGet(Class clazz, String name) {
		if (ignoredFields.contains(name)) {
			return null;
		}
		try {
			Field f = clazz.getDeclaredField(name);
			f.setAccessible(true);
			return f;
		} catch (NoSuchFieldException e) {
			LOGGER.info(e.getMessage(), e);
			return null;
		}
	}

	private static boolean isValid(PropertyDescriptor field, List<String> names) {
		return names != null && names.contains(field.getName());
	}

	public static <T extends Annotation> T getAnnotation(Class clazz, PropertyDescriptor descriptor,
			Class<T> annotation) {
		if (descriptor.getReadMethod().isAnnotationPresent(annotation)) {
			return descriptor.getReadMethod().getAnnotation(annotation);
		}
		Field pf = tryGet(clazz, descriptor.getName());
		if (pf != null && pf.isAnnotationPresent(annotation)) {
			return pf.getAnnotation(annotation);
		}
		return null;
	}

	public static PropertyDescriptor tryGetField(List<PropertyDescriptor> fields, String[] nome,
			String[] nomeSecundario) {
		List<String> primary = nome == null ? null : Arrays.asList(nome);
		List<String> secondary = nomeSecundario == null ? null : Arrays.asList(nomeSecundario);
		PropertyDescriptor f = null;
		for (PropertyDescriptor field : fields) {
			if (isValid(field, primary)) {
				return field;
			}
			if (isValid(field, secondary)) {
				f = field;
			}
		}
		return f;
	}

	public static Class<?> getClass(Object o1, Object fallback) {
		if (o1 != null) {
			return o1.getClass();
		}
		return fallback.getClass();
	}

	public static Boolean implementsInterface(Object object, Class interf) {
		if (object == null) {
			return false;
		}
		for (Class c : object.getClass().getInterfaces()) {
			if (c.equals(interf)) {
				return true;
			}
		}
		return false;
	}
}