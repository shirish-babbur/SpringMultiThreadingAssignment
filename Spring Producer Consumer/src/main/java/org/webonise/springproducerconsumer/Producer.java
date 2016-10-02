package org.webonise.springproducerconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.logging.Logger;

@Component
@Lazy
public class Producer implements Runnable {
    private ArrayList<Integer> sharedItemList;
    private static Logger LOGGER = Logger.getLogger(Producer.class.getName());
    @Autowired
    private LoggerManager loggerManager;
    @Autowired
    private ContentViewer contentViewer;

    @PostConstruct
    public void init() {
        this.sharedItemList = Application.sharedItemList;
        LOGGER = loggerManager.getLOGGER(LOGGER);
    }

    @Override
    public void run() {
        for (int i = 0; i < Application.NO_OF_PRODUCTS; i++) {
            try {
                // produces the Product.
                produce(i);
                synchronized (sharedItemList) {
                    sharedItemList.notifyAll();
                    contentViewer.display();
                }
            } catch (Exception e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    private void produce(int productNumber) throws Exception {
        synchronized (sharedItemList) {
            // Used Synchronized block to achieve mutual exclusion.
            // Wait if size of sharedItemList is full.
            while (sharedItemList.size() == Application.size) {
                LOGGER.info("Thread " + Thread.currentThread().getName() + " is Waiting SharedItem is full(size:" + sharedItemList.size() + ")");
                sharedItemList.wait();
            }
            // Otherwise add product and notify all waiting consumers.
            sharedItemList.add(productNumber);
            LOGGER.info("Produced:" + productNumber);
        }
    }
}
