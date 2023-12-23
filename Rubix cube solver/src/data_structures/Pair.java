package data_structures;

import java.io.Serializable;

/**
 * container class of two objects
 * @param <V> first object type
 * @param <K> second object type
 */
public class Pair<V, K> implements Serializable {
    private final V v;
    private final K k;

    /**
     * creates a new instance of a pair object
     * @param v first object
     * @param k second object
     */
    public Pair(V v, K k) {
        this.v = v;
        this.k = k;
    }

    /**
     * @return the first object
     */
    public V getV() {
        return v;
    }

    /**
     * @return the second object
     */
    public K getK() {
        return k;
    }
}
