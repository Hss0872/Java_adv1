package com.company.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static com.company.util.MyLogger.log;

public class CasMainV2 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        // incrementAndGet 구현
        int resultValue1 = incrementAndGet(atomicInteger);
        System.out.println("resultValue1 = " + resultValue1);

        int resultValue2 = incrementAndGet(atomicInteger);
        System.out.println("resultValue2 = " + resultValue2);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get();
            log("getValue = " + getValue);
            // thread1 : 0 일 때 다른 스레드에서 1로 값을 바꾼다면 false가 되고 다시 반복문 돈다
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result = " + result);
            log("getValue = " + getValue);
        } while (!result);

        return getValue + 1;
    }
}
