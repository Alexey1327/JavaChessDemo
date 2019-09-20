package ru.lanit.chess.Piece;

import ru.lanit.chess.AbstractPiece;
import ru.lanit.chess.ChessBoard;
import ru.lanit.chess.MoveVariants;

public class Knight extends AbstractPiece implements Piece {

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
    public MoveVariants getMoveVariants(ChessBoard board) {

        int fromX = this.getX(),
            fromY = this.getY();

        MoveVariants variants = new MoveVariants();

        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX - 1, fromY + 2);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX + 1, fromY + 2);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX - 2, fromY + 1);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX + 2, fromY + 1);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX - 2, fromY - 1);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX + 2, fromY - 1);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX - 1, fromY - 2);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX + 1, fromY - 2);

        return variants;
    }
}