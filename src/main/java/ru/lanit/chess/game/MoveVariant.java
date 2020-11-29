package ru.lanit.chess.game;

import ru.lanit.chess.exception.GameException;
import ru.lanit.chess.piece.*;

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
        if (piece instanceof Pawn) {
            if (piece.getY() == 6 && piece.getColor() == Color.WHITE || piece.getY() == 1 && piece.getColor() == Color.BLACK) {
                return PROMOTION_PAWN_WEIGHT;
            }
        }
        return REGULAR_MOVE_WEIGHT;
    }

    boolean isDeadKingVariant() {
        return this.resultWeight == EAT_KING_WEIGHT;
    }


    static byte calculateEatWeight(AbstractPiece piece1, AbstractPiece piece2) {
        if (piece2 instanceof King) {
            return EAT_KING_WEIGHT;
        }
        if (piece2 instanceof Knight) {
            return EAT_KNIGHT_WEIGHT;
        }
        if (piece2 instanceof Bishop) {
            return EAT_BISHOP_WEIGHT;
        }
        if (piece2 instanceof Rook) {
            return EAT_ROOK_WEIGHT;
        }
        if (piece2 instanceof Queen) {
            return EAT_QUEEN_WEIGHT;
        }
        if (piece2 instanceof Pawn) {
            return EAT_PAWN_WEIGHT;
        }

        if (piece1 instanceof Pawn) {
            if (piece2.getY() == 7 || piece2.getY() == 0) {
                return PROMOTION_EAT_PAWN_WEIGHT;
            }
        }

        throw new GameException("Error calculateEatWeight! " + piece1 + piece2);
    }


    static AbstractPiece getBestPieceForChange(AbstractPiece[] playerPieces) {
        // priority Queen, Rook, Bishop, Knight
        for (AbstractPiece deadPiece : playerPieces) {
            if (!deadPiece.isAlive() && deadPiece instanceof Queen) {
                return deadPiece;
            }
        }

        for (AbstractPiece deadPiece : playerPieces) {
            if (!deadPiece.isAlive() && deadPiece instanceof Rook) {
                return deadPiece;
            }
        }

        for (AbstractPiece deadPiece : playerPieces) {
            if (!deadPiece.isAlive() && deadPiece instanceof Bishop) {
                return deadPiece;
            }
        }

        for (AbstractPiece deadPiece : playerPieces) {
            if (!deadPiece.isAlive() && deadPiece instanceof Knight) {
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
        return Board.getChessCoords(fromX, fromY) + " -> "  + Board.getChessCoords(toX, toY);
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
