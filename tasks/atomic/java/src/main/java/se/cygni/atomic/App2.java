package se.cygni.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App2 {

    static int i = 0;

    public static void main(String[] args) throws Exception {
        final int threadCount = 10;
        final int loopsPerThread = 100000;

        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int j = 0; j < threadCount; j++) {
            executorService.submit(() -> {
                for (int j1 = 0; j1 < loopsPerThread; j1++) {
                    i += 1;
                }
            });
        }

        executorService.shutdown();
        final boolean success = executorService.awaitTermination(1, TimeUnit.SECONDS);
        if (!success) {
            throw new RuntimeException("Timeout waiting for executor service to shut down");
        }

        System.out.printf("Result of %s*%s increments: %d", threadCount, loopsPerThread, i);
    }
}
