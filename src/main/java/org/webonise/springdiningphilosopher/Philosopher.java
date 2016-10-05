package org.webonise.springdiningphilosopher;

import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

@Component
public class Philosopher implements Runnable {
    // The philosopher's unique id
    private int id;
    // The chopsticks this philosopher may use
    private Lock leftChopstick;
    private Lock rightChopstick;
    private static Logger LOGGER = Logger.getLogger(DiningPhilosopher.class.getName());
    private LoggerManager loggerManager = new LoggerManager();


    public void setId(int id) {
        this.id = id;
    }

    public void setLeftChopstick(Lock leftChopstick) {
        this.leftChopstick = leftChopstick;
    }

    public void setRightChopstick(Lock rightChopstick) {
        this.rightChopstick = rightChopstick;
    }

    // Repeatedly think, pick up chopsticks, eat and put down chopsticks
    public void run() {
        try {
            LOGGER = loggerManager.getLOGGER(LOGGER);
            think();
            pickUpLeftChopstick();
            pickUpRightChopstick();
            eat();
            putDownChopsticks();

        } catch (InterruptedException e) {
            LOGGER.info("Philosopher " + id + " was interrupted.\n");
        }
    }

    public void dosomething() {
        System.out.println("ID:" + id);
    }

    private void think() throws InterruptedException {
        LOGGER.info("Philosopher " + id + " is thinking.\n");
        Thread.sleep(2000);
    }

    // Locks the left chopstick to signify that this philosopher is holding it
    private void pickUpLeftChopstick() {
        leftChopstick.lock();
        LOGGER.info("Philosopher " + id + " is holding left chopstick.\n");
    }

    // Locks the right chopstick to signify that this philosopher is holding it
    private void pickUpRightChopstick() {
        rightChopstick.lock();
        LOGGER.info("Philosopher " + id + " is holding right chopstick.\n");
    }

    private void eat() throws InterruptedException {
        LOGGER.info("Philosopher " + id + " is eating.\n");
        Thread.sleep(2000);
    }

    // Releases the locks on both chopsticks to model putting them down so the
    // other philosophers can use them.
    private void putDownChopsticks() {
        leftChopstick.unlock();
        LOGGER.info("Philosopher " + id + " is released left chopstick.\n");
        rightChopstick.unlock();
        LOGGER.info("Philosopher " + id + " is released right chopstick.\n");
    }
}
