package ru.otus;

import ru.otus.annotations.Test;
import ru.otus.core.StartTest;
import ru.otus.tests.ClassTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class HwMain {
    public static void main(String[] args) {
        StartTest startTest = new StartTest(ClassTest.class);
        startTest.start();


        System.out.println("************************** start main *********");

        Class<ClassTest> clazz = ClassTest.class;

        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.println("--- constructors:");
        System.out.println(Arrays.toString(constructors));

        Method[] methodsAll = clazz.getDeclaredMethods();
        System.out.println("--- all methods:");
        Arrays.stream(methodsAll).forEach(method -> System.out.println(method.getName()));
        System.out.println("----------------------------------");

        // TODO: 02.02.2022 Получить конструктор тестируемого класса
        Constructor constructor = null;
        for (int i = 0; i < constructors.length; i++) {
            constructor = constructors[i];
            if (constructor.getGenericParameterTypes().length == 0)
                break;
        }

        try {
            constructor.setAccessible(true);
            ClassTest instance = (ClassTest) constructor.newInstance();

            // TODO: 02.02.2022 Получить список методов помеченных аннотацией Test
            Method declaredMethod = null;
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; i++) {
                declaredMethod = declaredMethods[i];

                boolean annotationPresent = declaredMethods[i].isAnnotationPresent(Test.class);
                if (annotationPresent) {
                    // TODO: 02.02.2022 Вызов методов тестируемого класса
                    instance.before();

                    try {
                        declaredMethod.setAccessible(true);
                        declaredMethod.invoke(instance);

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    instance.after();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("************************** finish");
    }
}
