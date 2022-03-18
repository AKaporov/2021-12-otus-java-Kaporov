package ru.otus;

import ru.otus.core.MainTestingClass;
import ru.otus.tests.CarTest;

public class HwMain {
    public static void main(String[] args) {
        MainTestingClass mainTestingClass = new MainTestingClass(CarTest.class);
        mainTestingClass.start();
    }
}
