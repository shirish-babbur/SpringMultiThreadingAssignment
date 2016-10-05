package org.webonise.springdiningphilosopher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class);
        DiningPhilosopher diningPhilosopher = context.getBean(DiningPhilosopher.class);
        diningPhilosopher.startApplication();
    }
}
