package org.example;

public class MyWaitNotify {
    // если надо использовать чёткую последовательность
    // они ждут пока текущая буква не сменится
    private final Object monitor = new Object();
    private char currentLetter = 'A';

    public static void main(String[] args) {
        MyWaitNotify waitNotifyApp = new MyWaitNotify();
        new Thread(() -> waitNotifyApp.printA()).start();
        new Thread(() -> waitNotifyApp.printB()).start();
    }
    private void printA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        monitor.wait();
                    }
                    System.out.println("A");
                    currentLetter = 'B';
                    monitor.notifyAll();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void printB() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        monitor.wait();
                    }
                    System.out.println("B");
                    currentLetter = 'A';
                    monitor.notifyAll();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
