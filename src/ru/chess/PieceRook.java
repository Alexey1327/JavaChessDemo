package ru.chess;

class PieceRook extends AbstractPiece implements PieceInterface {

    private final char symbol = 'R';

    public PieceRook(int x, int y, PieceColor color) {
        super(x, y, color);
    }

    @Override
    public Character getSymbol() {
        if (this.getColor() == PieceColor.WHITE) {
            return this.symbol;
        } else {
            return Character.toLowerCase(this.symbol);
        }
    }

    @Override
    public MoveVariants getMoveVariants(ChessBoard board) {

        int toX, toY;

        this.moveVariants.clear();

        toX = this.getX();
        toY = this.getY();
        while (addVariant(board, this.moveVariants, ++toX, toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = this.getX();
        while (addVariant(board, this.moveVariants, --toX, toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = this.getX();
        while (addVariant(board, this.moveVariants, toX, ++toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toY = this.getY();
        while (addVariant(board, this.moveVariants, toX, --toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        return this.moveVariants;
    }

}