package ru.chess;

class PieceKing extends AbstractPiece implements PieceInterface {

    private final char symbol = 'K';

    public PieceKing(int x, int y, PieceColor color) {
        super(x, y, color);
    }

    @Override
    public Character getSymbol() {
        return this.symbol;
    }

    @Override
    public MoveVariants getMoveVariants(ChessBoard board) {

        int toX = this.getX(),
            toY = this.getY();

        this.moveVariants.clear();

        addVariant(board, this.moveVariants, toX - 1, toY + 1);
        addVariant(board, this.moveVariants, toX, toY + 1);
        addVariant(board, this.moveVariants, toX + 1, toY + 1);
        addVariant(board, this.moveVariants, toX - 1, toY);
        addVariant(board, this.moveVariants, toX + 1, toY);
        addVariant(board, this.moveVariants, toX -1, toY - 1);
        addVariant(board, this.moveVariants, toX, toY - 1);
        addVariant(board, this.moveVariants, toX + 1, toY - 1);

        return this.moveVariants;
    }

}