package solve;

import data_structures.Cube;
import enums.Corner;
import enums.Edge;
import enums.CubeColor;
import interfaces.IsSolved;
import utils.Maps;

/**
 * Checks if the Eo line is solved.
 */
public class IsEoLineSolved implements IsSolved {
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

        //corner permutation
        for (int i = 0; i < Corner.values().length; i++) {
            significantColor = Maps.significantCornerColorMap.get(Corner.values()[i]).apply(c);
            permutation = Maps.cornerPermutationMap.get(c.corners[i]).get(significantColor);
            if (permutation != 0)
                return false;
        }

        //checks if the ud line is solved
        return c.edges[0] == Edge.DF && c.edges[2] == Edge.DB;
    }
}
