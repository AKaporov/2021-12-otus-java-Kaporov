package ru.hw.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Класс Банкнот")
class BanknoteImplTest {

    @Test
    @DisplayName("должен выдавать ошибку, когда пытались увеличть количество банктнот на отрицательное количество")
    void shouldReturnIllegalArgumentExceptionWhenAddNotPositiveCount() {
        Banknote banknote = new BanknoteImpl();

        assertThatThrownBy(() -> banknote.add(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("должен добавлять 2 банкноты после создания")
    void shouldAddTwoBanknote() {
        Banknote actualBanknote = new BanknoteImpl();

        actualBanknote.add(2);

        assertThat(actualBanknote.get(2)).isEqualTo(2);
    }

    @Test
    @DisplayName("должен содать 5 банкнот и потом добавлять 2 банкноты")
    void shouldCreateFiveBanknoteAndAddTwoBanknote() {
        Banknote actualBanknote = new BanknoteImpl(5);

        actualBanknote.add(2);

        assertThat(actualBanknote.get(7)).isEqualTo(7);
    }

    @Test
    @DisplayName("должен создавать 5 банкнот, а возвращать только 3")
    void shouldCreateFiveBanknote() {
        Banknote actualBanknote = new BanknoteImpl(5);

        assertThat(actualBanknote.get(3)).isEqualTo(3);
    }

    @Test
    @DisplayName("должен вернуть ошибку, когда запросили больше банкнот, чем создали")
    void shouldReturnRuntimeExceptionWhenTryGetNotExistsCountBanknote() {
        Banknote actualBanknote = new BanknoteImpl(2);

        assertThatThrownBy(() -> actualBanknote.get(5)).isInstanceOf(RuntimeException.class);
    }
}