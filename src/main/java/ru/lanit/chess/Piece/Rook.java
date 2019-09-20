package ru.lanit.chess.Piece;

import ru.lanit.chess.AbstractPiece;
import ru.lanit.chess.ChessBoard;
import ru.lanit.chess.MoveVariants;

public class Rook extends AbstractPiece implements Piece {

    public Rook(int x, int y, Color color) {
        super(x, y, color);
        super.setName("Rook");
    }

    @Override
    public Character getSymbol() {
        return this.getColor() == Color.WHITE ? '♖' : '♜';
    }

    @Override
    public MoveVariants getMoveVariants(ChessBoard board) {

        MoveVariants variants = new MoveVariants();
        addRookMoveVariants(board, variants, this.getX(), this.getY());

        return variants;
    }

    static void addRookMoveVariants(ChessBoard board, MoveVariants variants, int fromX, int fromY) {

        int toX = fromX;
        int toY = fromY;

        while (ChessBoard.addMoveVariant(board, variants, fromX, fromY, ++toX, toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = fromX;
        while (ChessBoard.addMoveVariant(board, variants, fromX, fromY, --toX, toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = fromX;
        while (ChessBoard.addMoveVariant(board, variants, fromX, fromY, toX, ++toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toY = fromY;
        while (ChessBoard.addMoveVariant(board, variants, fromX, fromY, toX, --toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }
    }
}