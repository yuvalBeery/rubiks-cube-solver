package enums;

import data_structures.List;

/**
 * enum that stores all the oll algorithms.
 */
public enum OllAlgorithm {
    ALGO1(new List<>(new Move[] {Move.R, Move.U2, Move.R2, Move.F, Move.R, Move.FPRIME, Move.U2, Move.RPRIME, Move.F, Move.R, Move.FPRIME})),
    ALGO2(new List<>(new Move[] {Move.L, Move.F, Move.LPRIME, Move.U2, Move.L, Move.F2, Move.RPRIME, Move.F2, Move.R, Move.FPRIME, Move.LPRIME})),
    ALGO3(new List<>(new Move[] {Move.LPRIME, Move.R2, Move.B, Move.RPRIME, Move.B, Move.L, Move.U2, Move.LPRIME, Move.B, Move.RPRIME, Move.L})),
    ALGO4(new List<>(new Move[] {Move.R, Move.LPRIME, Move.BPRIME, Move.L, Move.U2, Move.LPRIME, Move.BPRIME, Move.R, Move.BPRIME, Move.R2, Move.L})),
    ALGO5(new List<>(new Move[] {Move.RPRIME, Move.F2, Move.L, Move.F, Move.LPRIME, Move.F, Move.R})),
    ALGO6(new List<>(new Move[] {Move.L, Move.F2, Move.RPRIME, Move.FPRIME, Move.R, Move.FPRIME, Move.LPRIME})),
    ALGO7(new List<>(new Move[] {Move.L, Move.F, Move.RPRIME, Move.F, Move.R, Move.F2, Move.LPRIME})),
    ALGO8(new List<>(new Move[] {Move.RPRIME, Move.FPRIME, Move.L, Move.FPRIME, Move.LPRIME, Move.F2, Move.R})),
    ALGO9(new List<>(new Move[] {Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.RPRIME, Move.F, Move.R2, Move.U, Move.RPRIME, Move.UPRIME, Move.FPRIME})),
    ALGO10(new List<>(new Move[] {Move.R, Move.U, Move.RPRIME, Move.U, Move.RPRIME, Move.F, Move.R, Move.FPRIME, Move.R, Move.U2, Move.RPRIME})),
    ALGO11(new List<>(new Move[] {Move.L, Move.F, Move.RPRIME, Move.F, Move.RPRIME, Move.D, Move.R, Move.DPRIME, Move.R, Move.F2, Move.LPRIME})),
    ALGO12(new List<>(new Move[] {Move.RPRIME, Move.L, Move.RPRIME, Move.FPRIME, Move.R, Move.FPRIME, Move.RPRIME, Move.F2, Move.R, Move.FPRIME, Move.R, Move.LPRIME})),
    ALGO13(new List<>(new Move[] {Move.F, Move.U, Move.R, Move.UPRIME, Move.R2, Move.FPRIME, Move.R, Move.U, Move.R, Move.UPRIME, Move.RPRIME})),
    ALGO14(new List<>(new Move[] {Move.RPRIME, Move.F, Move.R, Move.U, Move.RPRIME, Move.FPRIME, Move.R, Move.F, Move.UPRIME, Move.FPRIME})),
    ALGO15(new List<>(new Move[] {Move.RPRIME, Move.FPRIME, Move.R, Move.LPRIME, Move.UPRIME, Move.L, Move.U, Move.RPRIME, Move.F, Move.R})),
    ALGO16(new List<>(new Move[] {Move.L, Move.F, Move.LPRIME, Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.L, Move.FPRIME, Move.LPRIME})),
    ALGO17(new List<>(new Move[] {Move.F, Move.RPRIME, Move.FPRIME, Move.R2, Move.LPRIME, Move.B, Move.R, Move.BPRIME, Move.RPRIME, Move.BPRIME, Move.RPRIME, Move.L})),
    ALGO18(new List<>(new Move[] {Move.L, Move.F, Move.RPRIME, Move.F, Move.R, Move.F2, Move.L2, Move.BPRIME, Move.R, Move.BPRIME, Move.RPRIME, Move.B2, Move.L})),
    ALGO19(new List<>(new Move[] {Move.LPRIME, Move.R, Move.B, Move.R, Move.B, Move.RPRIME, Move.BPRIME, Move.RPRIME, Move.L, Move.RPRIME, Move.F, Move.R, Move.FPRIME})),
    ALGO20(new List<>(new Move[] {Move.L, Move.F, Move.RPRIME, Move.FPRIME, Move.R2, Move.L2, Move.B, Move.R, Move.BPRIME, Move.RPRIME, Move.BPRIME, Move.RPRIME, Move.L})),
    ALGO21(new List<>(new Move[] {Move.R, Move.U2, Move.RPRIME, Move.UPRIME, Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.R, Move.UPRIME, Move.RPRIME})),
    ALGO22(new List<>(new Move[] {Move.R, Move.U2, Move.R2, Move.UPRIME, Move.R2, Move.UPRIME, Move.R2, Move.U2, Move.R})),
    ALGO23(new List<>(new Move[] {Move.R2, Move.DPRIME, Move.R, Move.U2, Move.RPRIME, Move.D, Move.R, Move.U2, Move.R})),
    ALGO24(new List<>(new Move[] {Move.L, Move.F, Move.RPRIME, Move.FPRIME, Move.LPRIME, Move.F, Move.R, Move.FPRIME})),
    ALGO25(new List<>(new Move[] {Move.FPRIME, Move.L, Move.F, Move.RPRIME, Move.FPRIME, Move.LPRIME, Move.F, Move.R})),
    ALGO26(new List<>(new Move[] {Move.R, Move.U2, Move.RPRIME, Move.UPRIME, Move.R, Move.UPRIME, Move.RPRIME})),
    ALGO27(new List<>(new Move[] {Move.R, Move.U, Move.RPRIME, Move.U, Move.R, Move.U2, Move.RPRIME})),
    ALGO28(new List<>(new Move[] {Move.L, Move.F, Move.RPRIME, Move.FPRIME, Move.LPRIME, Move.R, Move.U, Move.R, Move.UPRIME, Move.RPRIME})),
    ALGO29(new List<>(new Move[] {Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.R, Move.UPRIME, Move.RPRIME, Move.FPRIME, Move.UPRIME, Move.F, Move.R, Move.U, Move.RPRIME})),
    ALGO30(new List<>(new Move[] {Move.F, Move.RPRIME, Move.F, Move.R2, Move.UPRIME, Move.RPRIME, Move.UPRIME, Move.R, Move.U, Move.RPRIME, Move.F2})),
    ALGO31(new List<>(new Move[] {Move.RPRIME, Move.UPRIME, Move.F, Move.U, Move.R, Move.UPRIME, Move.RPRIME, Move.FPRIME, Move.R})),
    ALGO32(new List<>(new Move[] {Move.L, Move.U, Move.FPRIME, Move.UPRIME, Move.LPRIME, Move.U, Move.L, Move.F, Move.LPRIME})),
    ALGO33(new List<>(new Move[] {Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.RPRIME, Move.F, Move.R, Move.FPRIME})),
    ALGO34(new List<>(new Move[] {Move.R, Move.U, Move.R2, Move.UPRIME, Move.RPRIME, Move.F, Move.R, Move.U, Move.R, Move.UPRIME, Move.FPRIME})),
    ALGO35(new List<>(new Move[] {Move.R, Move.U2, Move.R2, Move.F, Move.R, Move.FPRIME, Move.R, Move.U2, Move.RPRIME})),
    ALGO36(new List<>(new Move[] {Move.LPRIME, Move.UPRIME, Move.L, Move.UPRIME,Move.LPRIME, Move.U, Move.L, Move.U, Move.L, Move.FPRIME, Move.LPRIME, Move.F})),
    ALGO37(new List<>(new Move[] {Move.F, Move.RPRIME, Move.FPRIME, Move.R, Move.U, Move.R, Move.UPRIME, Move.RPRIME})),
    ALGO38(new List<>(new Move[] {Move.R, Move.U, Move.RPRIME, Move.U, Move.R, Move.UPRIME, Move.RPRIME, Move.UPRIME, Move.RPRIME, Move.F, Move.R, Move.FPRIME})),
    ALGO39(new List<>(new Move[] {Move.L, Move.FPRIME, Move.LPRIME, Move.UPRIME, Move.L, Move.U, Move.F, Move.UPRIME, Move.LPRIME})),
    ALGO40(new List<>(new Move[] {Move.RPRIME, Move.F, Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.FPRIME, Move.U, Move.R})),
    ALGO41(new List<>(new Move[] {Move.R, Move.U, Move.RPRIME, Move.U, Move.R, Move.U2, Move.RPRIME, Move.F, Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.FPRIME})),
    ALGO42(new List<>(new Move[] {Move.RPRIME, Move.UPRIME, Move.R, Move.UPRIME, Move.RPRIME, Move.U2, Move.R, Move.F, Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.FPRIME})),
    ALGO43(new List<>(new Move[] {Move.FPRIME, Move.UPRIME, Move.LPRIME, Move.U, Move.L, Move.F})),
    ALGO44(new List<>(new Move[] {Move.F, Move.U, Move.R, Move.UPRIME, Move.RPRIME, Move.FPRIME})),
    ALGO45(new List<>(new Move[] {Move.F, Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.FPRIME})),
    ALGO46(new List<>(new Move[] {Move.RPRIME, Move.UPRIME, Move.RPRIME, Move.F, Move.R, Move.FPRIME, Move.U, Move.R})),
    ALGO47(new List<>(new Move[] {Move.RPRIME, Move.UPRIME, Move.RPRIME, Move.F, Move.R, Move.FPRIME, Move.RPRIME, Move.F, Move.R, Move.FPRIME, Move.U, Move.R})),
    ALGO48(new List<>(new Move[] {Move.F, Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.FPRIME})),
    ALGO49(new List<>(new Move[] {Move.L, Move.FPRIME, Move.L2, Move.B, Move.L2, Move.F, Move.L2, Move.BPRIME, Move.L})),
    ALGO50(new List<>(new Move[] {Move.LPRIME, Move.B, Move.L2, Move.FPRIME, Move.L2, Move.BPRIME, Move.L2, Move.F, Move.LPRIME})),
    ALGO51(new List<>(new Move[] {Move.F, Move.U, Move.R, Move.UPRIME, Move.RPRIME, Move.U, Move.R, Move.UPRIME, Move.RPRIME, Move.FPRIME})),
    ALGO52(new List<>(new Move[] {Move.R, Move.U, Move.RPRIME, Move.U, Move.R, Move.UPRIME, Move.B, Move.UPRIME, Move.BPRIME, Move.RPRIME})),
    ALGO53(new List<>(new Move[] {Move.RPRIME, Move.F2, Move.L, Move.F, Move.LPRIME, Move.FPRIME, Move.L, Move.F, Move.LPRIME, Move.F, Move.R})),
    ALGO54(new List<>(new Move[] {Move.L, Move.F2, Move.RPRIME, Move.FPRIME, Move.R, Move.F, Move.RPRIME, Move.FPRIME, Move.R, Move.FPRIME, Move.LPRIME})),
    ALGO55(new List<>(new Move[] {Move.RPRIME, Move.F, Move.R, Move.U, Move.R, Move.UPRIME, Move.R2, Move.FPRIME, Move.R2, Move.UPRIME, Move.RPRIME, Move.U, Move.R, Move.U, Move.RPRIME})),
    ALGO56(new List<>(new Move[] {Move.LPRIME, Move.BPRIME, Move.L, Move.UPRIME, Move.RPRIME, Move.U, Move.R, Move.UPRIME, Move.RPRIME, Move.U, Move.R, Move.LPRIME, Move.B, Move.L})),
    ALGO57(new List<>(new Move[] {Move.R, Move.U, Move.RPRIME, Move.UPRIME, Move.RPRIME, Move.L, Move.F, Move.R, Move.FPRIME, Move.LPRIME}));

    /**
     * the oll algorithm
     */
    private final List<Move> algo;

    OllAlgorithm(List<Move> algo) {
        this.algo = algo;
    }

    /**
     * @return the oll algorithm
     */
    public List<Move> getAlgorithm() {
        return algo;
    }

}
