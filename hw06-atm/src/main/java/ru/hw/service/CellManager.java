package ru.hw.service;

public interface CellManager {
    void allocateAmountToCells(int amount);

    int getAmountMinBanknoteFromAllCell(int amount);

    int getBalanceFromAllCell();

}
