package enums;

import interfaces.Hashable;

/**
 * enum that represent the edges of the rubix cube.
 * each edge is the initials of its full name.
 * for example: DF = down front edge.
 */
public enum Edge implements Hashable {
    /**
     * down front edge
     */
    DF("Down Front"),
    /**
     * down right edge
     */
    DR("Down Right"),
    /**
     * down back edge
     */
    DB("Down Back"),
    /**
     * down left edge
     */
    DL("Down Left"),
    /**
     * right front edge
     */
    RF("Right Front"),
    /**
     * right back edge
     */
    RB("Right Back"),
    /**
     * left back edge
     */
    LB("Left Back"),
    /**
     * left front edge
     */
    LF("Left Front"),
    /**
     * up front edge
     */
    UF("Up Front"),
    /**
     * up right edge
     */
    UR("Up Right"),
    /**
     * up back edge
     */
    UB("Up Back"),
    /**
     * up left edge
     */
    UL("Up Left");

    /**
     * the name of the edge
     */
    private String name;

    Edge(String name) {
        this.name = name;
    }

    /**
     * @return the name of the edge
     */
    public String getName() {
        return name;
    }

    @Override
    public int hash() {
        return ordinal();
    }
}
