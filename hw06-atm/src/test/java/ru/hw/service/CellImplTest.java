package ru.hw.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@DisplayName("Класс Ячейка")
class CellImplTest {
    @Test
    @DisplayName("должен возвращать все добавленные банктонты в ячейки")
    void shouldReturnAllBanknoteFromCells() {
        Banknote fiveThousand = new BanknoteImpl();
        Banknote oneThousand = new BanknoteImpl();

        Cell five = new CellImpl(fiveThousand);
        Cell one = new CellImpl(oneThousand);

        five.addBanknote(5);
        five.addBanknote(3);

        one.addBanknote(1);

        int actualFive = five.getBanknote(8);
        int actualOne = one.getBanknote(1);

        int expectFive = 8;
        int expectOne = 1;

        assertAll(() -> {
            assertThat(actualFive).isEqualTo(expectFive);
            assertThat(actualOne).isEqualTo(expectOne);
        });

    }
}