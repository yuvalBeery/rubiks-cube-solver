package data_structures;

import interfaces.Hashable;

import java.io.Serializable;

/**
 * Class that maps a key element with a value element,
 * and saves all the key and the values that was mapped.
 * @param <K> the type of the key element.
 * @param <V> the type of the value element
 */
public class Map<K extends Hashable, V> implements Serializable {
    /**
     * The default size of the array.
     */
    private static final int MAP_DEFAULT_SIZE = 10;

    private final int size;

    /**
     * array of lists that saves all the keys and values that was mapped.
     */
    private List<Pair<V, K>>[] arr;

    /**
     * Creates a new empty Map with the default size.
     */
    public Map() {
        size = MAP_DEFAULT_SIZE;
        arr = (List<Pair<V, K>>[]) new List[size];
    }

    /**
     * Creates a new empty Map with a given size.
     */
    public Map(int size) {
        this.size = size;
        arr = (List<Pair<V, K>>[]) new List[size];
    }

    public int getSize() {
        return size;
    }

    public List<Pair<V, K>>[] getArr() {
        return arr;
    }

    public void setArr(List<Pair<V, K>>[] arr) {
        this.arr = arr;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * @param key Key with which the specified value is to be associated
     * @param value Value to be associated with the specified key
     */
    public void put(K key, V value) {
        if (arr[key.hash() % size] == null)
            arr[key.hash() % size] = new List<>();
        arr[key.hash() % size].add(new Pair<>(value, key));
    }

    /**
     * Gets the value associated with the specified key.
     * @param key The key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    public V get(K key) {
        int hash = key.hash();
        if (arr[hash % size] == null)
            return null;
        for (Pair<V, K> p : arr[hash % size]) {
            if (p.getK().equals(key))
                return p.getV();
        }
        return null;
    }

    /**
     * Put in the map a new element with a specific value associated with the specific key,
     * if the key is not already in the map.
     * @param key Key with which the specified value is to be associated.
     * @param value Value to be associated with the specified key
     * @return True if the value was successfully added to the map, otherwise false.
     */
    public boolean putIfAbsent(K key, V value) {
        if (containsKey(key))
            return false;
        put(key, value);
        return true;
    }

    /**
     * Checks if there is mapping for the specified key.
     * @param key The key to check if there is mapping for.
     * @return True if the there is a mapping for the specified key, false if not.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }
}
