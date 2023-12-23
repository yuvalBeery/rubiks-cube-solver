package data_structures;


import java.io.Serializable;
import java.util.Iterator;

public class List<T> implements Iterable<T>, Serializable {
    /**
     * The default size of the list
     */
    private static final int LIST_DEFAULT_SIZE = 10;
    /**
     * The dynamic array
     */
    private T[] arr;
    /**
     * The index of the next object in the array
     */
    private int next;

    /**
     * Create a new empty List with the default size.
     */
    public List() {
        arr = (T[]) new Object[LIST_DEFAULT_SIZE];
        next = 0;
    }
    /**
     * Create a new empty List with the default size.
     */
    public List(int size) {
        arr = (T[]) new Object[size];
        next = 0;
    }
    /**
     * Create a new list from the specified array.
     * @param values the values to put in the list.
     */
    public List(T[] values) {
        arr = (T[]) new Object[values.length];
        for (T value : values)
            add(value);
    }

    /**
     * Get the number of elements in the list
     * @return The number of elements in the list.
     */
    public int size() {
        return next;
    }

    /**
     * Checks if the list is empty
     * @return True if the list has no elements, otherwise false.
     */
    public boolean isEmpty() {
        return next == 0;
    }

    /**
     * Add a new value to the next free place in the list
     * if the list is too small, the array will double it size.
     * @param value The value to add to the list.
     */
    public void add(T value) {
        if (next < arr.length)
            arr[next++] = value;
        else {
            T[] newArr = (T[]) new Object[(int)(arr.length*2)];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            newArr[next++] = value;
            arr = newArr;
        }
    }

    /**
     * Get the value from the specified index.
     * @param index The index of the value
     * @return The value in the specified index.
     */
    public T get(int index) {
        if (index >= next || isEmpty())
            return null;
        return arr[index];
    }

    /**
     * Adds all the elements from another list to the end of this list
     * @param list the values to add.
     */
    public void addAll(List<T> list) {
        if (list == null)
            return;
        for (Object value : list) {
            add((T) value);
        }
    }

    /**
     * Return and remove from the list the move in the specified index
     * @param index The index of the desired move
     * @return The move in the specified index.
     */
    public T remove(int index) {
        if (index >= next)
            return null;
        T value = arr[index];
        System.arraycopy(arr, index + 1, arr, index, next - index - 1);
        arr[next--] = null;
        return value;
    }

    /**
     * Reverse the orders of the elements in the list
     */
    public void reverse() {
        T value;
        for (int i = size() - 2; i >= 0; i--) {
            value = remove(i);
            add(value);
        }
    }

    /**
     * Deeply clone a data_structures.List object.
     * @return A new instance of a data_structures.List that is deeply equal to this list
     */
    @Override
    public List<T> clone() {
        List<T> cloned = (next < LIST_DEFAULT_SIZE) ? new List<>() : new List<>(next);
        cloned.next = next;
        if (next >= 0)
            System.arraycopy(arr, 0, cloned.arr, 0, next);
        return cloned;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        String str = "[ ";
        for (int i = 0; i < next - 1; i++) {
            str += arr[i] + ", ";
        }
        return str + arr[next - 1] + " ]";
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(arr, next);
    }
}
