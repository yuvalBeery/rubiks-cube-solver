package solve;

import data_structures.Cube;
import enums.Corner;
import enums.Edge;
import enums.CubeColor;
import interfaces.IsSolved;
import utils.Maps;

/**
 * Checks if the r block is solved.
 */
public class IsRBlockSolved implements IsSolved {
    @Override
    public boolean isSolved(Cube c) {
        if (c.corners[0] != Corner.DRF || c.corners[1] != Corner.DRB || c.edges[1] != Edge.DR || c.edges[4] != Edge.RF || c.edges[5] != Edge.RB)
            return false;

        CubeColor drfSignificantColor = Maps.significantCornerColorMap.get(Corner.DRF).apply(c);
        int drfPermutation = Maps.cornerPermutationMap.get(c.corners[0]).get(drfSignificantColor);

        CubeColor drbSignificantColor = Maps.significantCornerColorMap.get(Corner.DRB).apply(c);
        int drbPermutation = Maps.cornerPermutationMap.get(c.corners[1]).get(drbSignificantColor);

        CubeColor drSignificantColor = Maps.significantEdgeColorMap.get(Edge.DR).apply(c);
        int drPermutation = Maps.edgePermutationMap.get(c.edges[1]).get(drSignificantColor);

        CubeColor rfSignificantColor = Maps.significantEdgeColorMap.get(Edge.RF).apply(c);
        int rfPermutation = Maps.edgePermutationMap.get(c.edges[4]).get(rfSignificantColor);

        CubeColor rbSignificantColor = Maps.significantEdgeColorMap.get(Edge.RB).apply(c);
        int rbPermutation = Maps.edgePermutationMap.get(c.edges[5]).get(rbSignificantColor);

        return drfPermutation == 0 && drbPermutation == 0 && rfPermutation == 0 && drPermutation == 0 && rbPermutation == 0;
    }
}
