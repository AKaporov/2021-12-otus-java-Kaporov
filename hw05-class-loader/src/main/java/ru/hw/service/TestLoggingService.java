package ru.hw.service;

public interface TestLoggingService {

    String calculation();

    void calculation(int param);

    void calculation(int param1, String str);

    void calculation(int param1, int param2, String str);

    void doSomeThing();

    void doSomeThing(String str);
}
