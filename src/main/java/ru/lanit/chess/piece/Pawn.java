package ru.lanit.chess.piece;

import ru.lanit.chess.game.Board;
import ru.lanit.chess.game.MoveVariants;

public class Pawn extends AbstractPiece implements PieceInterface {

    public Pawn(int x, int y, Color color) {
        super(x, y, color);
        this.setName("Pawn");
    }

    @Override
    public Character getSymbol() {
        return this.getColor() == Color.WHITE ? '♙' : '♟';
    }

    @Override
    public MoveVariants getMoveVariants(Board board) {

        int fromX = this.getX();
        int fromY = this.getY();

        MoveVariants variants = new MoveVariants();

        if (board.isFirstMove()) {
            Board.addMoveVariant(board, variants, fromX, fromY, fromX, fromY + 2);
        }

        if (this.getColor() == Color.WHITE) {
            addPawnEatVariants(board, variants, fromX, fromY, fromX, fromY + 1);
        } else {
            addPawnEatVariants(board, variants, fromX, fromY, fromX, fromY - 1);
        }

        return variants;
    }

    private static void addPawnEatVariants(Board board, MoveVariants variants, int fromX, int fromY, int toX, int toY) {

        if (board.isFreeCell(toX, toY)) {
            Board.addMoveVariant(board, variants, fromX, fromY, toX, toY);
        }

        if (!board.isFreeCell(toX - 1, toY)) {
            Board.addMoveVariant(board, variants, fromX, fromY, toX - 1, toY);
        }
        if (!board.isFreeCell(toX + 1, toY)) {
            Board.addMoveVariant(board, variants, fromX, fromY, toX + 1, toY);
        }
    }
}