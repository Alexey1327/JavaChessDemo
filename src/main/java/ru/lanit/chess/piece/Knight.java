package ru.lanit.chess.piece;

import ru.lanit.chess.game.Board;
import ru.lanit.chess.game.MoveVariants;

public class Knight extends AbstractPiece implements PieceInterface {

    public Knight(int x, int y, Color color) {
        super(x, y, color);
        this.setName("Knight");
    }

    @Override
    public Character getSymbol() {
        return this.getColor() == Color.WHITE ? '♘' : '♞';
    }

    @Override
    public Character getTextSymbol() {
        if (this.getColor() == Color.WHITE) {
            return Character.toUpperCase(this.getName().charAt(1));
        } else {
            return this.getName().charAt(1);
        }
    }

    @Override
    public MoveVariants getMoveVariants(Board board) {

        int fromX = this.getX(),
            fromY = this.getY();

        MoveVariants variants = new MoveVariants();

        Board.addMoveVariant(board, variants, fromX, fromY, fromX - 1, fromY + 2);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX + 1, fromY + 2);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX - 2, fromY + 1);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX + 2, fromY + 1);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX - 2, fromY - 1);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX + 2, fromY - 1);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX - 1, fromY - 2);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX + 1, fromY - 2);

        return variants;
    }
}