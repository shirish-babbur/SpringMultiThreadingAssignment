package org.webonise.springproducerconsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.logging.ConsoleHandler;

@Component
public class AppConfig {

    @Bean
    public ConsoleHandler getConsoleHandler() {
        return new ConsoleHandler();
    }
}
