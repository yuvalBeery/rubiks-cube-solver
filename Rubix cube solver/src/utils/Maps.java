package utils;

import data_structures.*;
import enums.Corner;
import enums.Edge;
import enums.Move;
import enums.CubeColor;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Class that contains all the Maps of the program.
 * @see Map
 */
public class Maps {
    /**
     * Map that maps each move with his opposite move.
     * for example: U -> U'
     */
    public static Map<Move, Move> oppositeMoveMap;
    /**
     * Map that maps each move with his parallel move;
     * for example: B->F, R2->L, U'->D
     */
    public static Map<Move, Move> parallelMoveMap;
    /**
     * Map that maps each move with his priority.
     */
    public static Map<Move, Integer> priorityMap;
    /**
     * Map that maps a move function with the move name.
     * The move function executes the move on the specified cube.
     */
    public static Map<Move, Consumer<Cube>> executeMoveMap;
    /**
     *
     */
    public static Map<Corner, Map<CubeColor, Integer>> cornerPermutationMap;
    /**
     *
     */
    public static Map<Edge, Map<CubeColor, Integer>> edgePermutationMap;
    /**
     *
     */
    public static Map<Corner, Function<Cube, CubeColor>> significantCornerColorMap;
    /**
     *
     */
    public static Map<Edge, Function<Cube, CubeColor>> significantEdgeColorMap;

    //construct Maps by colling Map constructor
    static {
        initOppositeMoveMap();
        initParallelMoveMap();
        initPriorityMap();
        initExecuteMoveMap();
        initCornerPermutationMap();
        initEdgePermutationMap();
        initSignificantCornerColorMap();
        initSignificantEdgeColorMap();
    }

    /**
     * initialize oppositeMoveMap map
     */
    private static void initOppositeMoveMap() {
        oppositeMoveMap = new Map<>(Move.values().length);

        oppositeMoveMap.put(Move.U, Move.UPRIME);
        oppositeMoveMap.put(Move.UPRIME, Move.U);
        oppositeMoveMap.put(Move.U2, Move.U2);
        oppositeMoveMap.put(Move.D, Move.DPRIME);
        oppositeMoveMap.put(Move.DPRIME, Move.D);
        oppositeMoveMap.put(Move.D2, Move.D2);
        oppositeMoveMap.put(Move.R, Move.RPRIME);
        oppositeMoveMap.put(Move.RPRIME, Move.R);
        oppositeMoveMap.put(Move.R2, Move.R2);
        oppositeMoveMap.put(Move.L, Move.LPRIME);
        oppositeMoveMap.put(Move.LPRIME, Move.L);
        oppositeMoveMap.put(Move.L2, Move.L2);
        oppositeMoveMap.put(Move.F, Move.FPRIME);
        oppositeMoveMap.put(Move.FPRIME, Move.F);
        oppositeMoveMap.put(Move.F2, Move.F2);
        oppositeMoveMap.put(Move.B, Move.BPRIME);
        oppositeMoveMap.put(Move.BPRIME, Move.B);
        oppositeMoveMap.put(Move.B2, Move.B2);
    }

    /**
     * initialize parallelMoveMap map
     */
    private static void initParallelMoveMap() {
        parallelMoveMap = new Map<>(Move.values().length);
        parallelMoveMap.put(Move.U, Move.D);
        parallelMoveMap.put(Move.UPRIME, Move.D);
        parallelMoveMap.put(Move.U2, Move.D);
        parallelMoveMap.put(Move.D, Move.U);
        parallelMoveMap.put(Move.DPRIME, Move.U);
        parallelMoveMap.put(Move.D2, Move.U);
        parallelMoveMap.put(Move.R, Move.L);
        parallelMoveMap.put(Move.RPRIME, Move.L);
        parallelMoveMap.put(Move.R2, Move.L);
        parallelMoveMap.put(Move.L, Move.R);
        parallelMoveMap.put(Move.LPRIME, Move.R);
        parallelMoveMap.put(Move.L2, Move.R);
        parallelMoveMap.put(Move.F, Move.B);
        parallelMoveMap.put(Move.FPRIME, Move.B);
        parallelMoveMap.put(Move.F2, Move.B);
        parallelMoveMap.put(Move.B, Move.F);
        parallelMoveMap.put(Move.BPRIME, Move.F);
        parallelMoveMap.put(Move.B2, Move.F);
    }

    /**
     * initialize priorityMap map
     */
    private static void initPriorityMap() {
        priorityMap = new Map<>(Move.values().length);

        priorityMap.put(Move.U, 1);
        priorityMap.put(Move.UPRIME, 1);
        priorityMap.put(Move.U2, 1);
        priorityMap.put(Move.D, 0);
        priorityMap.put(Move.DPRIME, 0);
        priorityMap.put(Move.D2, 0);
        priorityMap.put(Move.R, 1);
        priorityMap.put(Move.RPRIME, 1);
        priorityMap.put(Move.R2, 1);
        priorityMap.put(Move.L, 0);
        priorityMap.put(Move.LPRIME, 0);
        priorityMap.put(Move.L2, 0);
        priorityMap.put(Move.F, 1);
        priorityMap.put(Move.FPRIME, 1);
        priorityMap.put(Move.F2, 1);
        priorityMap.put(Move.B, 0);
        priorityMap.put(Move.BPRIME, 0);
        priorityMap.put(Move.B2, 0);
    }

    /**
     * initialize cornerPermutationMap map
     */
    private static void initExecuteMoveMap() {
        executeMoveMap = new Map<>(Move.values().length);

        executeMoveMap.put(Move.U, Moves::U);
        executeMoveMap.put(Move.UPRIME, Moves::UPrime);
        executeMoveMap.put(Move.U2, Moves::U2);
        executeMoveMap.put(Move.D, Moves::D);
        executeMoveMap.put(Move.DPRIME, Moves::DPrime);
        executeMoveMap.put(Move.D2, Moves::D2);
        executeMoveMap.put(Move.R, Moves::R);
        executeMoveMap.put(Move.RPRIME, Moves::RPrime);
        executeMoveMap.put(Move.R2, Moves::R2);
        executeMoveMap.put(Move.L, Moves::L);
        executeMoveMap.put(Move.LPRIME, Moves::LPrime);
        executeMoveMap.put(Move.L2, Moves::L2);
        executeMoveMap.put(Move.F, Moves::F);
        executeMoveMap.put(Move.FPRIME, Moves::FPrime);
        executeMoveMap.put(Move.F2, Moves::F2);
        executeMoveMap.put(Move.B, Moves::B);
        executeMoveMap.put(Move.BPRIME, Moves::BPrime);
        executeMoveMap.put(Move.B2, Moves::B2);
    }

    /**
     * initialize executeMoveMap map
     */
    private static void initCornerPermutationMap() {
        cornerPermutationMap = new Map<>(Corner.values().length);
        Map<CubeColor, Integer> tmp = new Map<>(Corner.values().length);

        //DRF
        tmp.put(CubeColor.WHITE, 0);
        tmp.put(CubeColor.RED, 1);
        tmp.put(CubeColor.BLUE, 2);
        cornerPermutationMap.put(Corner.DRF, tmp);

        //DRB
        tmp = new Map<>(Corner.values().length);
        tmp.put(CubeColor.WHITE, 0);
        tmp.put(CubeColor.RED, 1);
        tmp.put(CubeColor.GREEN, 2);
        cornerPermutationMap.put(Corner.DRB, tmp);

        //DLB
        tmp = new Map<>(Corner.values().length);
        tmp.put(CubeColor.WHITE, 0);
        tmp.put(CubeColor.ORANGE, 1);
        tmp.put(CubeColor.GREEN, 2);
        cornerPermutationMap.put(Corner.DLB, tmp);

        //DLF
        tmp = new Map<>(Corner.values().length);
        tmp.put(CubeColor.WHITE, 0);
        tmp.put(CubeColor.ORANGE, 1);
        tmp.put(CubeColor.BLUE, 2);
        cornerPermutationMap.put(Corner.DLF, tmp);

        //URF
        tmp = new Map<>(Corner.values().length);
        tmp.put(CubeColor.YELLOW, 0);
        tmp.put(CubeColor.RED, 1);
        tmp.put(CubeColor.BLUE, 2);
        cornerPermutationMap.put(Corner.URF, tmp);

        //URB
        tmp = new Map<>(Corner.values().length);
        tmp.put(CubeColor.YELLOW, 0);
        tmp.put(CubeColor.RED, 1);
        tmp.put(CubeColor.GREEN, 2);
        cornerPermutationMap.put(Corner.URB, tmp);

        //ULB
        tmp = new Map<>(Corner.values().length);
        tmp.put(CubeColor.YELLOW, 0);
        tmp.put(CubeColor.ORANGE, 1);
        tmp.put(CubeColor.GREEN, 2);
        cornerPermutationMap.put(Corner.ULB, tmp);

        //ULF
        tmp = new Map<>(Corner.values().length);
        tmp.put(CubeColor.YELLOW, 0);
        tmp.put(CubeColor.ORANGE, 1);
        tmp.put(CubeColor.BLUE, 2);
        cornerPermutationMap.put(Corner.ULF, tmp);
    }

    /**
     * initialize edgePermutationMap map
     */
    private static void initEdgePermutationMap() {
        edgePermutationMap = new Map<>(Edge.values().length);
        Map<CubeColor, Integer> tmp = new Map<>(Edge.values().length);

        //DF
        tmp.put(CubeColor.WHITE, 0);
        tmp.put(CubeColor.BLUE, 1);
        edgePermutationMap.put(Edge.DF, tmp);

        //DR
        tmp = new Map<>(Edge.values().length);
        tmp.put(CubeColor.WHITE, 0);
        tmp.put(CubeColor.RED, 1);
        edgePermutationMap.put(Edge.DR, tmp);

        //DB
        tmp = new Map<>(Edge.values().length);
        tmp.put(CubeColor.WHITE, 0);
        tmp.put(CubeColor.GREEN, 1);
        edgePermutationMap.put(Edge.DB, tmp);

        //DL
        tmp = new Map<>(Edge.values().length);
        tmp.put(CubeColor.WHITE, 0);
        tmp.put(CubeColor.ORANGE, 1);
        edgePermutationMap.put(Edge.DL, tmp);

        //RF
        tmp = new Map<>(Edge.values().length);
        tmp.put(CubeColor.BLUE, 0);
        tmp.put(CubeColor.RED, 1);
        edgePermutationMap.put(Edge.RF, tmp);

        //RB
        tmp = new Map<>(Edge.values().length);
        tmp.put(CubeColor.GREEN, 0);
        tmp.put(CubeColor.RED, 1);
        edgePermutationMap.put(Edge.RB, tmp);

        //LB
        tmp = new Map<>(Edge.values().length);
        tmp.put(CubeColor.GREEN, 0);
        tmp.put(CubeColor.ORANGE, 1);
        edgePermutationMap.put(Edge.LB, tmp);

        //LF
        tmp = new Map<>(Edge.values().length);
        tmp.put(CubeColor.BLUE, 0);
        tmp.put(CubeColor.ORANGE, 1);
        edgePermutationMap.put(Edge.LF, tmp);

        //UF
        tmp = new Map<>(Edge.values().length);
        tmp.put(CubeColor.YELLOW, 0);
        tmp.put(CubeColor.BLUE, 1);
        edgePermutationMap.put(Edge.UF, tmp);

        //UR
        tmp = new Map<>(Edge.values().length);
        tmp.put(CubeColor.YELLOW, 0);
        tmp.put(CubeColor.RED, 1);
        edgePermutationMap.put(Edge.UR, tmp);

        //UB
        tmp = new Map<>(Edge.values().length);
        tmp.put(CubeColor.YELLOW, 0);
        tmp.put(CubeColor.GREEN, 1);
        edgePermutationMap.put(Edge.UB, tmp);

        //UL
        tmp = new Map<>(Edge.values().length);
        tmp.put(CubeColor.YELLOW, 0);
        tmp.put(CubeColor.ORANGE, 1);
        edgePermutationMap.put(Edge.UL, tmp);
    }

    /**
     * initialize significantCornerColorMap map
     */
    private static void initSignificantCornerColorMap() {
        significantCornerColorMap = new Map<>(Corner.values().length);

        significantCornerColorMap.put(Corner.DRF, SignificantColor::DRF);
        significantCornerColorMap.put(Corner.DRB, SignificantColor::DRB);
        significantCornerColorMap.put(Corner.DLB, SignificantColor::DLB);
        significantCornerColorMap.put(Corner.DLF, SignificantColor::DLF);
        significantCornerColorMap.put(Corner.URF, SignificantColor::URF);
        significantCornerColorMap.put(Corner.URB, SignificantColor::URB);
        significantCornerColorMap.put(Corner.ULB, SignificantColor::ULB);
        significantCornerColorMap.put(Corner.ULF, SignificantColor::ULF);
    }

    /**
     * initialize significantEdgeColorMap map
     */
    private static void initSignificantEdgeColorMap() {
        significantEdgeColorMap = new Map<>(Edge.values().length);

        significantEdgeColorMap.put(Edge.DF, SignificantColor::DF);
        significantEdgeColorMap.put(Edge.DR, SignificantColor::DR);
        significantEdgeColorMap.put(Edge.DB, SignificantColor::DB);
        significantEdgeColorMap.put(Edge.DL, SignificantColor::DL);
        significantEdgeColorMap.put(Edge.RF, SignificantColor::RF);
        significantEdgeColorMap.put(Edge.RB, SignificantColor::RB);
        significantEdgeColorMap.put(Edge.LB, SignificantColor::LB);
        significantEdgeColorMap.put(Edge.LF, SignificantColor::LF);
        significantEdgeColorMap.put(Edge.UF, SignificantColor::UF);
        significantEdgeColorMap.put(Edge.UR, SignificantColor::UR);
        significantEdgeColorMap.put(Edge.UB, SignificantColor::UB);
        significantEdgeColorMap.put(Edge.UL, SignificantColor::UL);
    }
}
