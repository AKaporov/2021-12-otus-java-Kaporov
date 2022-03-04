package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorUpperField10 implements Processor {

    @Override
    public Message process(Message message) {
        return message.toBuilder().field4(message.getField10().toUpperCase()).build();
    }

    @Override
    public Message swapField11AndField13(Message message) {
        return null;
    }

    @Override
    public void createThrowExceptionInAnEventSecond() {

    }
}
