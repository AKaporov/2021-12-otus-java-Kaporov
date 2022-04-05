package ru.otus.jdbc.mapper.jdbc.reflection;

import ru.otus.crm.annotation.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class IdAnnotationReflection {
    private final Class<?> clazz;

    public IdAnnotationReflection(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Field getIdAnnotationFiled() {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }

        return null;
    }

    public List<Field> getFieldsWithoutId(){
        Field[] fields = clazz.getDeclaredFields();

        List<Field> result = new ArrayList<>();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Id.class)) {
                result.add(field);
            }
        }

        return result;
    }
}
