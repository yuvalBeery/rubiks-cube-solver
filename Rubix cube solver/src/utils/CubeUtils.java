package utils;

import data_structures.List;
import enums.Corner;
import enums.CubeColor;
import enums.Edge;
import enums.Move;
import data_structures.Cube;
import exceptions.IncorrectAmountOfColorsException;

import java.util.Random;

/**
 * This class holds functions that associated with the class Cube,
 * such as chuck validation of a move, or scramble a given cube.
 *
 * @see Cube
 */
public class CubeUtils {
    /**
     * The number of moves to execute when scrambling the cube
     */
    private static final int NUM_OF_SCRAMBLE_MOVES = 10;

    /**
     * Generates a random move
     * @param moves The moves that already have been executed on the cube
     * @return The serial number of the move function
     */
    private static int generateRandomMove(List<Move> moves) {
        Random random = new Random();
        int moveNumber = random.nextInt(Move.values().length);
        while (!isMoveValid(moves, Move.values()[moveNumber])) {
            moveNumber = random.nextInt(Move.values().length);
        }
        return moveNumber;
    }

    /**
     * Checks if the move generate a new state of the cube
     * @param moves The moves that already have been executed on the cube.
     * @param move The move to be executed.
     * @return True if the move creates a new state of the cube, false if not.
     *
     * @see List
     */
    public static boolean isMoveValid(List<Move> moves, Move move) {
        boolean condition1 = true, condition2 = true, condition3 = true;
        int size = moves.size();
        if (size >= 1) {
            //if the previous move and "move" are not the same kind (both not some kind of U move or D move...)
            condition1 = moves.get(size-1).toString().charAt(0) != move.toString().charAt(0);
            //if not (the previous move is parallel to "move" and the previous move is prioritised)
            condition2 = !(moves.get(size-1).toString().charAt(0) == Maps.parallelMoveMap.get(move).toString().charAt(0) && Maps.priorityMap.get(moves.get(size-1)) < Maps.priorityMap.get(move));
        }
        if (size >= 2) {
            //if not (the 2 previous move is kind of "move" and the previous move is parallel to "move")
            condition3 = !(moves.get(size-2).toString().charAt(0) == move.toString().charAt(0) && moves.get(size-1).toString().charAt(0) == Maps.parallelMoveMap.get(move).toString().charAt(0));
        }
        return condition1 && condition2 && condition3;

    }

    /**
     * Scramble the cube with NUM_OF_SCRAMBLE_MOVES moves
     * @param c The cube to scramble
     * @return ArrayList of the scramble moves
     */
    public static List<Move> scramble(Cube c) {
        List<Move> moves = new List<>();
        for (int i = 0; i < NUM_OF_SCRAMBLE_MOVES; i++) {
            int moveNumber = CubeUtils.generateRandomMove(moves);
            c.executeMove(Move.values()[moveNumber]);
            moves.add(Move.values()[moveNumber]);
        }
        return moves;
    }

    /**
     * Executes a list of moves on the cube.
     * @param moves The list of moves to execute.
     * @param c The cube to execute the moves on.
     */
    public static void executeAlgorithm(List<Move> moves, Cube c) {
        if (moves == null)
            return;
        for (Move move : moves) {
            c.executeMove(move);
        }
    }

    /**
     * Create a new List that contains the opposite moves of the algorithm in the reversed order.
     * for example: U, F', R2 -> R2, F, U'
     * @param algo the algorithm to reverse.
     * @return the reversed algorithm.
     */
    public static List<Move> reverseAlgorithm(List<Move> algo) {
        if (algo == null)
            return null;
        List<Move> reversed = new List<>();
        for (Move move : algo) {
            reversed.add(Maps.oppositeMoveMap.get(move));
        }
        reversed.reverse();
        return reversed;
    }

    public static Corner[] getCornersFromStrings(String first3faces, String last3face) {
        Corner[] corners = new Corner[8];

        corners[0] = getCorner(first3faces.charAt(2), first3faces.charAt(15), first3faces.charAt(21));
        corners[1] = getCorner(first3faces.charAt(7), first3faces.charAt(23), last3face.charAt(5));
        corners[2] = getCorner(first3faces.charAt(5), last3face.charAt(13), last3face.charAt(7));
        corners[3] = getCorner(first3faces.charAt(0), first3faces.charAt(13), last3face.charAt(15));
        corners[4] = getCorner(last3face.charAt(23), first3faces.charAt(10), first3faces.charAt(16));
        corners[5] = getCorner(last3face.charAt(18), first3faces.charAt(18), last3face.charAt(0));
        corners[6] = getCorner(last3face.charAt(16), last3face.charAt(8), last3face.charAt(2));
        corners[7] = getCorner(last3face.charAt(21), first3faces.charAt(8), last3face.charAt(10));

        return corners;
    }

    private static Corner getCorner(char c1, char c2, char c3) {
        if ((c1 == CubeColor.WHITE.charValue() || c1 == CubeColor.BLUE.charValue() || c1 == CubeColor.RED.charValue()) && (c2 == CubeColor.WHITE.charValue() || c2 == CubeColor.BLUE.charValue() || c2 == CubeColor.RED.charValue()) && (c3 == CubeColor.WHITE.charValue() || c3 == CubeColor.BLUE.charValue() || c3 == CubeColor.RED.charValue()))
            return Corner.DRF;
        else if ((c1 == CubeColor.WHITE.charValue() || c1 == CubeColor.GREEN.charValue() || c1 == CubeColor.RED.charValue()) && (c2 == CubeColor.WHITE.charValue() || c2 == CubeColor.GREEN.charValue() || c2 == CubeColor.RED.charValue()) && (c3 == CubeColor.WHITE.charValue() || c3 == CubeColor.GREEN.charValue() || c3 == CubeColor.RED.charValue()))
            return Corner.DRB;
        else if ((c1 == CubeColor.WHITE.charValue() || c1 == CubeColor.GREEN.charValue() || c1 == CubeColor.ORANGE.charValue()) && (c2 == CubeColor.WHITE.charValue() || c2 == CubeColor.GREEN.charValue() || c2 == CubeColor.ORANGE.charValue()) && (c3 == CubeColor.WHITE.charValue() || c3 == CubeColor.GREEN.charValue() || c3 == CubeColor.ORANGE.charValue()))
            return Corner.DLB;
        else if ((c1 == CubeColor.WHITE.charValue() || c1 == CubeColor.BLUE.charValue() || c1 == CubeColor.ORANGE.charValue()) && (c2 == CubeColor.WHITE.charValue() || c2 == CubeColor.BLUE.charValue() || c2 == CubeColor.ORANGE.charValue()) && (c3 == CubeColor.WHITE.charValue() || c3 == CubeColor.BLUE.charValue() || c3 == CubeColor.ORANGE.charValue()))
            return Corner.DLF;
        else if ((c1 == CubeColor.YELLOW.charValue() || c1 == CubeColor.BLUE.charValue() || c1 == CubeColor.RED.charValue()) && (c2 == CubeColor.YELLOW.charValue() || c2 == CubeColor.BLUE.charValue() || c2 == CubeColor.RED.charValue()) && (c3 == CubeColor.YELLOW.charValue() || c3 == CubeColor.BLUE.charValue() || c3 == CubeColor.RED.charValue()))
            return Corner.URF;
        else if ((c1 == CubeColor.YELLOW.charValue() || c1 == CubeColor.GREEN.charValue() || c1 == CubeColor.RED.charValue()) && (c2 == CubeColor.YELLOW.charValue() || c2 == CubeColor.GREEN.charValue() || c2 == CubeColor.RED.charValue()) && (c3 == CubeColor.YELLOW.charValue() || c3 == CubeColor.GREEN.charValue() || c3 == CubeColor.RED.charValue()))
            return Corner.URB;
        else if ((c1 == CubeColor.YELLOW.charValue() || c1 == CubeColor.GREEN.charValue() || c1 == CubeColor.ORANGE.charValue()) && (c2 == CubeColor.YELLOW.charValue() || c2 == CubeColor.GREEN.charValue() || c2 == CubeColor.ORANGE.charValue()) && (c3 == CubeColor.YELLOW.charValue() || c3 == CubeColor.GREEN.charValue() || c3 == CubeColor.ORANGE.charValue()))
            return Corner.ULB;
        else if ((c1 == CubeColor.YELLOW.charValue() || c1 == CubeColor.BLUE.charValue() || c1 == CubeColor.ORANGE.charValue()) && (c2 == CubeColor.YELLOW.charValue() || c2 == CubeColor.BLUE.charValue() || c2 == CubeColor.ORANGE.charValue()) && (c3 == CubeColor.YELLOW.charValue() || c3 == CubeColor.BLUE.charValue() || c3 == CubeColor.ORANGE.charValue()))
            return Corner.ULF;
        return null;
    }

    public static Edge[] getEdgesFromStrings(String first3faces, String last3faces) {
        Edge[] edges = new Edge[12];

        edges[0] = getEdge(first3faces.charAt(1), first3faces.charAt(14));
        edges[1] = getEdge(first3faces.charAt(4), first3faces.charAt(22));
        edges[2] = getEdge(first3faces.charAt(6), last3faces.charAt(6));
        edges[3] = getEdge(first3faces.charAt(3), last3faces.charAt(14));
        edges[4] = getEdge(first3faces.charAt(12), first3faces.charAt(19));
        edges[5] = getEdge(first3faces.charAt(20), last3faces.charAt(3));
        edges[6] = getEdge(last3faces.charAt(4), last3faces.charAt(11));
        edges[7] = getEdge(first3faces.charAt(11), last3faces.charAt(12));
        edges[8] = getEdge(last3faces.charAt(22), first3faces.charAt(9));
        edges[9] = getEdge(last3faces.charAt(20), first3faces.charAt(17));
        edges[10] = getEdge(last3faces.charAt(17), last3faces.charAt(1));
        edges[11] = getEdge(last3faces.charAt(19), last3faces.charAt(9));

        return edges;
    }

    private static Edge getEdge(char c1, char c2) {
        if ((c1 == CubeColor.WHITE.charValue() && c2 == CubeColor.BLUE.charValue()) || (c1 == CubeColor.BLUE.charValue() && c2 == CubeColor.WHITE.charValue()))
            return Edge.DF;
        else if ((c1 == CubeColor.WHITE.charValue() && c2 == CubeColor.RED.charValue()) || (c1 == CubeColor.RED.charValue() && c2 == CubeColor.WHITE.charValue()))
            return Edge.DR;
        else if ((c1 == CubeColor.WHITE.charValue() && c2 == CubeColor.GREEN.charValue()) || (c1 == CubeColor.GREEN.charValue() && c2 == CubeColor.WHITE.charValue()))
            return Edge.DB;
        else if ((c1 == CubeColor.WHITE.charValue() && c2 == CubeColor.ORANGE.charValue()) || (c1 == CubeColor.ORANGE.charValue() && c2 == CubeColor.WHITE.charValue()))
            return Edge.DL;
        else if ((c1 == CubeColor.BLUE.charValue() && c2 == CubeColor.RED.charValue()) || (c1 == CubeColor.RED.charValue() && c2 == CubeColor.BLUE.charValue()))
            return Edge.RF;
        else if ((c1 == CubeColor.GREEN.charValue() && c2 == CubeColor.RED.charValue()) || (c1 == CubeColor.RED.charValue() && c2 == CubeColor.GREEN.charValue()))
            return Edge.RB;
        else if ((c1 == CubeColor.GREEN.charValue() && c2 == CubeColor.ORANGE.charValue()) || (c1 == CubeColor.ORANGE.charValue() && c2 == CubeColor.GREEN.charValue()))
            return Edge.LB;
        else if ((c1 == CubeColor.BLUE.charValue() && c2 == CubeColor.ORANGE.charValue()) || (c1 == CubeColor.ORANGE.charValue() && c2 == CubeColor.BLUE.charValue()))
            return Edge.LF;
        else if ((c1 == CubeColor.YELLOW.charValue() && c2 == CubeColor.BLUE.charValue()) || (c1 == CubeColor.BLUE.charValue() && c2 == CubeColor.YELLOW.charValue()))
            return Edge.UF;
        else if ((c1 == CubeColor.YELLOW.charValue() && c2 == CubeColor.RED.charValue()) || (c1 == CubeColor.RED.charValue() && c2 == CubeColor.YELLOW.charValue()))
            return Edge.UR;
        else if ((c1 == CubeColor.YELLOW.charValue() && c2 == CubeColor.GREEN.charValue()) || (c1 == CubeColor.GREEN.charValue() && c2 == CubeColor.YELLOW.charValue()))
            return Edge.UB;
        else if ((c1 == CubeColor.YELLOW.charValue() && c2 == CubeColor.ORANGE.charValue()) || (c1 == CubeColor.ORANGE.charValue() && c2 == CubeColor.YELLOW.charValue()))
            return Edge.UL;
        return null;
    }

    public static void validateCube(String first3faces, String last3faces) throws Exception {
        //check if all the colors appears 8 times
        int[] colorsCounter = new int[CubeColor.values().length];
        String allFaces = first3faces + last3faces;
        for (int i = 0; i < allFaces.length(); i++) {
            colorsCounter[allFaces.charAt(i) - '0']++;
        }
        for (int amount : colorsCounter) {
            if (amount != 8)
                throw new IncorrectAmountOfColorsException(colorsCounter);
        }
    }


    public static Cube getCubeFromCamera() {
        CubeCamera cc = new CubeCamera();
        try {
            CubeUtils.validateCube(cc.faces[0] + cc.faces[1] + cc.faces[2], cc.faces[3] + cc.faces[4] + cc.faces[5]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        Cube c = new Cube(cc.faces[0] + cc.faces[1] + cc.faces[2], cc.faces[3] + cc.faces[4] + cc.faces[5]);
        return c;
    }

    /**
     * Creates a new cube from the given string
     * @param str String representation of the wanted cube (only numbers from 0-5)
     * @return The new cube
     */
    public static Cube toCube(String str) {
        return null;
    }

    /**
     * If the string is less than 24,
     * the function will push zeroes to the start of the string until the string length is 24
     * @param str Three faces of the Cube.
     * @return The filled String.
     */
    private static String fillWithZeros(String str) {
        while (str.length() < 24) {
            str = "0" + str;
        }
        return str;
    }
}
