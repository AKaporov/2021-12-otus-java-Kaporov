package ru.hw;

import ru.hw.service.TestLoggingService;
import ru.hw.service.TestLoggingServiceImpl;

public class HWDemo {
    public static void main(String[] args) {
        TestLoggingService testLoggingService = new TestLoggingServiceImpl();
        testLoggingService.calculation(1);
        testLoggingService.calculation(2, "Hello");
        testLoggingService.calculation(3, 4, "World");

    }
}
