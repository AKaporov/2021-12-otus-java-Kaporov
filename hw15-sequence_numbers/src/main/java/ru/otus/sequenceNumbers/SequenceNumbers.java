package ru.otus.sequenceNumbers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class SequenceNumbers {
    private static final Logger logger = LoggerFactory.getLogger(SequenceNumbers.class);
    private static final int LIMIT = 10;
    private static final int[] ARRAY = IntStream.rangeClosed(1, LIMIT).toArray(); // From 1 to 10 //{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private String lastThreadName = "";
    private boolean toUp = true;

    private synchronized void action(int i) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                var currentThreadName = Thread.currentThread().getName();

                //spurious wakeup https://en.wikipedia.org/wiki/Spurious_wakeup
                //поэтому не if
                while (lastThreadName == currentThreadName) {
                    this.wait();
                }

                logger.info("ARRAY[" + i + "] = " + ARRAY[i]);
                lastThreadName = currentThreadName;
                i = getNextItem(i);

                sleep();
                notifyAll();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private int getNextItem(int i) {
        if (i == LIMIT - 1) {
            toUp = false;
        }

        if (i == 0) {
            toUp = true;
        }

        if (toUp) {
            if (i < LIMIT) {
                i++;
            } else {
                i--;
            }
        } else {
            if (i >= ARRAY[0]) {
                i--;
            } else {
                i++;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        SequenceNumbers sn = new SequenceNumbers();

        new Thread(() -> sn.action(0)).start();
        new Thread(() -> sn.action(0)).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
