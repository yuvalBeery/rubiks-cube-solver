package solve;

import data_structures.Cube;
import enums.Corner;
import enums.Edge;
import enums.CubeColor;
import interfaces.IsSolved;
import utils.Maps;

import java.util.concurrent.RunnableFuture;

/**
 * Checks if the rl slice is solved
 */
public class IsRlSliceSolved implements IsSolved {
    private static int[] evenCornersPermutations = new int[] {0, 2, 0, 2, 2, 0, 2, 0};
    private static int[] oddCornersPermutations = new int[] {2, 0, 2, 0, 0, 2, 0, 2};

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
            if (c.corners[i].ordinal() % 2 == 0) {
                if (c.corners[i].ordinal() < 4 && permutation != evenCornersPermutations[i])
                    return false;
                else if (c.corners[i].ordinal() > 3 && permutation != oddCornersPermutations[i])
                    return false;
            }
            else {
                if (c.corners[i].ordinal() < 4 && permutation != oddCornersPermutations[i])
                    return false;
                else if (c.corners[i].ordinal() > 3 && permutation != evenCornersPermutations[i])
                    return false;
            }
        }

        //checks if all the rl slice edges are in the rl slice
        for (int i = 8; i % 12 != 4; i += 2) {
            if (c.edges[i % 12] != Edge.DF && c.edges[i % 12] != Edge.DB && c.edges[i % 12] != Edge.UF && c.edges[i % 12] != Edge.UB)
                return false;
        }
        return true;
    }
}
