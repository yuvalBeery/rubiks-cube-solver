package enums;

import data_structures.Map;
import interfaces.Hashable;

/**
 * Enum that represents all the possible moves on the cube
 * @author yuvalBeery
 */
public enum Move implements Hashable {
    U("U"),
    UPRIME("U'"),
    U2("U2"),
    D("D"),
    DPRIME("D'"),
    D2("D2"),
    R("R"),
    RPRIME("R'"),
    R2("R2"),
    L("L"),
    LPRIME("L'"),
    L2("L2"),
    F("F"),
    FPRIME("F'"),
    F2("F2"),
    B("B"),
    BPRIME("B'"),
    B2("B2");

    /**
     * the name of the move
     */
    private String name;

    Move(String name) {
        this.name = name;
    }

    /**
     * @return the name of the move;
     */
    public String getName() {
        return name;
    }

    @Override
    public int hash() {
        return ordinal();
    }


    @Override
    public String toString() {
        return this.name;
    }
}
