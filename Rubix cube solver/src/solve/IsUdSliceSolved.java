package solve;

import data_structures.Cube;
import enums.Corner;
import enums.Edge;
import enums.CubeColor;
import interfaces.IsSolved;
import utils.Maps;

/**
 * Checks if the ud slice is solved.
 */
public class IsUdSliceSolved implements IsSolved {
    @Override
    public boolean isSolved(Cube c) {
        CubeColor significantColor;
        int permutation;

        //esdges orientation
        for (int i = 0; i < Edge.values().length; i++) {
            significantColor = Maps.significantEdgeColorMap.get(Edge.values()[i]).apply(c);
            permutation = Maps.edgePermutationMap.get(c.edges[i]).get(significantColor);
            if (permutation != 0)
                return false;
        }

        //corner orientation
        for (int i = 0; i < Corner.values().length; i++) {
            significantColor = Maps.significantCornerColorMap.get(Corner.values()[i]).apply(c);
            permutation = Maps.cornerPermutationMap.get(c.corners[i]).get(significantColor);
            if (permutation != 0)
                return false;
        }

        //checks if all the ud slice edges are in the ud slice
        for (int i = 4; i < 8; i++) {
            if (c.edges[i] != Edge.RF && c.edges[i] != Edge.RB && c.edges[i] != Edge.LB && c.edges[i] != Edge.LF)
                return false;
        }
        return true;
    }
}
