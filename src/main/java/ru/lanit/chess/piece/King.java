package ru.lanit.chess.piece;

import ru.lanit.chess.game.Board;
import ru.lanit.chess.game.MoveVariants;

public class King extends AbstractPiece implements PieceInterface {

    public King(int x, int y, Color color) {
        super(x, y, color);
        this.setName("King");
    }

    @Override
    public Character getSymbol() {
        return this.getColor() == Color.WHITE ? '♔' : '♚';
    }

    @Override
    public MoveVariants getMoveVariants(Board board) {

        int fromX = this.getX(),
            fromY = this.getY();

        MoveVariants variants = new MoveVariants();

        Board.addMoveVariant(board, variants, fromX, fromY, fromX - 1, fromY + 1);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX, fromY + 1);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX + 1, fromY + 1);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX - 1, fromY);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX + 1, fromY);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX -1, fromY - 1);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX, fromY - 1);
        Board.addMoveVariant(board, variants, fromX, fromY, fromX + 1, fromY - 1);

        return variants;
    }

}