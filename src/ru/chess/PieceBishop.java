package ru.chess;

class PieceBishop extends AbstractPiece implements Piece {

    PieceBishop(int x, int y, PieceColor color) {
        super(x, y, color);
        this.name = "Bishop";
    }

    @Override
    public MoveVariants getMoveVariants(ChessBoard board) {

        MoveVariants variants = new MoveVariants();
        addBishopMoveVariants(board, variants, this.getX(), this.getY());

        return variants;
    }
    
    static void addBishopMoveVariants(ChessBoard board, MoveVariants variants, int fromX, int fromY) {

        int toX = fromX;
        int toY = fromY;

        while (ChessBoard.addMoveVariant(board, variants, fromX, fromY, --toX, ++toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = fromX;
        toY = fromY;
        while (ChessBoard.addMoveVariant(board, variants, fromX, fromY, ++toX, ++toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = fromX;
        toY = fromY;
        while (ChessBoard.addMoveVariant(board, variants, fromX, fromY, ++toX, --toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = fromX;
        toY = fromY;
        while (ChessBoard.addMoveVariant(board, variants, fromX, fromY, --toX, --toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }
    }
    
}