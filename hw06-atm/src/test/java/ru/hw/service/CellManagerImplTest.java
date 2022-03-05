package ru.hw.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Менеджер ячеек")
class CellManagerImplTest {

    public static final int FIVE_THOUSAND = 5000;
    public static final int ONE_THOUSAND = 1000;
    public static final int FINE_HUNDRED = 500;
    public static final int ONE_HUNDRED = 100;
    public static final int ZERO_BANKNOTE = 0;
    public static final int ONE_BANKNOTE = 1;
    public static final int TWO_BANKNOTE = 2;
    public static final int THREE_BANKNOTE = 3;
    public static final int FOUR_BANKNOTE = 4;

    @Test
    @DisplayName("должен создать ячейки через Builder,  распредеить полученную сумму по ячейкам и вернуть общий баланс")
    void shouldCreateAllCellAndReturnBalanceAmount() {
        CellManagerImpl cellManager = new CellManagerImpl.Builder()
                .fiveThousandCell(ONE_BANKNOTE)
                .oneThousandCell(TWO_BANKNOTE)
                .fiveHundredCell(THREE_BANKNOTE)
                .oneHundredCell(FOUR_BANKNOTE)
                .build();

        int actualBalanceAmount = cellManager.getBalanceFromAllCell();

        int exceptedAmount = ONE_BANKNOTE * FIVE_THOUSAND
                + TWO_BANKNOTE * ONE_THOUSAND
                + THREE_BANKNOTE * FINE_HUNDRED
                + FOUR_BANKNOTE * ONE_HUNDRED;

        assertThat(actualBalanceAmount).isEqualTo(exceptedAmount);
    }

    @Test
    @DisplayName("должен распредеить полученную сумму по ячейкам и вернуть общий баланс")
    void shouldReturnBalanceAmount() {
        int exceptedAmount = ONE_BANKNOTE * FIVE_THOUSAND
                + TWO_BANKNOTE * ONE_THOUSAND
                + THREE_BANKNOTE * FINE_HUNDRED
                + FOUR_BANKNOTE * ONE_HUNDRED;

        CellManager cellManager = new CellManagerImpl();
        cellManager.allocateAmountToCells(exceptedAmount);

        int actualBalanceAmount = cellManager.getBalanceFromAllCell();

        assertThat(actualBalanceAmount).isEqualTo(exceptedAmount);
    }

    @Test
    @DisplayName("должен выбросить исключение если нельзя выдать запрошенную сумму")
    void shouldReturnExceptionThenAmountCannotIssued() {
        CellManager cellManager = new CellManagerImpl();

        assertThatThrownBy(() -> cellManager.getAmountMinBanknoteFromAllCell(ONE_HUNDRED)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("должен возвращать запрошенную сумму в минимальных купюрах (в наличии все купюры)")
    void shouldReturnAmountMinBanknoteFromAllCell() {
        CellManagerImpl cellManager = new CellManagerImpl.Builder()
                .fiveThousandCell(ONE_BANKNOTE)
                .oneThousandCell(TWO_BANKNOTE)
                .fiveHundredCell(THREE_BANKNOTE)
                .oneHundredCell(FOUR_BANKNOTE)
                .build();

        int exceptedAmount = ONE_BANKNOTE * FIVE_THOUSAND
                + TWO_BANKNOTE * ONE_THOUSAND
                + THREE_BANKNOTE * FINE_HUNDRED
                + TWO_BANKNOTE * ONE_HUNDRED;

        int actualAmount = cellManager.getAmountMinBanknoteFromAllCell(exceptedAmount);

        assertThat(actualAmount).isEqualTo(exceptedAmount);
    }

    @Test
    @DisplayName("должен выбрасывать исколючение, если сумму нельзя выдать")
    void shouldReturnExceptionWhenAmountCannotPaid() {
        CellManagerImpl cellManager = new CellManagerImpl.Builder()
                .fiveThousandCell(ONE_BANKNOTE)
                .oneThousandCell(TWO_BANKNOTE)
                .fiveHundredCell(THREE_BANKNOTE)
                .oneHundredCell(ONE_BANKNOTE)
                .build();

        int exceptedAmount = ONE_BANKNOTE * FIVE_THOUSAND
                + TWO_BANKNOTE * ONE_THOUSAND
                + THREE_BANKNOTE * FINE_HUNDRED
                + FOUR_BANKNOTE * ONE_HUNDRED;

        assertThatThrownBy(() -> cellManager.getAmountMinBanknoteFromAllCell(exceptedAmount)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("должен возвращать запрошенную сумму в минимальных купюрах (в наличии только 2000)")
    void shouldReturnAmountMinBanknoteFromTwoThousandCell() {
        CellManagerImpl cellManager = new CellManagerImpl.Builder()
                .fiveThousandCell(ZERO_BANKNOTE)
                .oneThousandCell(TWO_BANKNOTE)
                .fiveHundredCell(ZERO_BANKNOTE)
                .oneHundredCell(ZERO_BANKNOTE)
                .build();

        int exceptedAmount = TWO_BANKNOTE * ONE_THOUSAND;

        int actualAmount = cellManager.getAmountMinBanknoteFromAllCell(exceptedAmount);

        assertThat(actualAmount).isEqualTo(exceptedAmount);

    }
}