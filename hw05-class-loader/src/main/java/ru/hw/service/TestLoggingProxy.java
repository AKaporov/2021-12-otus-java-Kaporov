package ru.hw.service;

import ru.hw.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicReference;

public class TestLoggingProxy {
    private TestLoggingProxy() {
        System.out.println("constructor TestLoggingProxy");
    }

    public static TestLoggingService createTestLoggingInstance() {
        InvocationHandler handler = new TestLoggingInvocationHandler(new TestLoggingServiceImpl());
        return (TestLoggingService) Proxy.newProxyInstance(TestLoggingProxy.class.getClassLoader(),
                new Class<?>[]{TestLoggingService.class}, handler);

        // Guava
//        return Reflection.newProxy(TestLoggingService.class, handler);
    }

    @Override
    public String toString() {
        return "TestLoggingProxy{}";
    }

    static class TestLoggingInvocationHandler implements InvocationHandler {

        private final TestLoggingService myClass;

        public TestLoggingInvocationHandler(TestLoggingService myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class<? extends TestLoggingService> clazz = myClass.getClass();

            Class[] cArgs = null;
            if (args != null) {
                cArgs = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof Integer) {
                        cArgs[i] = Integer.TYPE;
                    } else {
                        cArgs[i] = args[i].getClass();
                    }
                }
            }

            Method declaredMethod = clazz.getDeclaredMethod(method.getName(), cArgs);

            if (declaredMethod.isAnnotationPresent(Log.class)) {
                AtomicReference<String> params = new AtomicReference<>("");

                if (args != null) {
                    for (Object arg : args) {
                        params.set(params + ", " + arg);
                    }
                }

                System.out.println("Execute method: " + method.getName() + ", param: " + params.get().replaceFirst(",", ""));
            }

            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "TestLoggingInvocationHandler{myClass = " + myClass + "}";
        }
    }
}
