package ru.chess;

class PieceKnight extends AbstractPiece implements PieceInterface {

    private final char symbol = 'N';


    public PieceKnight(int x, int y, PieceColor color) {
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

        addVariant(board, this.moveVariants, toX - 1, toY + 2);
        addVariant(board, this.moveVariants, toX + 1, toY + 2);
        addVariant(board, this.moveVariants, toX - 2, toY + 1);
        addVariant(board, this.moveVariants, toX + 2, toY + 1);
        addVariant(board, this.moveVariants, toX - 2, toY - 1);
        addVariant(board, this.moveVariants, toX + 2, toY - 1);
        addVariant(board, this.moveVariants, toX - 1, toY - 2);
        addVariant(board, this.moveVariants, toX + 1, toY - 2);

        return this.moveVariants;
    }


}