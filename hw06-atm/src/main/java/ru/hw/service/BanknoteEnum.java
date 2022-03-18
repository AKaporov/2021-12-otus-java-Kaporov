package ru.hw.service;

public enum BanknoteEnum {
    FIVE_THOUSAND(5000),
    ONE_THOUSAND(1000),
    FIVE_HUNDRED(500),
    ONE_HUNDRED(100);

    public final int nominal;

    BanknoteEnum(int nominal) {
        this.nominal = nominal;
    }
}
