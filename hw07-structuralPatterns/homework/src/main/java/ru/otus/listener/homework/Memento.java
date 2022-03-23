package ru.otus.listener.homework;

import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

record Memento(Message msg, LocalDateTime createdAt) {
    Memento(Message msg, LocalDateTime createdAt) {
        ObjectForMessage f13 = getDeepCopyObjectForMessage(msg.getField13());
        this.msg = msg.toBuilder()
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
        this.createdAt = createdAt;
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
}
