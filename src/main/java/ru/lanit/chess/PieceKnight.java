package ru.lanit.chess;

class PieceKnight extends AbstractPiece implements Piece {

    PieceKnight(int x, int y, PieceColor color) {
        super(x, y, color);
        this.name = "Knight";
    }

    @Override
    public Character getSymbol() {
        if (this.getColor() == PieceColor.WHITE) {
            return Character.toUpperCase(name.charAt(1));
        } else {
            return name.charAt(1);
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