package enums;

import data_structures.List;

/**
 * enum that stores all the pll algorithms.
 */
public enum PllAlgorithm {
    ALGO1(new List<>(new Move[] {Move.L2, Move.B2, Move.LPRIME, Move.FPRIME, Move.L, Move.B2, Move.LPRIME, Move.F, Move.LPRIME})),
    ALGO2(new List<>(new Move[] {Move.L2, Move.F2, Move.L, Move.B, Move.LPRIME, Move.F2, Move.L, Move.BPRIME, Move.L})),
    ALGO3(new List<>(new Move[] {Move.LPRIME, Move.B, Move.L, Move.FPRIME, Move.LPRIME, Move.BPRIME, Move.L, Move.F, Move.LPRIME, Move.BPRIME, Move.L, Move.FPRIME, Move.LPRIME, Move.B, Move.L, Move.F})),
    ALGO4(new List<>(new Move[] {Move.RPRIME, Move.UPRIME, Move.FPRIME, Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.RPRIME, Move.F, Move.R2, Move.UPRIME, Move.RPRIME, Move.UPRIME, Move.R, Move.U, Move.RPRIME, Move.U, Move.R})),
    ALGO5(new List<>(new Move[] {Move.R2, Move.U, Move.RPRIME, Move.U, Move.RPRIME, Move.UPRIME, Move.R, Move.UPRIME, Move.R2, Move.UPRIME, Move.D, Move.RPRIME, Move.U, Move.R, Move.DPRIME})),
    ALGO6(new List<>(new Move[] {Move.RPRIME, Move.UPRIME, Move.R, Move.U, Move.DPRIME, Move.R2, Move.U, Move.RPRIME, Move.U, Move.R, Move.UPRIME, Move.R, Move.UPRIME, Move.R2, Move.D})),
    ALGO7(new List<>(new Move[] {Move.R2, Move.UPRIME, Move.R, Move.UPRIME, Move.R, Move.U, Move.RPRIME, Move.U, Move.R2, Move.U, Move.DPRIME, Move.R, Move.UPRIME, Move.RPRIME, Move.D})),
    ALGO8(new List<>(new Move[] {Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.D, Move.R2, Move.UPRIME, Move.R, Move.UPRIME, Move.RPRIME, Move.U, Move.RPRIME, Move.U, Move.R2, Move.DPRIME})),
    ALGO9(new List<>(new Move[] {Move.R2, Move.L2, Move.D, Move.R2, Move.L2, Move.U2, Move.R2, Move.L2, Move.D, Move.R2, Move.L2})),
    ALGO10(new List<>(new Move[] {Move.R2, Move.D, Move.R, Move.DPRIME, Move.R, Move.F2, Move.LPRIME, Move.U, Move.L, Move.F2})),
    ALGO11(new List<>(new Move[] {Move.R, Move.U, Move.RPRIME, Move.FPRIME, Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.RPRIME, Move.F, Move.R2, Move.UPRIME, Move.RPRIME})),
    ALGO12(new List<>(new Move[] {Move.D, Move.R, Move.LPRIME, Move.U2, Move.B2, Move.R, Move.L, Move.UPRIME, Move.L2, Move.B2, Move.U2, Move.F2, Move.U, Move.F2, Move.R2, Move.D2, Move.L2})),
    ALGO13(new List<>(new Move[] {Move.RPRIME, Move.U, Move.R, Move.UPRIME, Move.RPRIME, Move.FPRIME, Move.UPRIME, Move.F, Move.R, Move.U, Move.RPRIME, Move.F, Move.RPRIME, Move.FPRIME, Move.R, Move.UPRIME, Move.R})),
    ALGO14(new List<>(new Move[] {Move.R, Move.UPRIME, Move.RPRIME, Move.UPRIME, Move.R, Move.U, Move.R, Move.D, Move.RPRIME, Move.UPRIME, Move.R, Move.DPRIME, Move.RPRIME, Move.U2, Move.RPRIME})),
    ALGO15(new List<>(new Move[] {Move.R2, Move.F, Move.R, Move.U, Move.R, Move.UPRIME, Move.RPRIME, Move.FPRIME, Move.R, Move.U2, Move.RPRIME, Move.U2, Move.R})),
    ALGO16(new List<>(new Move[] {Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.RPRIME, Move.F, Move.R2, Move.UPRIME, Move.RPRIME, Move.UPRIME, Move.R, Move.U, Move.RPRIME, Move.FPRIME})),
    ALGO17(new List<>(new Move[] {Move.R2, Move.L2, Move.D, Move.R, Move.LPRIME, Move.F2, Move.RPRIME, Move.L, Move.D, Move.R2, Move.L2})),
    ALGO18(new List<>(new Move[] {Move.R2, Move.L2, Move.DPRIME, Move.R, Move.LPRIME, Move.F2, Move.RPRIME, Move.L, Move.DPRIME, Move.R2, Move.L2})),
    ALGO19(new List<>(new Move[] {Move.RPRIME, Move.U, Move.RPRIME, Move.UPRIME, Move.BPRIME, Move.RPRIME, Move.B2, Move.UPRIME, Move.BPRIME, Move.U, Move.BPRIME, Move.R, Move.B, Move.R})),
    ALGO20(new List<>(new Move[] {Move.F, Move.R, Move.UPRIME, Move.RPRIME, Move.UPRIME, Move.R, Move.U, Move.RPRIME, Move.FPRIME, Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.RPRIME, Move.F, Move.R, Move.FPRIME})),
    ALGO21(new List<>(new Move[] {Move.RPRIME, Move.L, Move.F, Move.R2, Move.L2, Move.B, Move.R2, Move.L2, Move.F,  Move.RPRIME, Move.L, Move.D2, Move.R2, Move.L2}));

    /**
     * the pll algorithm
     */
    private final List<Move> algo;

    PllAlgorithm(List<Move> algo) {
        this.algo = algo;
    }

    /**
     * @return the pll algorithm
     */
    public List<Move> getAlgorithm() {
        return algo;
    }
}
