package ru.otus.processor.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.Processor;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Процессор искючений")
class ProcessorThrowExceptionInAnEvenSecondTest {
    public static final String OBJECT_FOR_MESSAGE_ELEMENT_1 = "list_1";
    public static final String OBJECT_FOR_MESSAGE_ELEMENT_2 = "list_2";
    public static final String FIELD_1 = "field1";
    public static final String FIELD_2 = "field2";
    public static final String FIELD_3 = "field3";
    public static final String FIELD_4 = "field4";
    public static final String FIELD_5 = "field5";
    public static final String FIELD_6 = "field6";
    public static final String FIELD_7 = "field7";
    public static final String FIELD_8 = "field8";
    public static final String FIELD_9 = "field9";
    public static final String FIELD_10 = "field10";
    public static final String FIELD_11 = "field11";
    public static final String FIELD_12 = "field12";

    @Test
    @DisplayName("должен генерировать выбрасывать исключение в четную секунду")
    void shouldThrowExceptionInEvenSecond() {
        Message message = createMessage();

        Processor processor = new ProcessorThrowExceptionInAnEvenSecond();

        assertThrows(RuntimeException.class, () -> processor.process(message));
    }

    private Message createMessage() {
        ObjectForMessage objectforMessage = createObjectForMessage();

        return new Message.Builder(100L)
                .field1(FIELD_1)
                .field2(FIELD_2)
                .field3(FIELD_3)
                .field4(FIELD_4)
                .field5(FIELD_5)
                .field6(FIELD_6)
                .field7(FIELD_7)
                .field8(FIELD_8)
                .field9(FIELD_9)
                .field10(FIELD_10)
                .field11(FIELD_11)
                .field12(FIELD_12)
                .field13(objectforMessage)
                .build();
    }

    private ObjectForMessage createObjectForMessage() {
        List<String> objForMessageList = new ArrayList<>(2);
        objForMessageList.add(OBJECT_FOR_MESSAGE_ELEMENT_1);
        objForMessageList.add(OBJECT_FOR_MESSAGE_ELEMENT_2);

        ObjectForMessage objectforMessage = new ObjectForMessage();
        objectforMessage.setData(objForMessageList);
        return objectforMessage;
    }
}