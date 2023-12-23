package solve;

import data_structures.Cube;
import enums.Corner;
import enums.Edge;
import enums.CubeColor;
import interfaces.IsSolved;
import utils.Maps;

/**
 * Checks if the fb slice is solved
 */
public class IsFbSliceSolved implements IsSolved {
    private static int[] usEdgesPermutation = new int[] {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0};
    private static int[] usSliceEdgesPermutation = new int[] {1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1};

    private static int[] evenCornersPermutations = new int[] {0, 1, 0, 1, 1, 0, 1, 0};
    private static int[] oddCornersPermutations = new int[] {1, 0, 1, 0, 0, 1, 0, 1};

    @Override
    public boolean isSolved(Cube c) {
        CubeColor significantColor;
        int permutation;

        //esdges orientation
        for (int i = 0; i < Edge.values().length; i++) {
            significantColor = Maps.significantEdgeColorMap.get(Edge.values()[i]).apply(c);
            permutation = Maps.edgePermutationMap.get(c.edges[i]).get(significantColor);
            if (((c.edges[i].ordinal() < 4 || c.edges[i].ordinal() > 7) && permutation != usEdgesPermutation[i]) ||
                    ((c.edges[i].ordinal() > 3 && c.edges[i].ordinal() < 8) && permutation != usSliceEdgesPermutation[i]))
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

        //checks if all the fb slice edges are in the fb slice
        for (int i = 9; i % 12 != 5; i += 2) {
            if (c.edges[i % 12] != Edge.DR && c.edges[i % 12] != Edge.DL && c.edges[i % 12] != Edge.UR && c.edges[i % 12] != Edge.UL)
                return false;
        }
        return true;
    }
}
