package ru.hw.service;

public class CellImpl implements Cell {
    private final Banknote banknote;

    public CellImpl(Banknote banknote) {
        this.banknote = banknote;
    }

    @Override
    public boolean addBanknote(int countBanknote) {
        banknote.increase(countBanknote);

        return true;
    }

    @Override
    public int getBanknote(int countBanknote) {
        return banknote.decrease(countBanknote);
    }

}
