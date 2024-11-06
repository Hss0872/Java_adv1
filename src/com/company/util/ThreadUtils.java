package com.company.util;


import static com.company.util.MyLogger.log;

public abstract class ThreadUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log("인터럽트 발생");
            throw new RuntimeException(e);
        }
    }
}