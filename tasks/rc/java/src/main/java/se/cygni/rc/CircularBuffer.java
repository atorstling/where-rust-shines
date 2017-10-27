package se.cygni.rc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// Not solved by Rust (sync several fields)
class CircularBuffer<T> {
    private final int capacity;
    private int writeLocation;
    private T[] buffer;

    public CircularBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalStateException("Capacity must be > 0, was " + capacity);
        }
        this.capacity = capacity;
        this.writeLocation = 0;
        buffer = (T[]) new Object[0];
    }

    T add(T t) {
        if (buffer.length < capacity) {
            final T[] newBuffer = (T[]) new Object[writeLocation + 1];
            System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
            buffer = newBuffer;
        }
        T previous = buffer[writeLocation];
        buffer[writeLocation] = t;
        writeLocation = (writeLocation + 1) % capacity;
        return previous;
    }

    T[] toArray() {
        final T[] bufferCopy = (T[]) new Object[buffer.length];
        System.arraycopy(buffer, 0, bufferCopy, 0, buffer.length);
        return bufferCopy;
    }
    
    public String toString() {
        return Arrays.toString(toArray());
    }

    public static void main(String[] args) throws Exception {
        final CircularBuffer<Integer> cb = new CircularBuffer<>(3);
        cb.add(1);
        cb.add(2);
        cb.add(3);
        cb.add(4);
        System.out.println(cb);

        final ExecutorService pool = Executors.newFixedThreadPool(10);
        final ArrayList<Future> futures = new ArrayList<>();
        final CircularBuffer<Integer> l = new CircularBuffer<Integer>(10);
        final int nElems = 100;
        for (int i = 0; i < nElems; i++) {
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
        System.out.println(l);
    }

}
