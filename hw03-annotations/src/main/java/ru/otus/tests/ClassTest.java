package ru.otus.tests;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class ClassTest {
    @Before
    public void before(){
        System.out.println("Method \"before()\" works, marked with my annotation @Before !!!!");
    }

    @After
    public void after(){
        System.out.println("Method \"after()\" works, marked with my annotation @After !!!!");
    }

    @Test
    void shouldTestingMethodOne(){
        System.out.println("Method \"shouldTestingMethodOne()\" works, marked with my annotation @Test !!!!");
    }

    @Test
    void shouldTestingMethodTwo(){
        System.out.println("Method \"shouldTestingMethodTwo()\" works, marked with my annotation @Test !!!!");
    }
}