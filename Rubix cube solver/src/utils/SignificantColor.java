package utils;

import data_structures.Cube;
import enums.CubeColor;

/**
 * class that have static method to get the significant color of a piece by the piece place.
 * the significant color is color found in the face associated with the first letter of the name of the piece.
 */
public class SignificantColor {
    //corners
    public static CubeColor DRF(Cube c) {
        return CubeColor.values()[c.first3Faces.charAt(2) - 48];
    }

    public static CubeColor DRB(Cube c) {
        return CubeColor.values()[c.first3Faces.charAt(7) - 48];
    }

    public static CubeColor DLB(Cube c) {
        return CubeColor.values()[c.first3Faces.charAt(5) - 48];
    }

    public static CubeColor DLF(Cube c) {
        return CubeColor.values()[c.first3Faces.charAt(0) - 48];
    }

    public static CubeColor URF(Cube c) {
        return CubeColor.values()[c.last3Faces.charAt(23) - 48];
    }

    public static CubeColor URB(Cube c) {
        return CubeColor.values()[c.last3Faces.charAt(18) - 48];
    }

    public static CubeColor ULB(Cube c) {
        return CubeColor.values()[c.last3Faces.charAt(16) - 48];
    }

    public static CubeColor ULF(Cube c) {
        return CubeColor.values()[c.last3Faces.charAt(21) - 48];
    }


    //edges
    public static CubeColor DF(Cube c) {
        return CubeColor.values()[c.first3Faces.charAt(1) - 48];
    }

    public static CubeColor DR(Cube c) {
        return CubeColor.values()[c.first3Faces.charAt(4) - 48];
    }

    public static CubeColor DB(Cube c) {
        return CubeColor.values()[c.first3Faces.charAt(6) - 48];
    }

    public static CubeColor DL(Cube c) {
        return CubeColor.values()[c.first3Faces.charAt(3) - 48];
    }

    public static CubeColor RF(Cube c) {
        return CubeColor.values()[c.first3Faces.charAt(12) - 48];
    }

    public static CubeColor RB(Cube c) {
        return CubeColor.values()[c.last3Faces.charAt(3) - 48];
    }

    public static CubeColor LB(Cube c) {
        return CubeColor.values()[c.last3Faces.charAt(4) - 48];
    }

    public static CubeColor LF(Cube c) {
        return CubeColor.values()[c.first3Faces.charAt(11) - 48];
    }

    public static CubeColor UF(Cube c) {
        return CubeColor.values()[c.last3Faces.charAt(22) - 48];
    }

    public static CubeColor UR(Cube c) {
        return CubeColor.values()[c.last3Faces.charAt(20) - 48];
    }

    public static CubeColor UB(Cube c) {
        return CubeColor.values()[c.last3Faces.charAt(17) - 48];
    }

    public static CubeColor UL(Cube c) {
        return CubeColor.values()[c.last3Faces.charAt(19) - 48];
    }
}
