package ru.lanit.chess;

public class MoveVariant {

    private static final byte REGULAR_MOVE_WEIGHT = 0;
    private static final byte EAT_PAWN_WEIGHT = 1;
    private static final byte EAT_KNIGHT_WEIGHT = 2;
    private static final byte EAT_BISHOP_WEIGHT = 3;
    private static final byte EAT_ROOK_WEIGHT = 4;
    private static final byte EAT_QUEEN_WEIGHT = 5;
    private static final byte PROMOTION_PAWN_WEIGHT = 6;
    private static final byte PROMOTION_EAT_PAWN_WEIGHT = 7;
    private static final byte EAT_KING_WEIGHT = 8;

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

    void setCheckMateResult() {
        this.moveResult = MoveResult.CHECK_MATE;
    }

    static byte calculateDestinationMoveWeight(AbstractPiece piece) {
        if (piece instanceof PiecePawn) {
            if (piece.getY() == 6 && piece.getColor() == PieceColor.WHITE || piece.getY() == 1 && piece.getColor() == PieceColor.BLACK) {
                return PROMOTION_PAWN_WEIGHT;
            }
        }
        return REGULAR_MOVE_WEIGHT;
    }

    boolean isDeadKingVariant() {
        return this.resultWeight == EAT_KING_WEIGHT;
    }


    static byte calculateEatWeight(AbstractPiece piece1, AbstractPiece piece2) {
        if (piece1 instanceof PiecePawn) {
            if (piece2.getY() == 7 || piece2.getY() == 0) {
                return PROMOTION_EAT_PAWN_WEIGHT;
            }
        }
        if (piece2 instanceof PieceKnight) {
            return EAT_KNIGHT_WEIGHT;
        }
        if (piece2 instanceof PieceBishop) {
            return EAT_BISHOP_WEIGHT;
        }
        if (piece2 instanceof PieceRook) {
            return EAT_ROOK_WEIGHT;
        }
        if (piece2 instanceof PieceQueen) {
            return EAT_QUEEN_WEIGHT;
        }
        if (piece2 instanceof PieceKing) {
            return EAT_KING_WEIGHT;
        }
        if (piece2 instanceof PiecePawn) {
            return EAT_PAWN_WEIGHT;
        }

        throw new GameException("Error calculateEatWeight! " + piece1 + piece2);
    }


    static AbstractPiece getBestPieceForChange(AbstractPiece[] playerPieces) {
        // priority Queen, Rook, Bishop, Knight
        for (AbstractPiece deadPiece : playerPieces) {
            if (!deadPiece.isAlive() && deadPiece instanceof PieceQueen) {
                return deadPiece;
            }
        }

        for (AbstractPiece deadPiece : playerPieces) {
            if (!deadPiece.isAlive() && deadPiece instanceof PieceRook) {
                return deadPiece;
            }
        }

        for (AbstractPiece deadPiece : playerPieces) {
            if (!deadPiece.isAlive() && deadPiece instanceof PieceBishop) {
                return deadPiece;
            }
        }

        for (AbstractPiece deadPiece : playerPieces) {
            if (!deadPiece.isAlive() && deadPiece instanceof PieceKnight) {
                return deadPiece;
            }
        }

        throw new GameException("Can't find dead Piece for change!");
    }


    @Override
    public String toString() {
        return "MoveVariant{" + pieceName + " " +
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
