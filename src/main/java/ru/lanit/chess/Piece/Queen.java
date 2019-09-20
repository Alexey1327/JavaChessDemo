package ru.lanit.chess.Piece;

import ru.lanit.chess.AbstractPiece;
import ru.lanit.chess.ChessBoard;
import ru.lanit.chess.MoveVariants;

public class Queen extends AbstractPiece implements Piece {

    public Queen(int x, int y, Color color) {
        super(x, y, color);
        this.setName("Queen");
    }

    @Override
    public Character getSymbol() {
        return this.getColor() == Color.WHITE ? '♕' : '♛';
    }

    @Override
    public MoveVariants getMoveVariants(ChessBoard board) {

        MoveVariants variants = new MoveVariants();
        Bishop.addBishopMoveVariants(board, variants, this.getX(), this.getY());
        Rook.addRookMoveVariants(board, variants, this.getX(), this.getY());

        return variants;
    }

}