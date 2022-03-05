package ru.hw.service;

import java.util.Objects;

public record BanknoteRecords(int nominal) {
    public BanknoteRecords {
        Objects.requireNonNull(nominal);
        Objects.nonNull(nominal);
        Objects.isNull(nominal);
    }
}
