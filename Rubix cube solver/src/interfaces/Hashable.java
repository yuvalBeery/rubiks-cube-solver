package interfaces;

/**
 * Interface for enabling hashing of objects to an array.
 */
public interface Hashable {
    /**
     * Calculate the object index in the array.
     * @return The index of the object in the array
     */
    int hash();
}
