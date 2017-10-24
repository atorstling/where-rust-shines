package se.cygni.rc;

import java.util.Arrays;

/**
 * A list backed by an array. Will start with a 1 element capacity,
 * and double it's capacity every time more space is required.
 */
class MyList<T> {
    private int size;
    private T[] buffer;

    public MyList() {
        size = 0;
        buffer = (T[]) new Object[1];
    }

    void add(T t) {
        final int newSize = size + 1;
        if (newSize > buffer.length) {
            resize();
        }
        buffer[newSize - 1] = t;
        size = newSize;
    }

    private void resize() {
        T[] newElems = (T[]) new Object[buffer.length * 2];
        System.arraycopy(buffer, 0, newElems, 0, size);
        buffer = newElems;
    }

    T[] toArray() {
        final T[] array = (T[]) new Object[size];
        System.arraycopy(buffer, 0, array, 0, size);
        return array;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }
}
