package org.example;

public class SyncCounter {
    private int c;

    public int value() {
        return c;
    }

    public SyncCounter() {
        c = 0;
    }

    public synchronized void inc() { // когда будем изменять значения, доступ к этому объекту будет иметь только один поток
        c++;
    }
}
