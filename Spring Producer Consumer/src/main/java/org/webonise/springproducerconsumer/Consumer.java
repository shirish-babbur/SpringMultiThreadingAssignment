package org.webonise.springproducerconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.logging.Logger;

@Component
@Lazy
public class Consumer implements Runnable {
    private ArrayList<Integer> sharedItemList;
    private int count = 0;
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
        while (count != Application.NO_OF_PRODUCTS) {
            try {
                // Consumer consumes Product.
                LOGGER.info("Consumed:" + consume());
                count++;
                synchronized (sharedItemList) {
                    sharedItemList.notifyAll();
                    contentViewer.display();
                    Thread.sleep(50);
                }
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }
        }
    }

    private int consume() throws Exception {
        synchronized (sharedItemList) {
            // Wait if size of sharedItemList is empty.
            while (sharedItemList.isEmpty()) {
                // Used Synchronized block to achieve mutual exclusion.
                LOGGER.info("Thread " + Thread.currentThread().getName() + " is Waiting SharedItem is Empty.(size:" + sharedItemList.size() + ")");
                sharedItemList.wait();
            }
            // Consumer consumes item and notifies other waiting Threads.
            return sharedItemList.remove(0);
        }
    }
}
