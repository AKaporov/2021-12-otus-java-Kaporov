package ru.otus.jdbc.mapper;

import ru.otus.jdbc.mapper.jdbc.reflection.IdAnnotationReflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    public EntityClassMetaDataImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    private final Class<T> clazz;

    @Override
    public String getName() {
        return clazz.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() {
        try {
            return clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Field getIdField() {
        var idReflection = new IdAnnotationReflection(clazz);

        return idReflection.getIdAnnotationFiled();
    }

    @Override
    public List<Field> getAllFields() {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("declaredField.getName() = " + declaredField.getName());
        }
        return Arrays.asList(clazz.getDeclaredFields());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        var idReflection = new IdAnnotationReflection(clazz);
        return idReflection.getFieldsWithoutId();
    }
}
