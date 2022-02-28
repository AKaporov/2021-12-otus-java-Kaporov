package ru.hw;

import ru.hw.service.ATM;
import ru.hw.service.ATMIml;

public class HWDemo {
    public static void main(String[] args) {
        ATM atm = new ATMIml();
        atm.addAmount(12500);

        System.out.println("Balance = " + atm.getBalance());
        System.out.println("get 10_000 = " + atm.getAmountMinBanknote(10_000));
    }
}