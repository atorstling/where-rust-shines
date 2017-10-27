package se.cygni.rc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.*;

public class App {

    static class User {
        
    }

    static class UserRegistry {
        int totalAge;
    }

    public static void main(String[] args) throws Exception {
        final int elementCount = 100000;

        final MyList<Integer> l = addThreaded(elementCount);

        // Check that size adds up
        final int size = l.getSize();
        if (size != elementCount) {
            final Set<Integer> missingInts = intRange(0, elementCount);
            missingInts.removeAll(Arrays.asList(l.toArray()));
            throw new IllegalStateException(String.format("Wrote %d elements, could only find %d written. Missing: %s", elementCount, size, missingInts));
        } else {
            System.out.println("OK");
        }
    }

    private static MyList<Integer> addThreaded(int elementCount) throws InterruptedException, ExecutionException, TimeoutException {
        // Create N tasks to add one element each to the list
        final ExecutorService pool = Executors.newFixedThreadPool(10);
        final ArrayList<Future> futures = new ArrayList<>();
        final MyList<Integer> l = new MyList<>();
        for (int i = 0; i < elementCount; i++) {
            int finalI = i;
            final Future<?> f = pool.submit(() -> l.add(finalI));
            futures.add(f);
        }
        for (Future future : futures) {
            future.get(1, TimeUnit.SECONDS);
        }
        pool.shutdown();
        final boolean success = pool.awaitTermination(1, TimeUnit.SECONDS);
        if (!success) {
            throw new IllegalStateException("Timeout waiting for thread pool to terminate");
        }
        return l;
    }

    private static Set<Integer> intRange(int from, int to) {
        final LinkedHashSet<Integer> ints = new LinkedHashSet<>(to-from);
        for (int i = from; i < to; i++) {
            ints.add(i);
        }
        return ints;
    }
}
