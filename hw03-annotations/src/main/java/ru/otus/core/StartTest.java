package ru.otus.core;

import ru.otus.annotations.Test;
import ru.otus.tests.ClassTest;
import ru.otus.reflection.ReflectionHelper;

import java.lang.reflect.Method;

public class StartTest {
    private final Class<ClassTest> clazz;

    public StartTest(Class<ClassTest> testClass) {
        this.clazz = testClass;
    }

    public void start() {
//        Constructor<?>[] constructors = clazz.getConstructors();
//        System.out.println("--- constructors:");
//        System.out.println(Arrays.toString(constructors));
//
//        Method[] methodsAll = clazz.getDeclaredMethods();
//        System.out.println("--- all methods:");
//        Arrays.stream(methodsAll).forEach(method -> System.out.println(method.getName()));
//
//        var constructor = getConstructorWithOutInputParam(constructors);
//        constructor.setAccessible(true);
//        clazz.getClass() o = (clazz.getClass()) constructor.newInstance();
//        o.before();
//        getNewInstance(constructor);

        Object[] args = getArgs();
        ClassTest instantiate = ReflectionHelper.instantiate(clazz, args);

        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (int i = 0; i < declaredMethods.length; i++) {
//            System.out.println("declaredMethods[" + i + "].getName() = " + declaredMethods[i].getName());

            boolean testAnnotationPresent = declaredMethods[i].isAnnotationPresent(Test.class);

            if (testAnnotationPresent) {
                instantiate.before();

                ReflectionHelper.callMethod(instantiate, declaredMethods[i].getName(), args);

                instantiate.after();
            }
        }

    }

    private Object[] getArgs() {
        return new Object[0];
    }

//    private Constructor getConstructorWithOutInputParam(Constructor<?>[] constructors) {
//        Constructor constructor = null;
//        for (int i = 0; i < constructors.length; i++) {
//            constructor = constructors[i];
//            if (constructor.getGenericParameterTypes().length == 0)
//                break;
//        }
//
//        return constructor;
//    }
}
