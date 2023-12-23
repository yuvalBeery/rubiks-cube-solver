package utils;

import data_structures.Cube;
import enums.Corner;
import enums.Edge;

/**
 * Class that contains all the possible moves that can be executed on the cube.
 * Every move function gets a cube reference and returns the name of the move.
 * The cube uses this class to preform moves on the cube.
 */
public class Moves {
    private static final int START_OF_WHITE_FACE = 0;
    private static final int START_OF_BLUE_FACE = 8;
    private static final int START_OF_RED_FACE = 16;
    private static final int START_OF_GREEN_FACE = 0;
    private static final int START_OF_ORANGE_FACE = 8;
    private static final int START_OF_YELLOW_FACE = 16;
    /**
     * Executes the move "U" on the cube.
     * @param c the cube to execute the move on
     */
    public static void U(Cube c) {
        char tmp1;
        for (int i = 0; i < Cube.SIZE; i++) {
            tmp1 = c.first3Faces.charAt(START_OF_BLUE_FACE+i);
            c.first3Faces = replaceChar(c.first3Faces, START_OF_BLUE_FACE+i, c.first3Faces.charAt(START_OF_RED_FACE+i));
            c.first3Faces = replaceChar(c.first3Faces, START_OF_RED_FACE+i, c.last3Faces.charAt(START_OF_GREEN_FACE+i));
            c.last3Faces = replaceChar(c.last3Faces, START_OF_GREEN_FACE+i, c.last3Faces.charAt(START_OF_ORANGE_FACE+i));
            c.last3Faces = replaceChar(c.last3Faces, START_OF_ORANGE_FACE+i, tmp1);
        }
        c.last3Faces = rotateFace(c.last3Faces, START_OF_YELLOW_FACE, START_OF_YELLOW_FACE+8);

        //corners
        Corner tmp2 = c.corners[4];
        c.corners[4] = c.corners[5];
        c.corners[5] = c.corners[6];
        c.corners[6] = c.corners[7];
        c.corners[7] = tmp2;
        //edges
        Edge tmp3 = c.edges[8];
        c.edges[8] = c.edges[9];
        c.edges[9] = c.edges[10];
        c.edges[10] = c.edges[11];
        c.edges[11] = tmp3;
    }

    /**
     * Executes the move "U'" on the cube.
     * @param c the cube to execute the move on
     */
    public static void UPrime(Cube c) {
        char tmp1;
        for (int i = 0; i < Cube.SIZE; i++) {
            tmp1 = c.first3Faces.charAt(START_OF_BLUE_FACE+i);
            c.first3Faces = replaceChar(c.first3Faces, START_OF_BLUE_FACE+i, c.last3Faces.charAt(START_OF_ORANGE_FACE+i));
            c.last3Faces = replaceChar(c.last3Faces, START_OF_ORANGE_FACE+i, c.last3Faces.charAt(START_OF_GREEN_FACE+i));
            c.last3Faces = replaceChar(c.last3Faces, START_OF_GREEN_FACE+i, c.first3Faces.charAt(START_OF_RED_FACE+i));
            c.first3Faces = replaceChar(c.first3Faces, START_OF_RED_FACE+i, tmp1);
        }
        c.last3Faces = rotateFacePrime(c.last3Faces, START_OF_YELLOW_FACE, START_OF_YELLOW_FACE+8);

        //corners
        Corner tmp2 = c.corners[4];
        c.corners[4] = c.corners[7];
        c.corners[7] = c.corners[6];
        c.corners[6] = c.corners[5];
        c.corners[5] = tmp2;
        //edges
        Edge tmp3 = c.edges[8];
        c.edges[8] = c.edges[11];
        c.edges[11] = c.edges[10];
        c.edges[10] = c.edges[9];
        c.edges[9] = tmp3;
    }

    /**
     * Executes the move "U2" on the cube.
     * @param c the cube to execute the move on
     */
    public static void U2(Cube c) {
        char tmp1;
        for (int i = 0; i < Cube.SIZE; i++) {
            tmp1 = c.first3Faces.charAt(START_OF_BLUE_FACE+i);
            c.first3Faces = replaceChar(c.first3Faces, START_OF_BLUE_FACE+i, c.last3Faces.charAt(START_OF_GREEN_FACE+i));
            c.last3Faces = replaceChar(c.last3Faces, START_OF_GREEN_FACE+i, tmp1);
            tmp1 = c.first3Faces.charAt(START_OF_RED_FACE+i);
            c.first3Faces = replaceChar(c.first3Faces, START_OF_RED_FACE+i, c.last3Faces.charAt(START_OF_ORANGE_FACE+i));
            c.last3Faces = replaceChar(c.last3Faces, START_OF_ORANGE_FACE+i, tmp1);
        }
        c.last3Faces = rotateFace2(c.last3Faces, START_OF_YELLOW_FACE, START_OF_YELLOW_FACE+8);

        //corners
        Corner tmp2 = c.corners[4];
        c.corners[4] = c.corners[6];
        c.corners[6] = tmp2;
        tmp2 = c.corners[5];
        c.corners[5] = c.corners[7];
        c.corners[7] = tmp2;
        //edges
        Edge tmp3 = c.edges[8];
        c.edges[8] = c.edges[10];
        c.edges[10] = tmp3;
        tmp3 = c.edges[9];
        c.edges[9] = c.edges[11];
        c.edges[11] = tmp3;
    }

    /**
     * Executes the move "D" on the cube.
     * @param c the cube to execute the move on
     */
    public static void D(Cube c) {
        char tmp1;
        for (int i = 0; i < Cube.SIZE; i++) {
            tmp1 = c.first3Faces.charAt(START_OF_BLUE_FACE+Cube.SIZE*2-1+i);
            c.first3Faces = replaceChar(c.first3Faces, START_OF_BLUE_FACE+Cube.SIZE*2-1+i, c.last3Faces.charAt(START_OF_ORANGE_FACE+Cube.SIZE*2-1+i));
            c.last3Faces = replaceChar(c.last3Faces, START_OF_ORANGE_FACE+Cube.SIZE*2-1+i, c.last3Faces.charAt(START_OF_GREEN_FACE+Cube.SIZE*2-1+i));
            c.last3Faces = replaceChar(c.last3Faces, START_OF_GREEN_FACE+Cube.SIZE*2-1+i, c.first3Faces.charAt(START_OF_RED_FACE+Cube.SIZE*2-1+i));
            c.first3Faces = replaceChar(c.first3Faces, START_OF_RED_FACE+Cube.SIZE*2-1+i, tmp1);
        }
        String str = rotateFace(c.first3Faces, START_OF_WHITE_FACE, START_OF_WHITE_FACE+8);
        c.first3Faces = str;

        //corners
        Corner tmp2 = c.corners[0];
        c.corners[0] = c.corners[3];
        c.corners[3] = c.corners[2];
        c.corners[2] = c.corners[1];
        c.corners[1] = tmp2;
        //edges
        Edge tmp3 = c.edges[0];
        c.edges[0] = c.edges[3];
        c.edges[3] = c.edges[2];
        c.edges[2] = c.edges[1];
        c.edges[1] = tmp3;
    }

    /**
     * Executes the move "D'" on the cube.
     * @param c the cube to execute the move on
     */
    public static void DPrime(Cube c) {
        char tmp1;
        for (int i = 0; i < Cube.SIZE; i++) {
            tmp1 = c.first3Faces.charAt(START_OF_BLUE_FACE+Cube.SIZE*2-1+i);
            c.first3Faces = replaceChar(c.first3Faces, START_OF_BLUE_FACE+Cube.SIZE*2-1+i, c.first3Faces.charAt(START_OF_RED_FACE+Cube.SIZE*2-1+i));
            c.first3Faces = replaceChar(c.first3Faces, START_OF_RED_FACE+Cube.SIZE*2-1+i, c.last3Faces.charAt(START_OF_GREEN_FACE+Cube.SIZE*2-1+i));
            c.last3Faces = replaceChar(c.last3Faces, START_OF_GREEN_FACE+Cube.SIZE*2-1+i, c.last3Faces.charAt(START_OF_ORANGE_FACE+Cube.SIZE*2-1+i));
            c.last3Faces = replaceChar(c.last3Faces, START_OF_ORANGE_FACE+Cube.SIZE*2-1+i, tmp1);
        }
        c.first3Faces = rotateFacePrime(c.first3Faces, START_OF_WHITE_FACE, START_OF_WHITE_FACE+8);

        //corners
        Corner tmp2 = c.corners[0];
        c.corners[0] = c.corners[1];
        c.corners[1] = c.corners[2];
        c.corners[2] = c.corners[3];
        c.corners[3] = tmp2;
        //edges
        Edge tmp3 = c.edges[0];
        c.edges[0] = c.edges[1];
        c.edges[1] = c.edges[2];
        c.edges[2] = c.edges[3];
        c.edges[3] = tmp3;
    }

    /**
     * Executes the move "D2" on the cube.
     * @param c the cube to execute the move on
     */
    public static void D2(Cube c) {
        char tmp1;
        for (int i = 0; i < Cube.SIZE; i++) {
            tmp1 = c.first3Faces.charAt(START_OF_BLUE_FACE+Cube.SIZE*2-1+i);
            c.first3Faces = replaceChar(c.first3Faces, START_OF_BLUE_FACE+Cube.SIZE*2-1+i, c.last3Faces.charAt(START_OF_GREEN_FACE+Cube.SIZE*2-1+i));
            c.last3Faces = replaceChar(c.last3Faces, START_OF_GREEN_FACE+Cube.SIZE*2-1+i, tmp1);
            tmp1 = c.first3Faces.charAt(START_OF_RED_FACE+Cube.SIZE*2-1+i);
            c.first3Faces = replaceChar(c.first3Faces, START_OF_RED_FACE+Cube.SIZE*2-1+i, c.last3Faces.charAt(START_OF_ORANGE_FACE+Cube.SIZE*2-1+i));
            c.last3Faces = replaceChar(c.last3Faces, START_OF_ORANGE_FACE+Cube.SIZE*2-1+i, tmp1);
        }
        c.first3Faces = rotateFace2(c.first3Faces, START_OF_WHITE_FACE, START_OF_WHITE_FACE+8);

        //corners
        Corner tmp2 = c.corners[0];
        c.corners[0] = c.corners[2];
        c.corners[2] = tmp2;
        tmp2 = c.corners[1];
        c.corners[1] = c.corners[3];
        c.corners[3] = tmp2;
        //edges
        Edge tmp3 = c.edges[0];
        c.edges[0] = c.edges[2];
        c.edges[2] = tmp3;
        tmp3 = c.edges[1];
        c.edges[1] = c.edges[3];
        c.edges[3] = tmp3;
    }

    /**
     * Executes the move "R" on the cube.
     * @param c the cube to execute the move on
     */
    public static void R(Cube c) {
        char tmp1;
        int whiteIndex, blueIndex, yellowIndex, greenIndex;
        for (int i = 0; i < Cube.SIZE; i++) {
            whiteIndex = START_OF_WHITE_FACE + Cube.SIZE*i + ((i==0)?2:1);
            blueIndex = START_OF_BLUE_FACE + Cube.SIZE*i + ((i==0)?2:1);
            yellowIndex = START_OF_YELLOW_FACE + Cube.SIZE*i + ((i==0)?2:1);
            greenIndex = START_OF_GREEN_FACE + Cube.SIZE*(Cube.SIZE-1-i) - ((i==0)?1:0);

            tmp1 = c.first3Faces.charAt(whiteIndex);
            c.first3Faces = replaceChar(c.first3Faces, whiteIndex, c.last3Faces.charAt(greenIndex));
            c.last3Faces = replaceChar(c.last3Faces, greenIndex, c.last3Faces.charAt(yellowIndex));
            c.last3Faces = replaceChar(c.last3Faces, yellowIndex, c.first3Faces.charAt(blueIndex));
            c.first3Faces = replaceChar(c.first3Faces, blueIndex, tmp1);
        }
        c.first3Faces = rotateFace(c.first3Faces, START_OF_RED_FACE, START_OF_RED_FACE+8);

        //corners
        Corner tmp2 = c.corners[0];
        c.corners[0] = c.corners[1];
        c.corners[1] = c.corners[5];
        c.corners[5] = c.corners[4];
        c.corners[4] = tmp2;
        //edges
        Edge tmp3 = c.edges[1];
        c.edges[1] = c.edges[5];
        c.edges[5] = c.edges[9];
        c.edges[9] = c.edges[4];
        c.edges[4] = tmp3;
    }

    /**
     * Executes the move "R'" on the cube.
     * @param c the cube to execute the move on
     */
    public static void RPrime(Cube c) {
        char tmp1;
        int whiteIndex, blueIndex, yellowIndex, greenIndex;
        for (int i = 0; i < Cube.SIZE; i++) {
            whiteIndex = START_OF_WHITE_FACE + Cube.SIZE*i + ((i==0)?2:1);
            blueIndex = START_OF_BLUE_FACE + Cube.SIZE*i + ((i==0)?2:1);
            yellowIndex = START_OF_YELLOW_FACE + Cube.SIZE*i + ((i==0)?2:1);
            greenIndex = START_OF_GREEN_FACE + Cube.SIZE*(Cube.SIZE-1-i) - ((i==0)?1:0);

            tmp1 = c.first3Faces.charAt(whiteIndex);
            c.first3Faces = replaceChar(c.first3Faces, whiteIndex, c.first3Faces.charAt(blueIndex));
            c.first3Faces = replaceChar(c.first3Faces, blueIndex, c.last3Faces.charAt(yellowIndex));
            c.last3Faces = replaceChar(c.last3Faces, yellowIndex, c.last3Faces.charAt(greenIndex));
            c.last3Faces = replaceChar(c.last3Faces, greenIndex, tmp1);
        }
        c.first3Faces = rotateFacePrime(c.first3Faces, START_OF_RED_FACE, START_OF_RED_FACE+8);

        //corners
        Corner tmp2 = c.corners[0];
        c.corners[0] = c.corners[4];
        c.corners[4] = c.corners[5];
        c.corners[5] = c.corners[1];
        c.corners[1] = tmp2;
        //edges
        Edge tmp3 = c.edges[1];
        c.edges[1] = c.edges[4];
        c.edges[4] = c.edges[9];
        c.edges[9] = c.edges[5];
        c.edges[5] = tmp3;
    }

    /**
     * Executes the move "R2" on the cube.
     * @param c the cube to execute the move on
     */
    public static void R2(Cube c) {
        char tmp1;
        int whiteIndex, blueIndex, yellowIndex, greenIndex;
        for (int i = 0; i < Cube.SIZE; i++) {
            whiteIndex = START_OF_WHITE_FACE + Cube.SIZE*i + ((i==0)?2:1);
            blueIndex = START_OF_BLUE_FACE + Cube.SIZE*i + ((i==0)?2:1);
            yellowIndex = START_OF_YELLOW_FACE + Cube.SIZE*i + ((i==0)?2:1);
            greenIndex = START_OF_GREEN_FACE + Cube.SIZE*(Cube.SIZE-1-i) - ((i==0)?1:0);

            tmp1 = c.first3Faces.charAt(whiteIndex);
            c.first3Faces = replaceChar(c.first3Faces, whiteIndex, c.last3Faces.charAt(yellowIndex));
            c.last3Faces = replaceChar(c.last3Faces, yellowIndex, tmp1);
            tmp1 = c.first3Faces.charAt(blueIndex);
            c.first3Faces = replaceChar(c.first3Faces, blueIndex, c.last3Faces.charAt(greenIndex));
            c.last3Faces = replaceChar(c.last3Faces, greenIndex, tmp1);
        }
        c.first3Faces = rotateFace2(c.first3Faces, START_OF_RED_FACE, START_OF_RED_FACE+8);

        //corners
        Corner tmp2 = c.corners[0];
        c.corners[0] = c.corners[5];
        c.corners[5] = tmp2;
        tmp2 = c.corners[1];
        c.corners[1] = c.corners[4];
        c.corners[4] = tmp2;
        //edges
        Edge tmp3 = c.edges[1];
        c.edges[1] = c.edges[9];
        c.edges[9] = tmp3;
        tmp3 = c.edges[5];
        c.edges[5] = c.edges[4];
        c.edges[4] = tmp3;
    }

    /**
     * Executes the move "L" on the cube.
     * @param c the cube to execute the move on
     */
    public static void L(Cube c) {
        char tmp1;
        int whiteIndex, blueIndex, yellowIndex, greenIndex;
        for (int i = 0; i < Cube.SIZE; i++) {
            whiteIndex = START_OF_WHITE_FACE + Cube.SIZE*i - ((i==2)?1:0);
            blueIndex = START_OF_BLUE_FACE + Cube.SIZE*i - ((i==2)?1:0);
            yellowIndex = START_OF_YELLOW_FACE + Cube.SIZE*i - ((i==2)?1:0);
            greenIndex = START_OF_GREEN_FACE + Cube.SIZE*(Cube.SIZE-1-i) + ((i==2)?2:1);

            tmp1 = c.first3Faces.charAt(whiteIndex);
            c.first3Faces = replaceChar(c.first3Faces, whiteIndex, c.first3Faces.charAt(blueIndex));
            c.first3Faces = replaceChar(c.first3Faces, blueIndex, c.last3Faces.charAt(yellowIndex));
            c.last3Faces = replaceChar(c.last3Faces, yellowIndex, c.last3Faces.charAt(greenIndex));
            c.last3Faces = replaceChar(c.last3Faces, greenIndex, tmp1);
        }
        c.last3Faces = rotateFace(c.last3Faces, START_OF_ORANGE_FACE, START_OF_ORANGE_FACE+8);

        //corners
        Corner tmp2 = c.corners[3];
        c.corners[3] = c.corners[7];
        c.corners[7] = c.corners[6];
        c.corners[6] = c.corners[2];
        c.corners[2] = tmp2;
        //edges
        Edge tmp3 = c.edges[3];
        c.edges[3] = c.edges[7];
        c.edges[7] = c.edges[11];
        c.edges[11] = c.edges[6];
        c.edges[6] = tmp3;
    }

    /**
     * Executes the move "L'" on the cube.
     * @param c the cube to execute the move on
     */
    public static void LPrime(Cube c) {
        char tmp1;
        int whiteIndex, blueIndex, yellowIndex, greenIndex;
        for (int i = 0; i < Cube.SIZE; i++) {
            whiteIndex = START_OF_WHITE_FACE + Cube.SIZE*i - ((i==2)?1:0);
            blueIndex = START_OF_BLUE_FACE + Cube.SIZE*i - ((i==2)?1:0);
            yellowIndex = START_OF_YELLOW_FACE + Cube.SIZE*i - ((i==2)?1:0);
            greenIndex = START_OF_GREEN_FACE + Cube.SIZE*(Cube.SIZE-1-i) + ((i==2)?2:1);

            tmp1 = c.first3Faces.charAt(whiteIndex);
            c.first3Faces = replaceChar(c.first3Faces, whiteIndex, c.last3Faces.charAt(greenIndex));
            c.last3Faces = replaceChar(c.last3Faces, greenIndex, c.last3Faces.charAt(yellowIndex));
            c.last3Faces = replaceChar(c.last3Faces, yellowIndex, c.first3Faces.charAt(blueIndex));
            c.first3Faces = replaceChar(c.first3Faces, blueIndex, tmp1);
        }
        c.last3Faces = rotateFacePrime(c.last3Faces, START_OF_ORANGE_FACE, START_OF_ORANGE_FACE+8);

        //corners
        Corner tmp2 = c.corners[3];
        c.corners[3] = c.corners[2];
        c.corners[2] = c.corners[6];
        c.corners[6] = c.corners[7];
        c.corners[7] = tmp2;
        //edges
        Edge tmp3 = c.edges[3];
        c.edges[3] = c.edges[6];
        c.edges[6] = c.edges[11];
        c.edges[11] = c.edges[7];
        c.edges[7] = tmp3;
    }

    /**
     * Executes the move "L2" on the cube.
     * @param c the cube to execute the move on
     */
    public static void L2(Cube c) {
        char tmp1;
        int whiteIndex, blueIndex, yellowIndex, greenIndex;
        for (int i = 0; i < Cube.SIZE; i++) {
            whiteIndex = START_OF_WHITE_FACE + Cube.SIZE*i - ((i==2)?1:0);
            blueIndex = START_OF_BLUE_FACE + Cube.SIZE*i - ((i==2)?1:0);
            yellowIndex = START_OF_YELLOW_FACE + Cube.SIZE*i - ((i==2)?1:0);
            greenIndex = START_OF_GREEN_FACE + Cube.SIZE*(Cube.SIZE-1-i) + ((i==2)?2:1);

            tmp1 = c.first3Faces.charAt(whiteIndex);
            c.first3Faces = replaceChar(c.first3Faces, whiteIndex, c.last3Faces.charAt(yellowIndex));
            c.last3Faces = replaceChar(c.last3Faces, yellowIndex, tmp1);
            tmp1 = c.first3Faces.charAt(blueIndex);
            c.first3Faces = replaceChar(c.first3Faces, blueIndex, c.last3Faces.charAt(greenIndex));
            c.last3Faces = replaceChar(c.last3Faces, greenIndex, tmp1);
        }
        c.last3Faces = rotateFace2(c.last3Faces, START_OF_ORANGE_FACE, START_OF_ORANGE_FACE+8);

        //corners
        Corner tmp2 = c.corners[3];
        c.corners[3] = c.corners[6];
        c.corners[6] = tmp2;
        tmp2 = c.corners[7];
        c.corners[7] = c.corners[2];
        c.corners[2] = tmp2;
        //edges
        Edge tmp3 = c.edges[3];
        c.edges[3] = c.edges[11];
        c.edges[11] = tmp3;
        tmp3 = c.edges[7];
        c.edges[7] = c.edges[6];
        c.edges[6] = tmp3;
    }

    /**
     * Executes the move "F" on the cube.
     * @param c the cube to execute the move on
     */
    public static void F(Cube c) {
        char tmp1;
        int whiteIndex, redIndex, yellowIndex, orangeIndex;
        for (int i = 0; i < Cube.SIZE; i++) {
            whiteIndex = START_OF_WHITE_FACE + (Cube.SIZE-1-i);
            redIndex = START_OF_RED_FACE + Cube.SIZE*i - ((i==2)?1:0);
            yellowIndex = START_OF_YELLOW_FACE + Cube.SIZE*2-1+i;
            orangeIndex = START_OF_ORANGE_FACE + Cube.SIZE*(Cube.SIZE-1-i) + ((i==2)?2:1);

            tmp1 = c.first3Faces.charAt(whiteIndex);
            c.first3Faces = replaceChar(c.first3Faces, whiteIndex, c.first3Faces.charAt(redIndex));
            c.first3Faces = replaceChar(c.first3Faces, redIndex, c.last3Faces.charAt(yellowIndex));
            c.last3Faces = replaceChar(c.last3Faces, yellowIndex, c.last3Faces.charAt(orangeIndex));
            c.last3Faces = replaceChar(c.last3Faces, orangeIndex, tmp1);
        }
        c.first3Faces = rotateFace(c.first3Faces, START_OF_BLUE_FACE, START_OF_BLUE_FACE+8);

        //corners
        Corner tmp2 = c.corners[0];
        c.corners[0] = c.corners[4];
        c.corners[4] = c.corners[7];
        c.corners[7] = c.corners[3];
        c.corners[3] = tmp2;
        //edges
        Edge tmp3 = c.edges[0];
        c.edges[0] = c.edges[4];
        c.edges[4] = c.edges[8];
        c.edges[8] = c.edges[7];
        c.edges[7] = tmp3;
    }

    /**
     * Executes the move "F'" on the cube.
     * @param c the cube to execute the move on
     */
    public static void FPrime(Cube c) {
        char tmp1;
        int whiteIndex, redIndex, yellowIndex, orangeIndex;
        for (int i = 0; i < Cube.SIZE; i++) {
            whiteIndex = START_OF_WHITE_FACE + (Cube.SIZE-1-i);
            redIndex = START_OF_RED_FACE + Cube.SIZE*i - ((i==2)?1:0);
            yellowIndex = START_OF_YELLOW_FACE + Cube.SIZE*2-1+i;
            orangeIndex = START_OF_ORANGE_FACE + Cube.SIZE*(Cube.SIZE-1-i) + ((i==2)?2:1);

            tmp1 = c.first3Faces.charAt(whiteIndex);
            c.first3Faces = replaceChar(c.first3Faces, whiteIndex, c.last3Faces.charAt(orangeIndex));
            c.last3Faces = replaceChar(c.last3Faces, orangeIndex, c.last3Faces.charAt(yellowIndex));
            c.last3Faces = replaceChar(c.last3Faces, yellowIndex, c.first3Faces.charAt(redIndex));
            c.first3Faces = replaceChar(c.first3Faces, redIndex, tmp1);
        }
        c.first3Faces = rotateFacePrime(c.first3Faces, START_OF_BLUE_FACE, START_OF_BLUE_FACE+8);

        //corners
        Corner tmp2 = c.corners[0];
        c.corners[0] = c.corners[3];
        c.corners[3] = c.corners[7];
        c.corners[7] = c.corners[4];
        c.corners[4] = tmp2;
        //edges
        Edge tmp3 = c.edges[0];
        c.edges[0] = c.edges[7];
        c.edges[7] = c.edges[8];
        c.edges[8] = c.edges[4];
        c.edges[4] = tmp3;
    }

    /**
     * Executes the move "F2" on the cube.
     * @param c the cube to execute the move on
     */
    public static void F2(Cube c) {
        char tmp1;
        int whiteIndex, redIndex, yellowIndex, orangeIndex;
        for (int i = 0; i < Cube.SIZE; i++) {
            whiteIndex = START_OF_WHITE_FACE + (Cube.SIZE-1-i);
            redIndex = START_OF_RED_FACE + Cube.SIZE*i - ((i==2)?1:0);
            yellowIndex = START_OF_YELLOW_FACE + Cube.SIZE*2-1+i;
            orangeIndex = START_OF_ORANGE_FACE + Cube.SIZE*(Cube.SIZE-1-i) + ((i==2)?2:1);

            tmp1 = c.first3Faces.charAt(whiteIndex);
            c.first3Faces = replaceChar(c.first3Faces, whiteIndex, c.last3Faces.charAt(yellowIndex));
            c.last3Faces = replaceChar(c.last3Faces, yellowIndex, tmp1);
            tmp1 = c.first3Faces.charAt(redIndex);
            c.first3Faces = replaceChar(c.first3Faces, redIndex, c.last3Faces.charAt(orangeIndex));
            c.last3Faces = replaceChar(c.last3Faces, orangeIndex, tmp1);
        }
        c.first3Faces = rotateFace2(c.first3Faces, START_OF_BLUE_FACE, START_OF_BLUE_FACE+8);

        //corners
        Corner tmp2 = c.corners[0];
        c.corners[0] = c.corners[7];
        c.corners[7] = tmp2;
        tmp2 = c.corners[4];
        c.corners[4] = c.corners[3];
        c.corners[3] = tmp2;
        //edges
        Edge tmp3 = c.edges[0];
        c.edges[0] = c.edges[8];
        c.edges[8] = tmp3;
        tmp3 = c.edges[4];
        c.edges[4] = c.edges[7];
        c.edges[7] = tmp3;
    }

    /**
     * Executes the move "B" on the cube.
     * @param c the cube to execute the move on
     */
    public static void B(Cube c) {
        char tmp1;
        int whiteIndex, orangeIndex, yellowIndex, redIndex;
        for (int i = 0; i < Cube.SIZE; i++) {
            whiteIndex = START_OF_WHITE_FACE + Cube.SIZE*2-1+i;
            orangeIndex = START_OF_ORANGE_FACE + Cube.SIZE*i - ((i==2)?1:0);
            yellowIndex = START_OF_YELLOW_FACE + Cube.SIZE-1-i;
            redIndex = START_OF_RED_FACE + Cube.SIZE*(Cube.SIZE-1-i) + ((i==2)?2:1);

            tmp1 = c.first3Faces.charAt(whiteIndex);
            c.first3Faces = replaceChar(c.first3Faces, whiteIndex, c.last3Faces.charAt(orangeIndex));
            c.last3Faces = replaceChar(c.last3Faces, orangeIndex, c.last3Faces.charAt(yellowIndex));
            c.last3Faces = replaceChar(c.last3Faces, yellowIndex, c.first3Faces.charAt(redIndex));
            c.first3Faces = replaceChar(c.first3Faces, redIndex, tmp1);
        }
        c.last3Faces = rotateFace(c.last3Faces, START_OF_GREEN_FACE, START_OF_GREEN_FACE+8);

        //corners
        Corner tmp2 = c.corners[1];
        c.corners[1] = c.corners[2];
        c.corners[2] = c.corners[6];
        c.corners[6] = c.corners[5];
        c.corners[5] = tmp2;
        //edges
        Edge tmp3 = c.edges[2];
        c.edges[2] = c.edges[6];
        c.edges[6] = c.edges[10];
        c.edges[10] = c.edges[5];
        c.edges[5] = tmp3;
    }

    /**
     * Executes the move "B'" on the cube.
     * @param c the cube to execute the move on
     */
    public static void BPrime(Cube c) {
        char tmp1;
        int whiteIndex, orangeIndex, yellowIndex, redIndex;
        for (int i = 0; i < Cube.SIZE; i++) {
            whiteIndex = START_OF_WHITE_FACE + Cube.SIZE*2-1+i;
            redIndex = START_OF_RED_FACE + Cube.SIZE*(Cube.SIZE-1-i) + ((i==2)?2:1);
            yellowIndex = START_OF_YELLOW_FACE + Cube.SIZE-1-i;
            orangeIndex = START_OF_ORANGE_FACE + Cube.SIZE*i - ((i==2)?1:0);

            tmp1 = c.first3Faces.charAt(whiteIndex);
            c.first3Faces = replaceChar(c.first3Faces, whiteIndex, c.first3Faces.charAt(redIndex));
            c.first3Faces = replaceChar(c.first3Faces, redIndex, c.last3Faces.charAt(yellowIndex));
            c.last3Faces = replaceChar(c.last3Faces, yellowIndex, c.last3Faces.charAt(orangeIndex));
            c.last3Faces = replaceChar(c.last3Faces, orangeIndex, tmp1);
        }
        c.last3Faces = rotateFacePrime(c.last3Faces, START_OF_GREEN_FACE, START_OF_GREEN_FACE+8);

        //corners
        Corner tmp2 = c.corners[1];
        c.corners[1] = c.corners[5];
        c.corners[5] = c.corners[6];
        c.corners[6] = c.corners[2];
        c.corners[2] = tmp2;
        //edges
        Edge tmp3 = c.edges[2];
        c.edges[2] = c.edges[5];
        c.edges[5] = c.edges[10];
        c.edges[10] = c.edges[6];
        c.edges[6] = tmp3;
    }

    /**
     * Executes the move "B2" on the cube.
     * @param c the cube to execute the move on
     */
    public static void B2(Cube c) {
        char tmp1;
        int whiteIndex, orangeIndex, yellowIndex, redIndex;
        for (int i = 0; i < Cube.SIZE; i++) {
            whiteIndex = START_OF_WHITE_FACE + Cube.SIZE*2-1+i;
            orangeIndex = START_OF_ORANGE_FACE + Cube.SIZE*i - ((i==2)?1:0);
            yellowIndex = START_OF_YELLOW_FACE + Cube.SIZE-1-i;
            redIndex = START_OF_RED_FACE + Cube.SIZE*(Cube.SIZE-1-i) + ((i==2)?2:1);

            tmp1 = c.first3Faces.charAt(whiteIndex);
            c.first3Faces = replaceChar(c.first3Faces, whiteIndex, c.last3Faces.charAt(yellowIndex));
            c.last3Faces = replaceChar(c.last3Faces, yellowIndex, tmp1);
            tmp1 = c.first3Faces.charAt(redIndex);
            c.first3Faces = replaceChar(c.first3Faces, redIndex, c.last3Faces.charAt(orangeIndex));
            c.last3Faces = replaceChar(c.last3Faces, orangeIndex, tmp1);
        }
        c.last3Faces = rotateFace2(c.last3Faces, START_OF_GREEN_FACE, START_OF_GREEN_FACE+8);

        //corners
        Corner tmp2 = c.corners[1];
        c.corners[1] = c.corners[6];
        c.corners[6] = tmp2;
        tmp2 = c.corners[2];
        c.corners[2] = c.corners[5];
        c.corners[5] = tmp2;
        //edges
        Edge tmp3 = c.edges[2];
        c.edges[2] = c.edges[10];
        c.edges[10] = tmp3;
        tmp3 = c.edges[6];
        c.edges[6] = c.edges[5];
        c.edges[5] = tmp3;
    }

    /**
     * Rotating a face of the cube clockwise.
     * @param faces The string of faces to rotate
     * @param startIndex The start index of the face in the string
     * @param endIndex The end index of the face in the string
     */
    private static String rotateFace(String faces, int startIndex, int endIndex) {
        String face = faces.substring(startIndex, endIndex);
        String rotated = "" + face.charAt(5) + face.charAt(3) + face.charAt(0) + face.charAt(6) + face.charAt(1) + face.charAt(7) + face.charAt(4) + face.charAt(2);
        return (endIndex == faces.length()) ? faces.substring(0, startIndex) + rotated : faces.substring(0, startIndex) + rotated + faces.substring(endIndex);
    }

    /**
     * Rotating a face of the cube counterclockwise.
     * @param faces The string of faces to rotate
     * @param startIndex The start index of the face in the string
     * @param endIndex The end index of the face in the string
     */
    private static String rotateFacePrime(String faces, int startIndex, int endIndex) {
        String face = faces.substring(startIndex, endIndex);
        String rotated = "" + face.charAt(2) + face.charAt(4) + face.charAt(7) + face.charAt(1) + face.charAt(6) + face.charAt(0) + face.charAt(3) + face.charAt(5);
        return (endIndex == faces.length()) ? faces.substring(0, startIndex) + rotated : faces.substring(0, startIndex) + rotated + faces.substring(endIndex);
    }

    /**
     * Rotating a face of the cube twice.
     * @param faces The string of faces to rotate
     * @param startIndex The start index of the face in the string
     * @param endIndex The end index of the face in the string
     */
    private static String rotateFace2(String faces, int startIndex, int endIndex) {
        String face = faces.substring(startIndex, endIndex);
        String rotated = "" + face.charAt(7) + face.charAt(6) + face.charAt(5) + face.charAt(4) + face.charAt(3) + face.charAt(2) + face.charAt(1) + face.charAt(0);
        return (endIndex == faces.length()) ? faces.substring(0, startIndex) + rotated : faces.substring(0, startIndex) + rotated + faces.substring(endIndex);
    }

    /**
     * Replace a char in the specific index with another char.
     * @param str The string to put the char in.
     * @param index The index of the char in the string.
     * @param c The char to put in the given index.
     * @return The new string after the replacement.
     */
    private static String replaceChar(String str, int index, char c) {
        if (index >= 0 && index < str.length())
            return str.substring(0, index) + c + str.substring(index+1);
        return null;
    }
}
