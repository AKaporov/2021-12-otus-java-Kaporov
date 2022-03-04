package ru.otus.processor;

import ru.otus.model.Message;

public class LoggerProcessor implements Processor {


    private final Processor processor;

    public LoggerProcessor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Message process(Message message) {
        System.out.println("log processing message:" + message);
        return processor.process(message);
    }

    @Override
    public Message swapField11AndField13(Message message) {
        return null;
    }

    @Override
    public void createThrowExceptionInAnEventSecond() {

    }
}
