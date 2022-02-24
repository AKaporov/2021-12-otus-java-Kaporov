package ru.hw.service;

public class BanknoteImpl implements Banknote {
    private int count;

    public BanknoteImpl(int count) {
        checkPositiveCount(count);

        this.count = count;
    }

    public BanknoteImpl() {
    }

    @Override
    public void increase(int count) {
        checkPositiveCount(count);

        this.count += count;
    }

    @Override
    public int decrease(int count) {
        checkPositiveCount(count);

        if (this.count >= count) {
            this.count -= count;
            return count;
        }

        throw new RuntimeException("Недостаточное количество банкнот для выдачи!");
    }

    private void checkPositiveCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Количество банкнот не должно быть отрицательное количество");
        }
    }
}
