package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

/**
 * Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
 * Секунда должна определяьться во время выполнения.
 * Тест - важная часть задания
 * Обязательно посмотрите пример к паттерну Мементо!
 */
public class ProcessorThrowExceptionInAnEvenSecond implements Processor {
    @Override
    public Message process(Message message) {
        if (isEvenSecond()) {
            throw new RuntimeException("It's an even second :)");
        }

        return message;
    }

    private boolean isEvenSecond() {
        long sec = System.currentTimeMillis() / 1000L;
        return sec % 2 == 0;
    }
}
