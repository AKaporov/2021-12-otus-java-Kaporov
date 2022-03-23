package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.listener.ListenerPrinterConsole;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.time.LocalDateTime;
import java.util.*;

public class HistoryListener implements Listener, HistoryReader {

        private final Map<Long, Deque<Message>> historyMessage = new HashMap<>();
//    private final Deque<Memento> historyMessage = new ArrayDeque<>();

//    private DateTimeProvider dateTimeProvider;


    private final List<Message> list = new ArrayList<>();


    @Override
    public void onUpdated(Message msg) {
//        throw new UnsupportedOperationException();
        new ListenerPrinterConsole().onUpdated(msg);

        addMessageToHistory(msg);
    }

    private void addMessageToHistory(Message msg) {
        Message copy = getCopyMessage(msg);
        System.out.println("copy = " + copy);

        Deque<Message> newMessaged = new LinkedList<>();
        Deque<Message> oldMessages = historyMessage.get(msg.getId());
        if (oldMessages != null) {
            newMessaged.addAll(oldMessages);
        }
        newMessaged.push(copy);

        historyMessage.put(msg.getId(), newMessaged);
//        historyMessage.push(new Memento(msg, LocalDateTime.now()));
    }

    private Message getCopyMessage(Message msg) {
        ObjectForMessage f13 = getDeepCopyObjectForMessage(msg.getField13());

        return msg.toBuilder()
                .field1(msg.getField1())
                .field2(msg.getField2())
                .field3(msg.getField3())
                .field4(msg.getField4())
                .field5(msg.getField5())
                .field6(msg.getField6())
                .field7(msg.getField7())
                .field8(msg.getField8())
                .field9(msg.getField9())
                .field10(msg.getField10())
                .field11(msg.getField11())
                .field12(msg.getField12())
                .field13(f13)
                .build();
    }

    private ObjectForMessage getDeepCopyObjectForMessage(ObjectForMessage f13) {
        ObjectForMessage result = new ObjectForMessage();
        List<String> objects = new ArrayList<>(f13.getData().size());

        for (String s : f13.getData()) {
            objects.add(s);
        }
        result.setData(objects);
        return result;
    }

    @Override
    public Optional<Message> findMessageById(long id) {
//        throw new UnsupportedOperationException();
        System.out.println("id = " + id);

        Queue<Message> messages = historyMessage.get(id);
        return Optional.ofNullable(messages.poll());

//        Memento poll = historyMessage.pop();
//        return Optional.ofNullable(poll.msg());
    }
}
