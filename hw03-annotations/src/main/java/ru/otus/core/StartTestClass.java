package ru.otus.core;

import ru.otus.annotations.Test;
import ru.otus.reflection.ReflectionHelper;
import ru.otus.tests.ClassTest;

import java.lang.reflect.Method;

public class StartTestClass {
    private final Class<ClassTest> clazz;

    public StartTestClass(Class<ClassTest> testClass) {
        this.clazz = testClass;
    }

    public void start() {
        Object[] args = getArgs();
        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (int i = 0; i < declaredMethods.length; i++) {
            boolean testAnnotationPresent = declaredMethods[i].isAnnotationPresent(Test.class);

            if (testAnnotationPresent) {
                ClassTest instantiate = ReflectionHelper.instantiate(clazz, args);
                System.out.println("instantiate.hashCode() = " + instantiate.hashCode());
                instantiate.before();

                ReflectionHelper.callMethod(instantiate, declaredMethods[i].getName(), args);

                instantiate.after();
            }
        }
    }

    private Object[] getArgs() {
        return clazz.getAnnotatedInterfaces();
    }
}
