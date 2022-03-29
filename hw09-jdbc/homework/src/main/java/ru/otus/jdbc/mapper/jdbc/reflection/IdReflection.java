package ru.otus.jdbc.mapper.jdbc.reflection;

import java.lang.reflect.Field;

public class IdReflection {
    private final Class<?> clazz;

    public IdReflection(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Field getIdFiled(){
        return null;
    }
}
