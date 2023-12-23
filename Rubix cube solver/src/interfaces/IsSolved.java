package interfaces;

import data_structures.Cube;

/**
 * Interface to check if the cube is solved
 */
public interface IsSolved {
    /**
     * Check if a part of the cube is solved.
     * @param c The cube to be checked.
     * @return true if the part of te cube is solved, false if not.
     */
    boolean isSolved(Cube c);
}
