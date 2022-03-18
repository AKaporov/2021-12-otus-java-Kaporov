package ru.hw.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Класс Банкомат")
class ATMImlTest {

    public static final int FIVE_THOUSAND = 5000;
    public static final int ONE_THOUSAND = 1000;
    public static final int FINE_HUNDRED = 500;
    public static final int ONE_HUNDRED = 100;
    public static final int ONE_BANKNOTE = 1;
    public static final int TWO_BANKNOTE = 2;
    public static final int THREE_BANKNOTE = 3;
    public static final int FOUR_BANKNOTE = 4;

    @Test
    @DisplayName("должен создать банкомат с купюрами и вернуть запрошенную сумму")
    void shouldAdd6600BanknoteToATM() {
        ATM atm = new ATMIml();

        int exceptedAmount = ONE_BANKNOTE * FIVE_THOUSAND
                + ONE_BANKNOTE * ONE_THOUSAND
                + ONE_BANKNOTE * FINE_HUNDRED
                + ONE_BANKNOTE * ONE_HUNDRED;

        atm.addAmount(exceptedAmount);

        int actualAmount = atm.getAmountMinBanknote(exceptedAmount);

        assertThat(actualAmount).isEqualTo(exceptedAmount);
    }

    @Test
    @DisplayName("должен вернуть исклчение если сумму нельзя выдать")
    void shouldReturnExceptionThenAmountCannotIssued() {
        ATM atm = new ATMIml();

        assertThatThrownBy(() -> atm.getAmountMinBanknote(ONE_HUNDRED)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("должен создать пустой банкомат, принять банкноты разных номиналов и вернуть сумму остатка")
    void shouldReturnBalanceAmount() {
        ATM atm = new ATMIml();
        int exceptedBalanceAmount = ONE_BANKNOTE * FIVE_THOUSAND
                + TWO_BANKNOTE * ONE_THOUSAND
                + THREE_BANKNOTE * FINE_HUNDRED
                + FOUR_BANKNOTE * ONE_HUNDRED;

        atm.addAmount(exceptedBalanceAmount);

        int actualBalanceAmount = atm.getBalance();

        assertThat(actualBalanceAmount).isEqualTo(exceptedBalanceAmount);
    }
}