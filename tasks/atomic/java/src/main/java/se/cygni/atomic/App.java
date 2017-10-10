package se.cygni.atomic;

import java.util.concurrent.*;

public class App {

    static int i = 0;

    public static void main(String[] args) throws Exception {
        final int nThreads = 10;

        final CountDownLatch allStarted = new CountDownLatch(nThreads);
        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(nThreads);
        for (int j = 0; j < nThreads; j++) {
            final Thread t = new Thread(() -> {
                try {
                    allStarted.countDown();
                    await(startSignal, "Timeout waiting for start signal");
                    for (int j1 = 0; j1 < 1000; j1++) {
                        i += 1;
                    }
                    done.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
        }
        await(allStarted, "Timeout waiting for threads to start");
        startSignal.countDown();
        await(done, "Timeout while waiting for threads to terminate");
        System.out.println("Result of 10*1000 additions: " + i);
    }

    private static void await(CountDownLatch latch, String message) throws InterruptedException {
        final boolean success = latch.await(1, TimeUnit.SECONDS);
        if (!success) {
            throw new RuntimeException(message);
        }
    }
}
