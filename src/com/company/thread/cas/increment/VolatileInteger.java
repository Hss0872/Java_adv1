package com.company.thread.cas.increment;

public class VolatileInteger implements IncrementInteger {

    volatile int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
