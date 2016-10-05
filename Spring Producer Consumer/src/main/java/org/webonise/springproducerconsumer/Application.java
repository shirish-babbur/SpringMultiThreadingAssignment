package org.webonise.springproducerconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.logging.Logger;

@Component
public class Application {
    public static final int NO_OF_PRODUCTS = 7;
    public static final int size = 4;
    private static Logger LOGGER = Logger.getLogger(Application.class.getName());
    public static ArrayList<Integer> sharedItemList = new ArrayList<Integer>();
    @Autowired
    private LoggerManager loggerManager;
    @Autowired
    @Lazy
    private Producer producer;
    @Autowired
    @Lazy
    private Consumer consumer;

    public void startApplication() {
        LOGGER = loggerManager.getLOGGER(LOGGER);
        // Producer Thread created.
        Thread producerThread = new Thread(producer, "ProducerThread");
        // Consumer Thread created.
        Thread consumerThread = new Thread(consumer, "ConsumerThread");
        // Both Threads Started.
        consumerThread.start();
        producerThread.start();
        try {
            consumerThread.join();
            producerThread.join();
        } catch (InterruptedException e) {
            LOGGER.severe(e.getMessage());
        }
        LOGGER.info("Joined both the threads Producer and Consumer to Main Thread.\nExiting");
    }
}
