package ru.otus;

import ru.otus.core.StartTestClass;
import ru.otus.tests.ClassTest;

public class HwMain {
    public static void main(String[] args) {
        StartTestClass startTestClass = new StartTestClass(ClassTest.class);
        startTestClass.start();
    }
}
