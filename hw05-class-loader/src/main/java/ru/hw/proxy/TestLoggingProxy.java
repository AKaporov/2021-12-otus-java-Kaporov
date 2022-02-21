package ru.hw.proxy;

import ru.hw.annotations.Log;
import ru.hw.service.TestLoggingService;
import ru.hw.service.TestLoggingServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestLoggingProxy {
    private TestLoggingProxy() {
        System.out.println("constructor TestLoggingProxy");
    }

    public static TestLoggingService createTestLoggingInstance() {
        InvocationHandler handler = new TestLoggingInvocationHandler(new TestLoggingServiceImpl());
        return (TestLoggingService) Proxy.newProxyInstance(TestLoggingProxy.class.getClassLoader(), new Class<?>[]{TestLoggingService.class}, handler);

        // Guava
//        return Reflection.newProxy(TestLoggingService.class, handler);
    }

    @Override
    public String toString() {
        return "TestLoggingProxy{}";
    }

    static class TestLoggingInvocationHandler implements InvocationHandler {

        private final TestLoggingService myClass;
        private final Set<String> allMethodsLogClass;

        public TestLoggingInvocationHandler(TestLoggingService myClass) {
            this.myClass = myClass;

            allMethodsLogClass = getAllMethodsWithInputParamsAnnotatedLogClass();
        }

        private Set<String> getAllMethodsWithInputParamsAnnotatedLogClass() {
            Set<String> result = new HashSet<>();

            for (var m : myClass.getClass().getDeclaredMethods()) {
                if (m.isAnnotationPresent(Log.class)) {
                    result.add(getCode(m));
                }
            }

            return result;
        }

        private String getCode(Method m) {
            return m.getName() + "_" + Arrays.toString(m.getParameterTypes());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (allMethodsLogClass.contains(getCode(method))) {
                System.out.println("Execute method: " + method.getName() + ", param: " + Arrays.toString(args));
            }

            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "TestLoggingInvocationHandler{myClass = " + myClass + "}";
        }
    }
}
