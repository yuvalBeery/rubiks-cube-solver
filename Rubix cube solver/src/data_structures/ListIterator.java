package data_structures;

import java.util.Iterator;

/**
 * Costume Iterator class for the class List
 */
public class ListIterator<T> implements Iterator<T> {
    /**
     * the array to iterate
     */
    private T[] arr;

    /**
     * the number of elements to iterate in the array.
     */
    int length;

    /**
     * The next element to iterate
     */
    private int current;

    /**
     * Creates a new ListIterator
     * @param arr the array to iterate
     * @param length the number of elements to iterate in the array
     */
    public ListIterator(T[] arr, int length) {
        this.arr = arr;
        this.length = length;
        current = 0;
    }

    @Override
    public boolean hasNext() {
        return current < length;
    }

    @Override
    public T next() {
        return arr[current++];
    }
}

