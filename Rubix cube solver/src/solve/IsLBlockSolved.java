package solve;

import data_structures.Cube;
import enums.Corner;
import enums.Edge;
import enums.CubeColor;
import interfaces.IsSolved;
import utils.Maps;

/**
 * Checks if the l block is solved
 */
public class IsLBlockSolved implements IsSolved {
    @Override
    public boolean isSolved(Cube c) {
        if (c.corners[2] != Corner.DLB || c.corners[3] != Corner.DLF || c.edges[3] != Edge.DL || c.edges[6] != Edge.LB || c.edges[7] != Edge.LF)
            return false;

        CubeColor dlbSignificantColor = Maps.significantCornerColorMap.get(Corner.DLB).apply(c);
        int dlbPermutation = Maps.cornerPermutationMap.get(c.corners[2]).get(dlbSignificantColor);

        CubeColor dlfSignificantColor = Maps.significantCornerColorMap.get(Corner.DLF).apply(c);
        int dlfPermutation = Maps.cornerPermutationMap.get(c.corners[3]).get(dlfSignificantColor);

        CubeColor dlSignificantColor = Maps.significantEdgeColorMap.get(Edge.DL).apply(c);
        int dlPermutation = Maps.edgePermutationMap.get(c.edges[3]).get(dlSignificantColor);

        CubeColor lbSignificantColor = Maps.significantEdgeColorMap.get(Edge.LB).apply(c);
        int lbPermutation = Maps.edgePermutationMap.get(c.edges[6]).get(lbSignificantColor);

        CubeColor lfSignificantColor = Maps.significantEdgeColorMap.get(Edge.LF).apply(c);
        int lfPermutation = Maps.edgePermutationMap.get(c.edges[7]).get(lfSignificantColor);

        return dlbPermutation == 0 && dlfPermutation == 0 && dlPermutation == 0 && lbPermutation == 0 && lfPermutation == 0;
    }
}
