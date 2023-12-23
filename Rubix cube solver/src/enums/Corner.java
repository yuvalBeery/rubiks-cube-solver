package enums;

import interfaces.Hashable;

/**
 * enum that represent the corners of the rubix cube.
 * each corner is the initials of its full name.
 * for example: DRF = down right front corner.
 */
public enum Corner implements Hashable {
    /**
     * down right front corner
     */
    DRF("Down Right Front"),
    /**
     * down right back corner
     */
    DRB("Down Right Back"),
    /**
     * down left back corner
     */
    DLB("Down Left Back"),
    /**
     * down left front corner
     */
    DLF("Down Left Front"),
    /**
     * up right front corner
     */
    URF("Up Right Front"),
    /**
     * up right back corner
     */
    URB("Up Right Back"),
    /**
     * up left back corner
     */
    ULB("Up Left Back"),
    /**
     * up left front corner
     */
    ULF("Up Left Front");

    /**
     * the name of the corner
     */
    private String name;

    Corner(String name) {
        this.name = name;
    }

    /**
     * @return the name of the corner
     */
    public String getName() {
        return name;
    }

    @Override
    public int hash() {
        return ordinal();
    }
}
