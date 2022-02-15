package ru.otus.core;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.reflection.ReflectionHelper;

import java.lang.reflect.Method;

public class MainTestingClass {
    private final Class<?> clazz;
    private static final Object[] argsEmpty = new Class[0];

    public MainTestingClass(Class<?> testClass) {
        this.clazz = testClass;
    }

    public static <T> T myInstantiate(Class<T> type) {
        return ReflectionHelper.instantiate(type, argsEmpty);
    }

    public static Object myCallMethod(Object object, String name) {
        return ReflectionHelper.callMethod(object, name, argsEmpty);
    }

    public void start() {
        int countAllTest = 0;
        int countSuccessfulTest = 0;
        int countErrorTest = 0;
        Method beforeMethod = null;
        Method afterMethod = null;
//        Object[] args = new Class[0];
        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method m : declaredMethods) {
            if (m.isAnnotationPresent(Before.class))
                beforeMethod = m;

            if (m.isAnnotationPresent(After.class))
                afterMethod = m;
        }

        for (Method m : declaredMethods) {
            boolean testAnnotationPresent = m.isAnnotationPresent(Test.class);

            if (testAnnotationPresent) {
                ++countAllTest;
//                Object instantiate = ReflectionHelper.instantiate(clazz, args);
                Object instantiate = myInstantiate(clazz);

//                System.out.println("methodName = " + declaredMethods[i].getName() + " / instantiate.hashCode() = " + instantiate.hashCode());

                if (beforeMethod != null)
//                    ReflectionHelper.callMethod(instantiate, beforeMethod.getName(), args);
                    myCallMethod(instantiate, beforeMethod.getName());

                try {
//                    ReflectionHelper.callMethod(instantiate, m.getName(), args);
                    myCallMethod(instantiate, m.getName());


                    if (afterMethod != null)
//                        ReflectionHelper.callMethod(instantiate, afterMethod.getName(), args);
                        myCallMethod(instantiate, afterMethod.getName());

                    ++countSuccessfulTest;
                } catch (RuntimeException e) {
                    if (afterMethod != null)
//                        ReflectionHelper.callMethod(instantiate, afterMethod.getName(), args);
                        myCallMethod(instantiate, afterMethod.getName());

                    ++countErrorTest;
                }

            }
        }

        System.out.println("Всего тестов: " + countAllTest + ", успешных: " + countSuccessfulTest + ", ошибочных: " + countErrorTest);
    }
}
