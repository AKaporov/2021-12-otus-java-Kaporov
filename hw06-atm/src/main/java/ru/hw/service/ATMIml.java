package ru.hw.service;

public class ATMIml implements ATM {
    private final CellManager cellManager = new CellManagerImpl();

    @Override
    public void addAmount(int amount) {
        cellManager.allocateAmountToCells(amount);
    }

    @Override
    public int getAmountMinBanknote(int amount) {
        return cellManager.getAmountMinBanknoteFromAllCell(amount);
    }

    @Override
    public int getBalance() {
        return cellManager.getBalanceFromAllCell();
    }
}