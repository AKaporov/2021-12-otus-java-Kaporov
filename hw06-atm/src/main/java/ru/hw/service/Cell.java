package ru.hw.service;

public interface Cell {
    boolean addBanknote(int count);

    int getBanknote(int count);

    int getBalance();
}
