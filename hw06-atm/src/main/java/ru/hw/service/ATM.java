package ru.hw.service;

public interface ATM {
    Cell createCell(Cell cellNew);

    boolean addBanknote(Cell cell, int banknotes);

    int getBanknote(Cell cell, int banknotes);
}
