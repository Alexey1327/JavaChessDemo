package ru.lanit.chess;

public class MoveVariant {

    private static final byte REGULAR_MOVE_WEIGHT = 0;
    private static final byte EAT_PAWN_WEIGHT = 1;
    private static final byte EAT_KNIGHT_WEIGHT = 2;
    private static final byte EAT_BISHOP_WEIGHT = 3;
    private static final byte EAT_ROOK_WEIGHT = 4;
    private static final byte EAT_QUEEN_WEIGHT = 5;
    // private static final byte CHANGE_PIECE_WEIGHT = 6; TODO доделать
    private static final byte EAT_KING_WEIGHT = 7;

    private int fromX, fromY, toX, toY, resultWeight;

    private MoveResult moveResult;

    private String pieceName;

    MoveResult getMoveResult() {
        return moveResult;
    }

    MoveVariant(int x, int y, int toX, int toY, int resultWeight, MoveResult moveResult, String pieceName) {
        this.fromX = x;
        this.fromY = y;
        this.toX = toX;
        this.toY = toY;
        this.resultWeight = resultWeight;
        this.moveResult = moveResult;
        this.pieceName = pieceName;
    }

    static byte getRegularMoveWeight() {
        return REGULAR_MOVE_WEIGHT;
    }

    boolean isDeadKingVariant() {
        return this.resultWeight == EAT_KING_WEIGHT;
    }

    static byte getEatWeight(AbstractPiece piece) {
        if (piece instanceof PiecePawn) {
            return EAT_PAWN_WEIGHT;
        }
        if (piece instanceof PieceKnight) {
            return EAT_KNIGHT_WEIGHT;
        }
        if (piece instanceof PieceBishop) {
            return EAT_BISHOP_WEIGHT;
        }
        if (piece instanceof PieceRook) {
            return EAT_ROOK_WEIGHT;
        }
        if (piece instanceof PieceQueen) {
            return EAT_QUEEN_WEIGHT;
        }
        if (piece instanceof PieceKing) {
            return EAT_KING_WEIGHT;
        }

        throw new GameException("Unknown Piece Type!");
    }

    @Override
    public String toString() {
        return "MoveVariant{ " + pieceName + " " +
                this.toStringShort() +
                ", resultWeight=" + resultWeight +
                ", moveResult=" + moveResult +
                '}';
    }

    String toStringShort() {
        return ChessBoard.getChessCoords(fromX, fromY) + " -> "  + ChessBoard.getChessCoords(toX, toY);
    }

    int getToX() {
        return toX;
    }

    int getToY() {
        return toY;
    }

    int getFromX() {
        return fromX;
    }

    int getFromY() {
        return fromY;
    }

    int getResultWeight() {
        return resultWeight;
    }
}
