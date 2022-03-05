package ru.hw.service;

public class CellManagerImpl implements CellManager {
    // TODO: 05.03.2022 Использоваие BanknoteEnum
//    private static final int FIVE_THOUSAND = 5000;
//    private static final int ONE_THOUSAND = 1000;
//    private static final int FIVE_HUNDRED = 500;
//    private static final int ONE_HUNDRED = 100;

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
        // TODO: 05.03.2022 Использоваие BanknoteEnum
//        this.fiveThousandCell = new CellImpl(new BanknoteRecords(FIVE_THOUSAND));
//        this.oneThousandCell = new CellImpl(new BanknoteRecords(ONE_THOUSAND));
//        this.fiveHundredCell = new CellImpl(new BanknoteRecords(FIVE_HUNDRED));
//        this.oneHundredCell = new CellImpl(new BanknoteRecords(ONE_HUNDRED));

        this.fiveThousandCell = new CellImpl();
        this.oneThousandCell = new CellImpl();
        this.fiveHundredCell = new CellImpl();
        this.oneHundredCell = new CellImpl();
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
//            restAmount -= fiveThousand * FIVE_THOUSAND;
            restAmount -= fiveThousand * BanknoteEnum.FIVE_THOUSAND.nominal;
        }

        int oneThousand = getMinBanknoteFromOneThousandCell(restAmount);
        if (oneThousand > 0) {
//            restAmount -= oneThousand * ONE_THOUSAND;
            restAmount -= oneThousand * BanknoteEnum.ONE_THOUSAND.nominal;
        }
        int fiveHundred = getMinBanknoteFromFiveHundred(restAmount);
        if (fiveHundred > 0) {
//            restAmount -= fiveHundred * FIVE_HUNDRED;
            restAmount -= fiveHundred * BanknoteEnum.FIVE_HUNDRED.nominal;
        }
        int oneHundred = getMinBanknoteFromOneHundred(restAmount);


        int result = fiveThousand * BanknoteEnum.FIVE_THOUSAND.nominal +
                oneThousand * BanknoteEnum.ONE_THOUSAND.nominal +
                fiveHundred * BanknoteEnum.FIVE_HUNDRED.nominal +
                oneHundred * BanknoteEnum.ONE_HUNDRED.nominal;

        if (result != amount) {
            throw new RuntimeException("Недостаточное количество банкнот для выдачи!");
        }

        return result;
    }

    private int getMinBanknoteFromOneHundred(int restAmount) {
        int result = 0;
        while (restAmount >= BanknoteEnum.ONE_HUNDRED.nominal) {
            if (oneHundredCell.getBalance() > 0) {
                result += oneHundredCell.getBanknote(1);

                restAmount -= BanknoteEnum.ONE_HUNDRED.nominal;
            } else {
                break;
            }
        }

        return result;
    }

    private int getMinBanknoteFromFiveHundred(int restAmount) {
        int result = 0;
        while (restAmount >= BanknoteEnum.FIVE_HUNDRED.nominal) {
            if (fiveHundredCell.getBalance() > 0) {
                result += fiveHundredCell.getBanknote(1);

                restAmount -= BanknoteEnum.FIVE_HUNDRED.nominal;
            } else {
                break;
            }
        }

        return result;
    }

    private int getMinBanknoteFromOneThousandCell(int restAmount) {
        int result = 0;
        while (restAmount >= BanknoteEnum.ONE_THOUSAND.nominal) {
            if (oneThousandCell.getBalance() > 0) {
                result += oneThousandCell.getBanknote(1);

                restAmount -= BanknoteEnum.ONE_THOUSAND.nominal;
            } else {
                break;
            }
        }

        return result;
    }

    private int getMinBanknoteFromFiveThousandCell(int restAmount) {
        int result = 0;
        while (restAmount >= BanknoteEnum.FIVE_THOUSAND.nominal) {
            if (fiveThousandCell.getBalance() > 0) {
                result += fiveThousandCell.getBanknote(1);

                restAmount -= BanknoteEnum.FIVE_THOUSAND.nominal;
            } else {
                break;
            }
        }

        return result;
    }

    @Override
    public int getBalanceFromAllCell() {
        return fiveThousandCell.getBalance() * BanknoteEnum.FIVE_THOUSAND.nominal +
                oneThousandCell.getBalance() * BanknoteEnum.ONE_THOUSAND.nominal +
                fiveHundredCell.getBalance() * BanknoteEnum.FIVE_HUNDRED.nominal +
                oneHundredCell.getBalance() * BanknoteEnum.ONE_HUNDRED.nominal;
    }

    private void addInOneHundredCell(int restAmount) {
        while (restAmount >= BanknoteEnum.ONE_HUNDRED.nominal) {
            oneHundredCell.addBanknote(1);
            restAmount -= BanknoteEnum.ONE_HUNDRED.nominal;
        }
    }

    private int addInFiveHundredCell(int restAmount) {
        while (restAmount >= BanknoteEnum.FIVE_HUNDRED.nominal) {
            fiveHundredCell.addBanknote(1);
            restAmount -= BanknoteEnum.FIVE_HUNDRED.nominal;
        }

        return restAmount;
    }

    private int addInOneThousandCell(int restAmount) {
        while (restAmount >= BanknoteEnum.ONE_THOUSAND.nominal) {
            oneThousandCell.addBanknote(1);
            restAmount -= BanknoteEnum.ONE_THOUSAND.nominal;
        }

        return restAmount;
    }

    private int addInFiveThousandCell(int restAmount) {
        while (restAmount >= BanknoteEnum.FIVE_THOUSAND.nominal) {
            fiveThousandCell.addBanknote(1);
            restAmount -= BanknoteEnum.FIVE_THOUSAND.nominal;
        }

        return restAmount;
    }


    public static class Builder {
        private Cell fiveThousandCell;
        private Cell oneThousandCell;
        private Cell fiveHundredCell;
        private Cell oneHundredCell;

        public Builder() {
//            this.fiveThousandCell = new CellImpl(new BanknoteRecords(FIVE_THOUSAND));
//            this.oneThousandCell = new CellImpl(new BanknoteRecords(ONE_THOUSAND));
//            this.fiveHundredCell = new CellImpl(new BanknoteRecords(FIVE_HUNDRED));
//            this.oneHundredCell = new CellImpl(new BanknoteRecords(ONE_HUNDRED));
            this.fiveThousandCell = new CellImpl();
            this.oneThousandCell = new CellImpl();
            this.fiveHundredCell = new CellImpl();
            this.oneHundredCell = new CellImpl();
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

