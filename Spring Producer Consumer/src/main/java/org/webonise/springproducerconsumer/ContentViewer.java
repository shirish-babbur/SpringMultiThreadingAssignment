package org.webonise.springproducerconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

@Component
@Lazy
public class ContentViewer {
    private ArrayList<Integer> sharedItemList;
    private static Logger LOGGER = Logger.getLogger(ContentViewer.class.getName());
    @Autowired
    private LoggerManager loggerManager;

    @PostConstruct
    public void init() {
        LOGGER = loggerManager.getLOGGER(LOGGER);
        this.sharedItemList = Application.sharedItemList;
    }

    public void display() {
        synchronized (sharedItemList) {
            Iterator<Integer> iterator = sharedItemList.iterator();
            while (iterator.hasNext())
                LOGGER.info(iterator.next() + ",");
            System.out.println();
        }
    }
}
