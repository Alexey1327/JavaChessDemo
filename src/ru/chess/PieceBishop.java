package ru.chess;

class PieceBishop extends AbstractPiece implements PieceInterface {

    private final char symbol = 'B';

    public PieceBishop(int x, int y, PieceColor color) {
        super(x, y, color);
    }

    @Override
    public Character getSymbol() {
        return this.symbol;
    }


    @Override
    public MoveVariants getMoveVariants(ChessBoard board) {

        int toX, toY;

        this.moveVariants.clear();

        toX = this.getX();
        toY = this.getY();
        while (addVariant(board, this.moveVariants, --toX, ++toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = this.getX();
        toY = this.getY();
        while (addVariant(board, this.moveVariants, ++toX, ++toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = this.getX();
        toY = this.getY();
        while (addVariant(board, this.moveVariants, ++toX, --toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = this.getX();
        toY = this.getY();
        while (addVariant(board, this.moveVariants, --toX, --toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        return this.moveVariants;
    }

}