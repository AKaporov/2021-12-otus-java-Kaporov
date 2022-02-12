package ru.otus;

import ru.otus.core.StartTestingClass;
import ru.otus.tests.CarTest;

public class HwMain {
    public static void main(String[] args) {
        StartTestingClass startTestingClass = new StartTestingClass(CarTest.class);
        startTestingClass.start();
    }
}
