package ru.lanit.chess.piece;

import ru.lanit.chess.game.Board;
import ru.lanit.chess.game.MoveVariants;

public class Queen extends AbstractPiece implements PieceInterface {

    public Queen(int x, int y, Color color) {
        super(x, y, color);
        this.setName("Queen");
    }

    @Override
    public Character getSymbol() {
        return this.getColor() == Color.WHITE ? '♕' : '♛';
    }

    @Override
    public MoveVariants getMoveVariants(Board board) {

        MoveVariants variants = new MoveVariants();
        Bishop.addBishopMoveVariants(board, variants, this.getX(), this.getY());
        Rook.addRookMoveVariants(board, variants, this.getX(), this.getY());

        return variants;
    }

}