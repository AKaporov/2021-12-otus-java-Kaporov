package ru.hw.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;


@DisplayName("Класс Ячейка")
class CellImplTest {
    public static final int EXPECTED_BALANCE = 10;

    @Test
    @DisplayName("должен возвращать все добавленные банктонты в ячейки")
    void shouldReturnAllBanknoteFromCells() {
        // TODO: 05.03.2022 Использоваие BanknoteEnum
//        BanknoteRecords fiveThousand = new BanknoteRecords(5000);
//        BanknoteRecords oneThousand = new BanknoteRecords(1000);

//        Cell firstCell = new CellImpl(fiveThousand);
        Cell firstCell = new CellImpl();
        firstCell.addBanknote(5);
        firstCell.addBanknote(EXPECTED_BALANCE);

//        Cell secondCell = new CellImpl(oneThousand);
        Cell secondCell = new CellImpl();
        secondCell.addBanknote(1);

        int actualFive = firstCell.getBanknote(8);
        int actualOne = secondCell.getBanknote(1);

        int expectFive = 8;
        int expectOne = 1;

        assertAll(() -> {
            assertThat(actualFive).isEqualTo(expectFive);
            assertThat(actualOne).isEqualTo(expectOne);
        });
    }

    @Test
    @DisplayName("должен вернуть ошибку, когда пытались увеличть количество банктнот на отрицательное количество")
    void shouldReturnIllegalArgumentExceptionWhenAddNotPositiveCount() {
        // TODO: 05.03.2022 Использоваие BanknoteEnum
//        Cell cell = new CellImpl(new BanknoteRecords(100));
        Cell cell = new CellImpl();

        assertThatThrownBy(() -> cell.addBanknote(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("должен вернуть нуль ианкнот, если запросили больше банкнот, чем есть")
    void shouldReturnRuntimeExceptionWhenTryGetNotExistsCountBanknote() {
        // TODO: 05.03.2022 Использоваие BanknoteEnum
//        Cell cell = new CellImpl(new BanknoteRecords(100));
        Cell cell = new CellImpl();

        cell.addBanknote(EXPECTED_BALANCE);

        int actualCount = cell.getBanknote(15);

        assertThat(actualCount).isZero();
    }

    @Test
    @DisplayName("должен вернуть сумму остатка")
    void shouldReturnBalance() {
        // TODO: 05.03.2022 Использоваие BanknoteEnum
//        Cell cell = new CellImpl(new BanknoteRecords(100));
        Cell cell = new CellImpl();

        cell.addBanknote(EXPECTED_BALANCE);

        int actualBalance = cell.getBalance();

        assertThat(actualBalance).isEqualTo(EXPECTED_BALANCE);
    }
}