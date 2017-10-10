package se.cygni.atomic;

import java.util.concurrent.*;

public class App {

    static int i = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        final int nThreads = 10;

        final CountDownLatch allStarted = new CountDownLatch(nThreads);
        final CountDownLatch mayBegin = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(nThreads);
        for (int j = 0; j < nThreads; j++) {
            new Thread(() -> {
                try {
                    allStarted.countDown();
                    mayBegin.await(1, TimeUnit.SECONDS);
                    for (int j1 = 0; j1 < 1000; j1++) {
                        i += 1;
                    }
                    done.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        allStarted.await(1, TimeUnit.SECONDS);
        mayBegin.countDown();
        final boolean success = done.await(5, TimeUnit.SECONDS);
        if (!success) {
            throw new RuntimeException("Timeout while waiting for threads to terminate");
        }
        System.out.println("Result of 10*1000 additions: " + i);
    }
}
