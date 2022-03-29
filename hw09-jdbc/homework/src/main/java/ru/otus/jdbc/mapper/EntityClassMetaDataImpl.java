package ru.otus.jdbc.mapper;

import ru.otus.jdbc.mapper.jdbc.reflection.IdReflection;

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
        return clazz.getCanonicalName();
    }

    @Override
    public Constructor<T> getConstructor() {
//        clazz.getConstructor(String.class)
        return null;
    }

    @Override
    public Field getIdField() {
        IdReflection idReflection = new IdReflection(clazz);

        return idReflection.getIdFiled();
    }

    @Override
    public List<Field> getAllFields() {
        return Arrays.asList(clazz.getFields());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return null;
    }
}
