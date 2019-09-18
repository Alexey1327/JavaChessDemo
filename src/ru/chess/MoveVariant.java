package ru.chess;

public class MoveVariant {

    public static final byte REGULAR_MOVE_WEIGHT = 0;
    public static final byte EAT_PAWN_WEIGHT = 1;
    public static final byte EAT_KNIGHT_WEIGHT = 2;
    public static final byte EAT_BISHOP_WEIGHT = 3;
    public static final byte EAT_ROOK_WEIGHT = 4;
    public static final byte EAT_QUEEN_WEIGHT = 5;
    public static final byte CHANGE_PIECE_WEIGHT = 6;
    public static final byte EAT_KING_WEIGHT = 7;

    private int fromX, fromY, toX, toY, resultWeight;

    private MoveResult moveResult;

    private char symbol;

    public MoveResult getMoveResult() {
        return moveResult;
    }

    public MoveVariant(int x, int y, int toX, int toY, int resultWeight, MoveResult moveResult, char symbol) {
        this.fromX = x;
        this.fromY = y;
        this.toX = toX;
        this.toY = toY;
        this.resultWeight = resultWeight;
        this.moveResult = moveResult;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "MoveVariant{ " + symbol + " " +
                this.toStringShort() +
                ", resultWeight=" + resultWeight +
                ", moveResult=" + moveResult +
                '}';
    }

    public String toStringShort() {
        return AbstractPiece.getChessCoords(fromX, fromY) + " -> "  + AbstractPiece.getChessCoords(toX, toY);
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }

    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getResultWeight() {
        return resultWeight;
    }
}
