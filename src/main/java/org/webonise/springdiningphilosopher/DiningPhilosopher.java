package org.webonise.springdiningphilosopher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

@Component
public class DiningPhilosopher {
    // The number of philosophers
    public static final int NUM_PHILOSOPHERS = 5;
    @Autowired
    @Qualifier("Carray")
    private Lock[] chopsticks;
    @Autowired
    @Qualifier("Parray")
    private Philosopher[] philosophers;

    public void startApplication() {
        // Create the philosophers and start each running in its own thread.
        for (int i = 0; i < DiningPhilosopher.NUM_PHILOSOPHERS; i++) {
            philosophers[i].setId(i);
            philosophers[i].setLeftChopstick(chopsticks[i]);
            philosophers[i].setRightChopstick(chopsticks[(i + 1) % NUM_PHILOSOPHERS]);
            Thread philosopher = new Thread(philosophers[i]);
            philosopher.start();
        }
    }
}
