package data_structures;

import enums.Corner;
import enums.Edge;
import enums.CubeColor;
import enums.Move;
import interfaces.Hashable;
import utils.CubeUtils;
import utils.Maps;

import java.io.Serializable;

/**
 * class that represents a 3*3*3 rubix cube.
 */
public class Cube implements Hashable, Serializable {
    /**
     * The size of the rubix cube
     */
    public static final int SIZE = 3;

    /**
     * The max hash value of a cube
     */
    public static final int MAX_HASH_VALUE = (int) Math.sqrt(Integer.MAX_VALUE);

    /**
     * Representation of the first 3 faces of the cube (white, blue, red).
     * Each number in the string is a color (0=white, 1=blue, 2=red, 3=green, 4=orange, 5=yellow).
     * every face is represented via 8 numbers (without the middle facelet).
     */
    public String first3Faces;
    /**
     * Representation of the last 3 faces of the cube (green, orange, yellow).
     * each number in the string is a color (0=white, 1=blue, 2=red, 3=green, 4=orange, 5=yellow).
     * every face is represented via 8 numbers (without the middle facelet)
     */
    public String last3Faces;

    /**
     * Representation of the location of each corner in the cube.
     * 0=DRF, 1=DRB, 2=DLB, 3=DLF, 4=URF, 5=URB, 6=ULB, 7=ULF
     * @see enums.Corner
     */
    public Corner[] corners;
    /**
     * Representation of the location of each edge in the cube.
     * 0=DF, 1=DR, 2=DB, 3=DL, 4=RF, 5=RB, 6=LB, 7=LF, 8=UF, 9=UR, 10=UB, 11=UL
     * @see enums.Edge
     */
    public Edge[] edges;

    /**
     * Creates a new solved Cube,
     */
    public Cube() {
        first3Faces = "000000001111111122222222";
        last3Faces = "333333334444444455555555";
        corners = new Corner[] {Corner.DRF, Corner.DRB, Corner.DLB, Corner.DLF, Corner.URF, Corner.URB, Corner.ULB, Corner.ULF};
        edges = new Edge[] {Edge.DF, Edge.DR, Edge.DB, Edge.DL, Edge.RF, Edge.RB, Edge.LB, Edge.LF, Edge.UF, Edge.UR, Edge.UB, Edge.UL};
    }

    public Cube(String first3Faces, String last3Faces) {
        this.first3Faces = first3Faces;
        this.last3Faces = last3Faces;
        this.corners = new Corner[8];
        this.edges = new Edge[12];

        Corner[] corners = CubeUtils.getCornersFromStrings(first3Faces, last3Faces);
        System.arraycopy(corners, 0, this.corners, 0, corners.length);
        Edge[] edges = CubeUtils.getEdgesFromStrings(first3Faces, last3Faces);
        System.arraycopy(edges, 0, this.edges, 0, edges.length);
    }

    /**
     * Executes a move on the cube.
     * @param move The move name.
     * @see Move
     * @see Maps
     */
    public void executeMove(Move move) {
        Maps.executeMoveMap.get(move).accept(this);
    }

    /**
     * Deeply clone a Cube object
     * @return A new instance of a data_structures.Cube that is deeply equal to this cube
     */
    @Override
    public Cube clone() {
        Cube cloned = new Cube();
        cloned.first3Faces = first3Faces;
        cloned.last3Faces = last3Faces;
        System.arraycopy(corners, 0, cloned.corners, 0, corners.length);
        System.arraycopy(edges, 0, cloned.edges, 0, edges.length);
        return cloned;
    }

    /**
     * Deeply equals two Cubes.
     * Two Cubes are considered deeply equal if each facelet of the other cube is equal to his corresponding facelet in this cube.
     * @param obj The object to compares the cube to.
     * @return True if they are deeply equal, false if they are not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != Cube.class)
            return false;
        Cube c = (Cube) obj;
        return first3Faces.equals(c.first3Faces) && last3Faces.equals(c.last3Faces);
    }

    @Override
    public String toString() {
        String str = "";
        int i = 0;

        while (i < 8) {
            str += CubeColor.values()[Integer.parseInt(String.valueOf(first3Faces.charAt(i)))].toString() + ", ";
            if (i == 3)
                str += CubeColor.WHITE.toString() + ", ";
            if (i == 2 || i == 4)
                str += "\n";
            i++;
        }
        str += "\n\n";
        while (i < 16) {
            str += CubeColor.values()[Integer.parseInt(String.valueOf(first3Faces.charAt(i)))].toString() + ", ";
            if (i == 11)
                str += CubeColor.BLUE.toString() + ", ";
            if (i == 10 || i == 12)
                str += "\n";
            i++;
        }
        str += "\n\n";
        while (i < 24) {
            str += CubeColor.values()[Integer.parseInt(String.valueOf(first3Faces.charAt(i)))].toString() + ", ";
            if (i == 19)
                str += CubeColor.RED.toString() + ", ";
            else if (i == 18 || i == 20)
                str += "\n";
            i++;
        }
        str += "\n\n";

        i = 0;
        while (i < 8) {
            str += CubeColor.values()[Integer.parseInt(String.valueOf(last3Faces.charAt(i)))].toString() + ", ";
            if (i == 3)
                str += CubeColor.GREEN.toString() + ", ";
            if (i == 2 || i == 4)
                str += "\n";
            i++;
        }
        str += "\n\n";
        while (i < 16) {
            str += CubeColor.values()[Integer.parseInt(String.valueOf(last3Faces.charAt(i)))].toString() + ", ";
            if (i == 11)
                str += CubeColor.ORANGE.toString() + ", ";
            if (i == 10 || i == 12)
                str += "\n";
            i++;
        }
        str += "\n\n";
        while (i < 24) {
            str += CubeColor.values()[Integer.parseInt(String.valueOf(last3Faces.charAt(i)))].toString() + ", ";
            if (i == 19)
                str += CubeColor.YELLOW.toString() + ", ";
            if (i == 18 || i == 20)
                str += "\n";
            i++;
        }
        str += "\n\n";

        return str;
    }

    @Override
    public int hash() {
        long l1 = Long.parseLong(first3Faces, 6);
        long l2 = Long.parseLong(last3Faces, 6);
        long l3 = l1 & l2;

        l1 = l3 & 0xffff0000;
        l1 >>= 4*4;
        l2 = l3 & 0x0000ffff;

        return (int) (l1 | l2) < 0 ? ~((int) (l1 | l2)) : (int) (l1 | l2);
    }
}
