package ru.lanit.chess;

class PieceRook extends AbstractPiece implements Piece {

    PieceRook(int x, int y, PieceColor color) {
        super(x, y, color);
        this.name = "Rook";
    }

    @Override
    public MoveVariants getMoveVariants(ChessBoard board) {

        MoveVariants variants = new MoveVariants();
        addRookMoveVariants(board, variants, this.getX(), this.getY());

        return variants;
    }

    static void addRookMoveVariants(ChessBoard board, MoveVariants variants, int fromX, int fromY) {

        int toX = fromX;
        int toY = fromY;

        while (ChessBoard.addMoveVariant(board, variants, fromX, fromY, ++toX, toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = fromX;
        while (ChessBoard.addMoveVariant(board, variants, fromX, fromY, --toX, toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = fromX;
        while (ChessBoard.addMoveVariant(board, variants, fromX, fromY, toX, ++toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toY = fromY;
        while (ChessBoard.addMoveVariant(board, variants, fromX, fromY, toX, --toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }
    }
}