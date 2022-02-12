package ru.otus.tests;

import ru.otus.Car;
import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class CarTest {

    private Car testingCar;

    @Before
    public void before() {
        testingCar = new Car("BVM", 5);

        System.out.println("Method \"before()\" works, marked with my annotation @Before !!!!");
    }

    @After
    public void after() {
        System.out.println("Method \"after()\" works, marked with my annotation @After !!!!");
    }

    @Test
    void shouldReturnPersonalCarInformation() {
        testingCar.getPersonalInformation();
    }

    @Test
    void shouldTestingMethodOne() {
        throw new RuntimeException("Some Exception in TestingMethodOne");
//        System.out.println("Method \"shouldTestingMethodOne()\" works, marked with my annotation @Test !!!!");
    }

    @Test
    void shouldTestingMethodTwo() {
        throw new RuntimeException("Some Exception in TestingMethodTwo");
//        System.out.println("Method \"shouldTestingMethodTwo()\" works, marked with my annotation @Test !!!!");
    }
}