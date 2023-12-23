package solve;

import data_structures.Cube;
import interfaces.IsSolved;

/**
 * Checks if the cube is solved
 */
public class IsCubeSolved implements IsSolved {
    @Override
    public boolean isSolved(Cube c) {
        return c.first3Faces.equals("000000001111111122222222") && c.last3Faces.equals("333333334444444455555555");
    }
}
