package ru.lanit.chess;

class PiecePawn extends AbstractPiece implements Piece {

    PiecePawn(int x, int y, PieceColor color) {
        super(x, y, color);
        this.name = "Pawn";
    }

    @Override
    public MoveVariants getMoveVariants(ChessBoard board) {

        int fromX = this.getX();
        int fromY = this.getY();

        MoveVariants variants = new MoveVariants();

        if (board.isFirstMove()) {
            ChessBoard.addMoveVariant(board, variants, fromX, fromY, fromX, fromY + 2);
        }

        if (this.getColor() == PieceColor.WHITE) {
            addPawnEatVariants(board, variants, fromX, fromY, fromX, fromY + 1);
        } else {
            addPawnEatVariants(board, variants, fromX, fromY, fromX, fromY - 1);
        }

        return variants;
    }

    private static void addPawnEatVariants(ChessBoard board, MoveVariants variants, int fromX, int fromY, int toX, int toY) {

        if (board.isFreeCell(toX, toY)) {
            ChessBoard.addMoveVariant(board, variants, fromX, fromY, toX, toY);
        }

        if (!board.isFreeCell(toX - 1, toY)) {
            ChessBoard.addMoveVariant(board, variants, fromX, fromY, toX - 1, toY);
        }
        if (!board.isFreeCell(toX + 1, toY)) {
            ChessBoard.addMoveVariant(board, variants, fromX, fromY, toX + 1, toY);
        }
    }
}