package solve;

import data_structures.Cube;
import data_structures.List;
import enums.*;
import utils.CubeUtils;
import interfaces.IsSolved;

import data_structures.Map;

/**
 * Class that contains static methods that solves the rubik's cube.
 */
public class Solve {
    private static final List<Move> allMoves = new List<>(Move.values());
    private static final List<Move> rBlockMoves = new List<>(new Move[]{Move.U, Move.UPRIME, Move.U2, Move.R, Move.RPRIME, Move.R2, Move.L, Move.LPRIME, Move.L2});
    private static final List<Move> lBlockMoves = new List<>(new Move[]{Move.U, Move.UPRIME, Move.U2, Move.L, Move.LPRIME, Move.L2});

    private static final int MAX_MAPS_DEPTH = 7;
    private static int stateSolved = -1;

    private static final Map<Cube, List<Move>> udSliceMap = new Map<>(Cube.MAX_HASH_VALUE + 1);
    private static final Map<Cube, List<Move>> rlSliceMap = new Map<>(Cube.MAX_HASH_VALUE + 1);
    private static final Map<Cube, List<Move>> fbSliceMap = new Map<>(Cube.MAX_HASH_VALUE + 1);

    private static final List<Move> udSliceMoves = new List<>(new Move[]{Move.U, Move.UPRIME, Move.U2, Move.D, Move.DPRIME, Move.D2, Move.R2, Move.L2, Move.F2, Move.B2});
    private static final List<Move> rlSliceMoves = new List<>(new Move[]{Move.R, Move.RPRIME, Move.R2, Move.L, Move.LPRIME, Move.L2, Move.U2, Move.D2, Move.F2, Move.B2});
    private static final List<Move> fbSliceMoves = new List<>(new Move[]{Move.F, Move.FPRIME, Move.F2, Move.B, Move.BPRIME, Move.B2, Move.U2, Move.D2, Move.R2, Move.L2});

    private static final IsUdSliceSolved udSlice = new IsUdSliceSolved();
    private static final IsRlSliceSolved rlSlice = new IsRlSliceSolved();
    private static final IsFbSliceSolved fbSlice = new IsFbSliceSolved();


    /**
     * Solves the cube using brute force.
     *
     * @param c The cube to solve.
     * @return the solution for the cube.
     */
    public static List<Move> bruteForce(Cube c) {
        return solve(1, 1, new List<>(), c, new IsCubeSolved(), allMoves);
    }

    /**
     * Solves the cube using the zz method.
     *
     * @param c The cube to solve
     * @return the solution for the cube.
     */
    public static List<Move> zzMethod(Cube c) {
        Cube cloned = c.clone();
        List<Move> moves = new List<>();

        List<Move> tmp = getF2lMoves(cloned);
        CubeUtils.executeAlgorithm(tmp, cloned);
        moves.addAll(tmp);

        tmp = getOllMoves(cloned);
        CubeUtils.executeAlgorithm(tmp, cloned);
        moves.addAll(tmp);
        System.out.println("oll: " + tmp);

        tmp = getPllMoves(cloned);
        CubeUtils.executeAlgorithm(tmp, cloned);
        moves.addAll(tmp);
        System.out.println("pll: " + tmp);

        IsCubeSolved ics = new IsCubeSolved();
        while (!ics.isSolved(cloned)) {
            cloned.executeMove(Move.U);
            moves.add(Move.U);
        }
        return moves;
    }


    /**
     * Solve the first two layers of the cube
     *
     * @param c the state of the cube to be solved
     * @return the solution for the f2l.
     */
    private static List<Move> getF2lMoves(Cube c) {
        Cube cloned = c.clone();
        List<Move> moves = new List<>();

        //EO line
        List<Move> tmp = solve(1, 1, new List<>(), cloned, new IsEoLineSolved(), allMoves);
        CubeUtils.executeAlgorithm(tmp, cloned);
        moves.addAll(tmp);
        System.out.println("EO line: " + tmp);

        //R block
        tmp = solve(1, 1, new List<>(), cloned, new IsRBlockSolved(), rBlockMoves);
        CubeUtils.executeAlgorithm(tmp, cloned);
        moves.addAll(tmp);
        System.out.println("R block: " + tmp);

        //L block
        tmp = solve(1, 1, new List<>(), cloned, new IsLBlockSolved(), lBlockMoves);
        CubeUtils.executeAlgorithm(tmp, cloned);
        moves.addAll(tmp);
        System.out.println("L block" + tmp);

        return moves;
    }

    /**
     * Solve the oll of a cube.
     * The f2l must be solved.
     * The function add the new moves to "moves"
     *
     * @param c The cube to solve
     * @return the solution for the oll.
     */
    private static List<Move> getOllMoves(Cube c) {
        Cube cloned = c.clone();
        List<Move> moves = new List<>();
        int algo;
        while ((algo = getOllCase(cloned)) == -1 && moves.size() < 4) {
            cloned.executeMove(Move.U);
            moves.add(Move.U);
        }
        if (moves.size() == 4)
            return new List<>();
        moves.addAll(OllAlgorithm.values()[algo - 1].getAlgorithm());
        return moves;
    }

    /**
     * Gets the oll case of a cube.
     *
     * @param c The cube to get the oll case from
     * @return The oll case number
     * See the oll cases: https://jperm.net/algs/oll
     */
    private static int getOllCase(Cube c) {
        int numOfYellows = getNumOfYellows(c);
        switch (numOfYellows) {
            case 0:
                if (c.first3Faces.charAt(8) != CubeColor.YELLOW.charValue() && c.last3Faces.charAt(0) != CubeColor.YELLOW.charValue() && c.last3Faces.charAt(2) != CubeColor.YELLOW.charValue())
                    return 1;
                else if (c.first3Faces.charAt(9) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(0) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(2) == CubeColor.YELLOW.charValue())
                    return 2;
                break;

            case 1:
                if (c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(10) == CubeColor.YELLOW.charValue())
                    return 3;
                else if (c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue())
                    return 4;
                break;

            case 2:
                if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue())
                    return 17;
                else if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue())
                    return 18;
                else if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) != CubeColor.YELLOW.charValue())
                    return 19;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(2) == CubeColor.YELLOW.charValue())
                    return 47;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(8) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(10) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(0) == CubeColor.YELLOW.charValue())
                    return 48;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(16) != CubeColor.YELLOW.charValue() && c.first3Faces.charAt(18) != CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) != CubeColor.YELLOW.charValue())
                    return 49;
                else if (c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(22) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(16) != CubeColor.YELLOW.charValue() && c.first3Faces.charAt(18) != CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) != CubeColor.YELLOW.charValue())
                    return 50;
                else if (c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(10) != CubeColor.YELLOW.charValue())
                    return 51;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(22) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(10) != CubeColor.YELLOW.charValue())
                    return 52;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(0) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(2) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(16) != CubeColor.YELLOW.charValue())
                    return 53;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(0) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(2) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(10) != CubeColor.YELLOW.charValue())
                    return 54;
                else if (c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(10) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(0) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(2) == CubeColor.YELLOW.charValue())
                    return 55;
                else if (c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) != CubeColor.YELLOW.charValue() && c.first3Faces.charAt(10) != CubeColor.YELLOW.charValue() && c.last3Faces.charAt(0) != CubeColor.YELLOW.charValue() && c.last3Faces.charAt(2) != CubeColor.YELLOW.charValue())
                    return 56;
                break;

            case 3:
                if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(10) == CubeColor.YELLOW.charValue())
                    return 5;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue())
                    return 6;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue())
                    return 7;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(0) == CubeColor.YELLOW.charValue())
                    return 8;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue())
                    return 9;
                else if (c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(22) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(10) == CubeColor.YELLOW.charValue())
                    return 10;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(2) == CubeColor.YELLOW.charValue())
                    return 11;
                else if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue())
                    return 12;
                else if (c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(18) == CubeColor.YELLOW.charValue())
                    return 13;
                else if (c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(16) != CubeColor.YELLOW.charValue())
                    return 14;
                else if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(10) == CubeColor.YELLOW.charValue())
                    return 15;
                else if (c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(16) == CubeColor.YELLOW.charValue())
                    return 16;
                break;

            case 4:
                if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue())
                    return 20;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(22) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(10) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(18) != CubeColor.YELLOW.charValue())
                    return 21;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(22) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(8) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(10) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(10) == CubeColor.YELLOW.charValue())
                    return 22;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue())
                    return 29;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(0) != CubeColor.YELLOW.charValue())
                    return 30;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue())
                    return 31;
                else if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(10) == CubeColor.YELLOW.charValue())
                    return 32;
                else if (c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue())
                    return 33;
                else if (c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(18) == CubeColor.YELLOW.charValue())
                    return 34;
                else if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(22) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue())
                    return 35;
                else if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) != CubeColor.YELLOW.charValue())
                    return 36;
                else if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue())
                    return 37;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(10) != CubeColor.YELLOW.charValue())
                    return 38;
                else if (c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue())
                    return 39;
                else if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue())
                    return 40;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(0) == CubeColor.YELLOW.charValue())
                    return 41;
                else if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(22) == CubeColor.YELLOW.charValue())
                    return 42;
                else if (c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) != CubeColor.YELLOW.charValue())
                    return 43;
                else if (c.last3Faces.charAt(16) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(17) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(10) != CubeColor.YELLOW.charValue())
                    return 44;
                else if (c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) != CubeColor.YELLOW.charValue())
                    return 45;
                else if (c.last3Faces.charAt(19) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(20) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) == CubeColor.YELLOW.charValue() && c.first3Faces.charAt(18) != CubeColor.YELLOW.charValue())
                    return 46;

                break;
            case 5:
                if (c.last3Faces.charAt(18) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(2) != CubeColor.YELLOW.charValue())
                    return 26;
                else if (c.last3Faces.charAt(21) == CubeColor.YELLOW.charValue() && c.last3Faces.charAt(8) != CubeColor.YELLOW.charValue())
                    return 27;
                break;

            case 6:
                if (c.last3Faces.charAt(16) != CubeColor.YELLOW.charValue() && c.last3Faces.charAt(18) != CubeColor.YELLOW.charValue() && c.last3Faces.charAt(0) == CubeColor.YELLOW.charValue())
                    return 23;
                else if (c.last3Faces.charAt(16) != CubeColor.YELLOW.charValue() && c.last3Faces.charAt(21) != CubeColor.YELLOW.charValue() && c.first3Faces.charAt(8) == CubeColor.YELLOW.charValue())
                    return 24;
                else if (c.last3Faces.charAt(16) != CubeColor.YELLOW.charValue() && c.last3Faces.charAt(23) != CubeColor.YELLOW.charValue() && c.first3Faces.charAt(10) == CubeColor.YELLOW.charValue())
                    return 25;
                else if (c.last3Faces.charAt(20) != CubeColor.YELLOW.charValue() && c.last3Faces.charAt(22) != CubeColor.YELLOW.charValue())
                    return 28;
                else if (c.last3Faces.charAt(17) != CubeColor.YELLOW.charValue() && c.last3Faces.charAt(22) != CubeColor.YELLOW.charValue())
                    return 57;
                break;
        }
        return -1;
    }

    /**
     * @param c The cube to get the number of yellow facelets from.
     * @return The number of yellow facelets in the yellow layer
     */
    private static int getNumOfYellows(Cube c) {
        int counter = 0;
        for (int i = 16; i < c.last3Faces.length(); i++) {
            if (c.last3Faces.charAt(i) == CubeColor.YELLOW.charValue())
                counter++;
        }
        return counter;
    }

    /**
     * Solve the pll of a cube.
     * The f2l and oll must be solved
     * The function add the new moves to "moves"
     *
     * @param c The cube to solve
     * @return The solution for the pll.
     */
    private static List<Move> getPllMoves(Cube c) {
        Cube cloned = c.clone();
        List<Move> moves = new List<>();
        int algo;
        while ((algo = getPllCase(cloned)) == -1 && moves.size() < 4) {
            cloned.executeMove(Move.U);
            moves.add(Move.U);
        }
        if (moves.size() == 4)
            return new List<>();
        moves.addAll(PllAlgorithm.values()[algo - 1].getAlgorithm());
        return moves;
    }

    /**
     * Gets the pll case of a cube.
     *
     * @param c The cube to get the oll case from
     * @return The pll case number
     * see the pll cases: https://jperm.net/algs/pll
     */
    private static int getPllCase(Cube c) {
        if (c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) != c.last3Faces.charAt(9) && c.first3Faces.charAt(9) == c.first3Faces.charAt(10) && c.first3Faces.charAt(16) == c.first3Faces.charAt(17))
            return 1;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) != c.last3Faces.charAt(9) && c.first3Faces.charAt(17) == c.first3Faces.charAt(18) && c.first3Faces.charAt(17) == c.first3Faces.charAt(18) && c.last3Faces.charAt(0) == c.last3Faces.charAt(1))
            return 2;
        else if (c.first3Faces.charAt(8) == c.last3Faces.charAt(2) && c.first3Faces.charAt(8) == c.last3Faces.charAt(9) && c.first3Faces.charAt(10) == c.last3Faces.charAt(0) && c.first3Faces.charAt(10) == c.first3Faces.charAt(17))
            return 3;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(9) && c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.first3Faces.charAt(10) == c.last3Faces.charAt(0) && c.first3Faces.charAt(10) == c.first3Faces.charAt(17))
            return 4;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) != c.last3Faces.charAt(9) && c.first3Faces.charAt(9) == c.first3Faces.charAt(10) && c.first3Faces.charAt(8) != c.first3Faces.charAt(9) && c.first3Faces.charAt(8) != c.last3Faces.charAt(9))
            return 5;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) != c.last3Faces.charAt(9) && c.first3Faces.charAt(17) == c.first3Faces.charAt(18) && c.first3Faces.charAt(16) != c.first3Faces.charAt(17) && c.first3Faces.charAt(16) != c.last3Faces.charAt(9))
            return 6;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) != c.last3Faces.charAt(9) && c.last3Faces.charAt(0) == c.last3Faces.charAt(1) && c.last3Faces.charAt(1) != c.last3Faces.charAt(2) && c.first3Faces.charAt(8) == c.last3Faces.charAt(9))
            return 7;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) != c.last3Faces.charAt(9) && c.first3Faces.charAt(16) == c.first3Faces.charAt(17) && c.first3Faces.charAt(16) != c.first3Faces.charAt(18) && c.first3Faces.charAt(18) == c.last3Faces.charAt(1))
            return 8;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) == c.first3Faces.charAt(17) && c.first3Faces.charAt(16) == c.first3Faces.charAt(18) && c.first3Faces.charAt(16) == c.last3Faces.charAt(9))
            return 9;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(9) && c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.first3Faces.charAt(8) == c.first3Faces.charAt(9) && c.first3Faces.charAt(8) != c.first3Faces.charAt(10))
            return 10;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(9) && c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.first3Faces.charAt(9) == c.first3Faces.charAt(10) && c.first3Faces.charAt(9) != c.first3Faces.charAt(8))
            return 11;
        else if (c.last3Faces.charAt(9) == c.last3Faces.charAt(10) && c.last3Faces.charAt(9) == c.first3Faces.charAt(16) && c.last3Faces.charAt(8) == c.first3Faces.charAt(17) && c.first3Faces.charAt(17) == c.first3Faces.charAt(18))
            return 12;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(9) && c.last3Faces.charAt(8) == c.first3Faces.charAt(18) && c.last3Faces.charAt(10) == c.first3Faces.charAt(16) && c.first3Faces.charAt(16) == c.first3Faces.charAt(17))
            return 13;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) != c.last3Faces.charAt(9) && c.first3Faces.charAt(8) == c.first3Faces.charAt(9) && c.first3Faces.charAt(8) != c.first3Faces.charAt(10) && c.first3Faces.charAt(10) != c.last3Faces.charAt(9))
            return 14;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) != c.last3Faces.charAt(9) && c.last3Faces.charAt(1) == c.last3Faces.charAt(2) && c.last3Faces.charAt(1) != c.last3Faces.charAt(0) && c.first3Faces.charAt(8) != c.first3Faces.charAt(9))
            return 15;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) != c.last3Faces.charAt(9) && c.last3Faces.charAt(1) == c.last3Faces.charAt(2) && c.last3Faces.charAt(1) != c.last3Faces.charAt(0) && c.first3Faces.charAt(8) == c.first3Faces.charAt(9))
            return 16;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) == c.first3Faces.charAt(17) && c.last3Faces.charAt(9) == c.first3Faces.charAt(8) && c.first3Faces.charAt(8) == c.first3Faces.charAt(10) && c.first3Faces.charAt(16) == c.first3Faces.charAt(18) && c.first3Faces.charAt(16) == c.first3Faces.charAt(9))
            return 17;
        else if (c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) == c.first3Faces.charAt(9) && c.first3Faces.charAt(8) == c.first3Faces.charAt(10) && c.first3Faces.charAt(8) == c.first3Faces.charAt(17) && c.first3Faces.charAt(16) == c.first3Faces.charAt(18) && c.first3Faces.charAt(16) == c.last3Faces.charAt(9))
            return 18;
        else if (c.first3Faces.charAt(8) == c.first3Faces.charAt(9) && c.first3Faces.charAt(8) == c.last3Faces.charAt(2) && c.first3Faces.charAt(10) == c.first3Faces.charAt(17) && c.first3Faces.charAt(10) == c.last3Faces.charAt(0))
            return 19;
        else if (c.first3Faces.charAt(8) == c.first3Faces.charAt(9) && c.first3Faces.charAt(8) != c.first3Faces.charAt(10) && c.first3Faces.charAt(17) == c.first3Faces.charAt(18) && c.first3Faces.charAt(17) != c.first3Faces.charAt(16))
            return 20;
        else if (c.first3Faces.charAt(8) == c.first3Faces.charAt(10) && c.first3Faces.charAt(8) == c.last3Faces.charAt(9) && c.last3Faces.charAt(8) == c.last3Faces.charAt(10) && c.last3Faces.charAt(8) == c.first3Faces.charAt(9))
            return 21;

        return -1;
    }

    /**
     * Solves the cube using kociembas method
     * @param c the cube to solve
     * @return the solution for the cube
     */
    public static List<Move> kociembasMethod(Cube c) {
        List<Move> moves;
        List<Move> tmp;
        //solves state1
        moves = solveState1(1, 1, new List<>(), c);

        System.out.println("state1 moves: " + moves);
        CubeUtils.executeAlgorithm(moves, c);

        Map<Cube, List<Move>> map;
        List<Move> restrictMoves;

        if (stateSolved == 0) {
            map = udSliceMap;
            restrictMoves = udSliceMoves;
            System.out.println("ud slice solved");
        } else if (stateSolved == 1) {
            map = rlSliceMap;
            restrictMoves = rlSliceMoves;
            System.out.println("rl slice solved");
        } else {
            map = fbSliceMap;
            restrictMoves = fbSliceMoves;
            System.out.println("fb slice solved");
        }

        //init the map
        System.out.println("map init started");
        initMap(1, 1, new List<>(), new Cube(), restrictMoves, map);
        System.out.println("map init ended");


        //solves state2
        tmp = solveState2(1, 1, new List<>(), c, restrictMoves, map);
        System.out.println("state2 moves: " + tmp);
        moves.addAll(tmp);
        return moves;
    }

    /**
     * solves the first state of the cube in kociembas method
     * @param currDepth the current inner depth of the recursion.
     * @param depth the depth of the recursion.
     * @param moves the moves that was executed on the cube.
     * @param c The cube to solve.
     * @return the solution for the first state in kociembas method.
     */
    private static List<Move> solveState1(int currDepth, int depth, List<Move> moves, Cube c) {
        if (udSlice.isSolved(c) || rlSlice.isSolved(c) || fbSlice.isSolved(c)) {
            if (udSlice.isSolved(c))
                stateSolved = 0;
            else if (rlSlice.isSolved(c))
                stateSolved = 1;
            else
                stateSolved = 2;
            return moves;
        }
        if (currDepth == 0)
            return null;
        for (Move move : Move.values()) {
            if (CubeUtils.isMoveValid(moves, move)) {
                List<Move> newMoves = moves.clone();
                newMoves.add(move);
                Cube newCube = c.clone();
                newCube.executeMove(move);
                newMoves = solveState1(currDepth - 1, depth, newMoves, newCube);
                if (newMoves != null)
                    return newMoves;
            }
        }
        if (currDepth == depth)
            return solveState1(depth + 1, depth + 1, moves, c);
        return null;
    }

    /**
     * solves the second state in kociembas method
     * @param currDepth the current inner depth of the recursion.
     * @param depth the depth of the recursion.
     * @param moves the moves that was executed on the cube.
     * @param c The cube to solve.
     * @param restrictMoves the move to restrict the function to search.
     * @param map a map for the meet in the midle
     * @return the solution for the second state in kociembas method
     */
    private static List<Move> solveState2(int currDepth, int depth, List<Move> moves, Cube c, List<Move> restrictMoves, Map<Cube, List<Move>> map) {
        if (map.containsKey(c)) {
            moves.addAll(map.get(c));
            return moves;
        }
        if (currDepth == 0)
            return null;

        for (Move move : restrictMoves) {
            if (CubeUtils.isMoveValid(moves, move)) {
                List<Move> newMoves = moves.clone();
                newMoves.add(move);
                Cube newCube = c.clone();
                newCube.executeMove(move);
                newMoves = solveState2(currDepth - 1, depth, newMoves, newCube, restrictMoves, map);
                if (newMoves != null)
                    return newMoves;
            }
        }

        if (currDepth == depth)
            return solveState2(depth + 1, depth + 1, moves, c, restrictMoves, map);
        return null;
    }

    /**
     * initiate the map for meet in the midle
     * @param currDepth the current inner depth of the recursion.
     * @param depth the depth of the recursion.
     * @param moves the moves that was executed on the cube.
     * @param c The cube to solve.
     * @param restrictMoves the move to restrict the function to search.
     * @param map a map for the meet in the midle
     */
    private static void initMap(int currDepth, int depth, List<Move> moves, Cube c, List<Move> restrictMoves, Map<Cube, List<Move>> map) {
        if (currDepth == 0)
            return;

        for (Move move : restrictMoves) {
            if (CubeUtils.isMoveValid(moves, move)) {
                List<Move> newMoves = moves.clone();
                newMoves.add(move);
                Cube newCube = c.clone();
                newCube.executeMove(move);
                if (currDepth == 1)
                    map.putIfAbsent(c, CubeUtils.reverseAlgorithm(moves));
                initMap(currDepth - 1, depth, newMoves, newCube, restrictMoves, map);
            }
        }

        if (currDepth == depth && depth < MAX_MAPS_DEPTH)
            initMap(depth + 1, depth + 1, moves, c, restrictMoves, map);
    }

    /**
     * Solves the cube using brut force.
     * The function wil restrict the moves to search to the given moves
     *
     * @param currDepth the current inner depth of the recursion.
     * @param depth the depth of the recursion.
     * @param moves the moves that was executed on the cube.
     * @param c The cube to solve.
     * @param isSolved The function that check if the cube is solved
     * @param restrictMoves the move to restrict the function to search.
     * @return the solution for the cube.
     */
    private static List<Move> solve(int currDepth, int depth, List<Move> moves, Cube c, IsSolved isSolved, List<Move> restrictMoves) {
        if (isSolved.isSolved(c))
            return moves;
        if (currDepth == 0)
            return null;
        for (Move move : restrictMoves) {
            if (CubeUtils.isMoveValid(moves, move)) {
                List<Move> newMoves = moves.clone();
                newMoves.add(move);
                Cube newCube = c.clone();
                newCube.executeMove(move);
                newMoves = solve(currDepth - 1, depth, newMoves, newCube, isSolved, restrictMoves);
                if (newMoves != null)
                    return newMoves;
            }
        }
        if (currDepth == depth) {
            System.out.println("depth: " + depth);
            return solve(depth + 1, depth + 1, moves, c, isSolved, restrictMoves);
        } else
            return null;
    }
}