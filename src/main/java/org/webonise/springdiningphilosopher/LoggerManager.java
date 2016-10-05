package org.webonise.springdiningphilosopher;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerManager {
    private ConsoleHandler consoleHandler = new ConsoleHandler();

    protected Logger getLOGGER(Logger LOGGER) {
        consoleHandler.setLevel(Level.ALL);
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(consoleHandler);
        return LOGGER;
    }
}
