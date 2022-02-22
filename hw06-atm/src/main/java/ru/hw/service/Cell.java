package ru.hw.service;

public interface Cell {
    Cell create(ATM atm, Banknote banknote);

    boolean addBanknote(Banknote banknote);

    int getBanknote(int count);
}
