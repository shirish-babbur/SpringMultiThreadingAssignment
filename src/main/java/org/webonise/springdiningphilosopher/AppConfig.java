package org.webonise.springdiningphilosopher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Configuration
public class AppConfig {

    @Bean("Parray")
    public Philosopher[] getPhilosopher() {
        Philosopher philosophers[] = new Philosopher[DiningPhilosopher.NUM_PHILOSOPHERS];
        for (int i = 0; i < DiningPhilosopher.NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher();
        }
        return philosophers;
    }

    @Bean("Carray")
    public Lock[] getChopsticks() {
        Lock[] chopsticks = new ReentrantLock[DiningPhilosopher.NUM_PHILOSOPHERS];
        for (int i = 0; i < DiningPhilosopher.NUM_PHILOSOPHERS; i++) {
            chopsticks[i] = new ReentrantLock();
        }
        return chopsticks;
    }
}
