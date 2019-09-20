package ru.lanit.chess.Piece;

import ru.lanit.chess.AbstractPiece;
import ru.lanit.chess.ChessBoard;
import ru.lanit.chess.MoveVariants;

public class King extends AbstractPiece implements Piece {

    public King(int x, int y, Color color) {
        super(x, y, color);
        this.setName("King");
    }

    @Override
    public Character getSymbol() {
        return this.getColor() == Color.WHITE ? '♔' : '♚';
    }

    @Override
    public MoveVariants getMoveVariants(ChessBoard board) {

        int fromX = this.getX(),
            fromY = this.getY();

        MoveVariants variants = new MoveVariants();

        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX - 1, fromY + 1);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX, fromY + 1);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX + 1, fromY + 1);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX - 1, fromY);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX + 1, fromY);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX -1, fromY - 1);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX, fromY - 1);
        ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX + 1, fromY - 1);

        return variants;
    }

}