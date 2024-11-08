package com.company.thread.excutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static com.company.util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {

        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            // 구현체로 캐스팅해야 쓸 수 있는 메서드들
            int pool = poolExecutor.getPoolSize();
            int active = poolExecutor.getActiveCount();
            int queued = poolExecutor.getQueue().size();
            long completedTaskCount = poolExecutor.getCompletedTaskCount();
            log("[pool=" + pool + ", active=" + active + ", queuedTasks=" + queued +
                    ", completedTaskCount=" + completedTaskCount + "]");
        } else {
            log(executorService);
        }
    }
}
