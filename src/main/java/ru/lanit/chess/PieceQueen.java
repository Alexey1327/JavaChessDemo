package ru.lanit.chess;

public class PieceQueen extends AbstractPiece implements Piece {

    PieceQueen(int x, int y, PieceColor color) {
        super(x, y, color);
        this.name = "Queen";
    }

    @Override
    public MoveVariants getMoveVariants(ChessBoard board) {

        MoveVariants variants = new MoveVariants();
        PieceBishop.addBishopMoveVariants(board, variants, this.getX(), this.getY());
        PieceRook.addRookMoveVariants(board, variants, this.getX(), this.getY());

        return variants;
    }

}