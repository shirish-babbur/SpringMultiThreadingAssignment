package org.webonise.springproducerconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class LoggerManager {
    @Autowired
    private ConsoleHandler consoleHandler;

    protected Logger getLOGGER(Logger LOGGER) {
        consoleHandler.setLevel(Level.ALL);
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(consoleHandler);
        return LOGGER;
    }
}
