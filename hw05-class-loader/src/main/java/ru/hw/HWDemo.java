package ru.hw;

import ru.hw.service.TestLoggingProxy;
import ru.hw.service.TestLoggingService;

public class HWDemo {
    public static void main(String[] args) {
        TestLoggingService myClass = TestLoggingProxy.createTestLoggingInstance();
        myClass.doSomeThing("hello world");
        myClass.doSomeThing();
        myClass.calculation(10);
        myClass.calculation(11, "from");
        myClass.calculation(12, 13, "agent");
        System.out.printf("OutPut from method = %s", myClass.calculation());

    }
}
