package ru.hw.service;

public class CellManagerImpl implements CellManager {
    private static final int FIVE_THOUSAND = 5000;
    private static final int ONE_THOUSAND = 1000;
    private static final int FIVE_HUNDRED = 500;
    private static final int ONE_HUNDRED = 100;

    private Cell fiveThousandCell;
    private Cell oneThousandCell;
    private Cell fiveHundredCell;
    private Cell oneHundredCell;

    private CellManagerImpl(Builder builder) {
        this.fiveThousandCell = builder.fiveThousandCell;
        this.oneThousandCell = builder.oneThousandCell;
        this.fiveHundredCell = builder.fiveHundredCell;
        this.oneHundredCell = builder.oneHundredCell;
    }

    public CellManagerImpl() {
        this.fiveThousandCell = new CellImpl(new Banknote(FIVE_THOUSAND));
        this.oneThousandCell = new CellImpl(new Banknote(ONE_THOUSAND));
        this.fiveHundredCell = new CellImpl(new Banknote(FIVE_HUNDRED));
        this.oneHundredCell = new CellImpl(new Banknote(ONE_HUNDRED));
    }

    @Override
    public void allocateAmountToCells(int amount) {
        int restAmount = addInFiveThousandCell(amount);
        restAmount = addInOneThousandCell(restAmount);
        restAmount = addInFiveHundredCell(restAmount);
        addInOneHundredCell(restAmount);
    }

    @Override
    public int getAmountMinBanknoteFromAllCell(int amount) {
        int restAmount = amount;
        int fiveThousand = getMinBanknoteFromFiveThousandCell(restAmount);
        if (fiveThousand > 0) {
            restAmount -= fiveThousand * FIVE_THOUSAND;
        }

        int oneThousand = getMinBanknoteFromOneThousandCell(restAmount);
        if (oneThousand > 0) {
            restAmount -= oneThousand * ONE_THOUSAND;
        }
        int fiveHundred = getMinBanknoteFromFiveHundred(restAmount);
        if (fiveHundred > 0) {
            restAmount -= fiveHundred * FIVE_HUNDRED;
        }
        int oneHundred = getMinBanknoteFromOneHundred(restAmount);


        int result = fiveThousand * FIVE_THOUSAND +
                oneThousand * ONE_THOUSAND +
                fiveHundred * FIVE_HUNDRED +
                oneHundred * ONE_HUNDRED;

        if (result != amount) {
            throw new RuntimeException("Недостаточное количество банкнот для выдачи!");
        }

        return result;
    }

    private int getMinBanknoteFromOneHundred(int restAmount) {
        int result = 0;
        while (restAmount >= ONE_HUNDRED) {
            if (oneHundredCell.getBalance() > 0) {
                result += oneHundredCell.getBanknote(1);

                restAmount -= ONE_HUNDRED;
            } else {
                break;
            }
        }

        return result;
    }

    private int getMinBanknoteFromFiveHundred(int restAmount) {
        int result = 0;
        while (restAmount >= FIVE_HUNDRED) {
            if (fiveHundredCell.getBalance() > 0) {
                result += fiveHundredCell.getBanknote(1);

                restAmount -= FIVE_HUNDRED;
            } else {
                break;
            }
        }

        return result;
    }

    private int getMinBanknoteFromOneThousandCell(int restAmount) {
        int result = 0;
        while (restAmount >= ONE_THOUSAND) {
            if (oneThousandCell.getBalance() > 0) {
                result += oneThousandCell.getBanknote(1);

                restAmount -= ONE_THOUSAND;
            } else {
                break;
            }
        }

        return result;
    }

    private int getMinBanknoteFromFiveThousandCell(int restAmount) {
        int result = 0;
        while (restAmount >= FIVE_THOUSAND) {
            if (fiveThousandCell.getBalance() > 0) {
                result += fiveThousandCell.getBanknote(1);

                restAmount -= FIVE_THOUSAND;
            } else {
                break;
            }
        }

        return result;
    }

    @Override
    public int getBalanceFromAllCell() {
        return fiveThousandCell.getBalance() * FIVE_THOUSAND +
                oneThousandCell.getBalance() * ONE_THOUSAND +
                fiveHundredCell.getBalance() * FIVE_HUNDRED +
                oneHundredCell.getBalance() * ONE_HUNDRED;
    }

    private void addInOneHundredCell(int restAmount) {
        while (restAmount >= ONE_HUNDRED) {
            oneHundredCell.addBanknote(1);
            restAmount -= ONE_HUNDRED;
        }
    }

    private int addInFiveHundredCell(int restAmount) {
        while (restAmount >= FIVE_HUNDRED) {
            fiveHundredCell.addBanknote(1);
            restAmount -= FIVE_HUNDRED;
        }

        return restAmount;
    }

    private int addInOneThousandCell(int restAmount) {
        while (restAmount >= ONE_THOUSAND) {
            oneThousandCell.addBanknote(1);
            restAmount -= ONE_THOUSAND;
        }

        return restAmount;
    }

    private int addInFiveThousandCell(int restAmount) {
        while (restAmount >= FIVE_THOUSAND) {
            fiveThousandCell.addBanknote(1);
            restAmount -= FIVE_THOUSAND;
        }

        return restAmount;
    }


    public static class Builder {
        private Cell fiveThousandCell;
        private Cell oneThousandCell;
        private Cell fiveHundredCell;
        private Cell oneHundredCell;

        public Builder() {
            this.fiveThousandCell = new CellImpl(new Banknote(FIVE_THOUSAND));
            this.oneThousandCell = new CellImpl(new Banknote(ONE_THOUSAND));
            this.fiveHundredCell = new CellImpl(new Banknote(FIVE_HUNDRED));
            this.oneHundredCell = new CellImpl(new Banknote(ONE_HUNDRED));
        }

        Builder fiveThousandCell(int count) {
            this.fiveThousandCell.addBanknote(count);
            return this;
        }

        Builder oneThousandCell(int count) {
            this.oneThousandCell.addBanknote(count);
            return this;
        }

        Builder fiveHundredCell(int count) {

            this.fiveHundredCell.addBanknote(count);
            return this;
        }

        Builder oneHundredCell(int count) {
            this.oneHundredCell.addBanknote(count);
            return this;
        }

        CellManagerImpl build() {
            return new CellManagerImpl(this);
            //можно и так - return new ATMIml(fiveThousandCell, oneThousandCell, fiveHundredCell, oneHundredCell);
        }
    }
}

