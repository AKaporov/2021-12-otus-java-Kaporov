package ru.otus.dto;

import java.util.Arrays;
import java.util.Objects;

public class AppComponentDto {
    private final int order;
    private final String name;
    private final Object[] args;

    public AppComponentDto(int order, String name, Object[] args) {
        this.order = order;
        this.name = name;
        this.args = args;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public Object[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "AppComponentDto{" +
                "order=" + order +
                ", name='" + name + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
