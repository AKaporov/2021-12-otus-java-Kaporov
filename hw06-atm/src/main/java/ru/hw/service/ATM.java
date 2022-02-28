package ru.hw.service;

public interface ATM {
    void addAmount(int amount);

    int getAmountMinBanknote(int amount);

    int getBalance();
}
