package ru.hw.service;

public class CellImpl implements Cell {
    private final Banknote banknote;
    private int countBanknote = 0;

    public CellImpl(Banknote banknote) {
        this.banknote = banknote;
    }

    @Override
    public boolean addBanknote(int count) {
        checkPositiveCount(count);

        this.countBanknote += count;

        return true;
    }

    @Override
    public int getBanknote(int count) {
        checkPositiveCount(count);

        if (this.countBanknote < count)
            return 0;

        this.countBanknote -= count;

        return count;
    }

    @Override
    public int getBalance() {
        return this.countBanknote;
    }

    private void checkPositiveCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Количество банкнот не должно быть отрицательное количество");
        }
    }
}
