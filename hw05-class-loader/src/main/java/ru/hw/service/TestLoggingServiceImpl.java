package ru.hw.service;

import ru.hw.annotations.Log;

public class TestLoggingServiceImpl implements TestLoggingService {
    @Log
    @Override
    public String calculation() {
        return "Return value: call method calculation with @Log";
    }

    @Log
    @Override
    public void calculation(int param) {
        System.out.println("param: " + param);
    }

    @Override
    public void calculation(int param1, String str) {
        System.out.println("param1 = " + param1 + " / param2 = " + str);
    }

    @Log
    @Override
    public void calculation(int param1, int param2, String str) {
        System.out.println("param1 = " + param1 + " / param2 = " + param2 + " / param3 = " + str);
    }

    @Log
    @Override
    public void doSomeThing() {
        System.out.println("coll method doSomeThing with empty param!");
    }

    @Override
    public void doSomeThing(String str) {
        System.out.println("coll method doSomeThing with param = \"" + str + "\"");
    }

    @Override
    public String toString() {
        return "TestLoggingServiceImpl{}";
    }
}
