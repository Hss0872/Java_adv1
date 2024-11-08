package com.company.thread.excutor.future;

import java.util.Random;
import java.util.concurrent.*;

import static com.company.util.MyLogger.log;
import static com.company.util.ThreadUtils.sleep;

public class CallableMainV1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer> future = es.submit(new MyCallable());
        Integer result = future.get();
        log("result = " + result);
        es.close();
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            log("Callable 시작");
            sleep(1000);
            int value = new Random().nextInt(10);
            log("value = " + value);
            log("Callable 완료");
            return value ;
        }
    }
}