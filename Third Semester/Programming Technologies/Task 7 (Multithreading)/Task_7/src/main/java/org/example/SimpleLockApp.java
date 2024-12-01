package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleLockApp {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                System.err.println("T-BEFORE-LOCK-FIRST");
                lock.lock();
                System.err.println("T-GET-LOCK-FIRST");
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finally {
                System.err.println("T-END-FIRST");
                lock.unlock();
            }
        }).start();

        // код не дописан... new Thread(() -> { System.err.println("T-BEGIN-SECOND"); })
    }
}
