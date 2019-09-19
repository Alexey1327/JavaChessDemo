package ru.chess;

class PieceKing extends AbstractPiece implements Piece {

    PieceKing(int x, int y, PieceColor color) {
        super(x, y, color);
        this.name = "King";
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