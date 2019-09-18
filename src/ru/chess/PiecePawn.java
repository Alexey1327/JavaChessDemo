package ru.chess;

class PiecePawn extends AbstractPiece implements PieceInterface{

    private final char symbol = 'P';

    public PiecePawn(int x, int y, PieceColor color) {
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

        MoveVariant variant;

        int toX = this.getX(),
            toY = this.getY();

        this.moveVariants.clear();

        if (this.getColor() == PieceColor.WHITE) {
            if (board.moveCounter == 1) {
                variant = new MoveVariant(this.getX(), this.getY(), toX, toY+2, MoveVariant.REGULAR_MOVE_WEIGHT, MoveResult.MOVE, this.getSymbol());
                this.moveVariants.add(variant);
                variant = new MoveVariant(this.getX(), this.getY(), toX, toY+1, MoveVariant.REGULAR_MOVE_WEIGHT, MoveResult.MOVE, this.getSymbol());
                this.moveVariants.add(variant);
            } else {
                toY++;
                if (board.isFreeCell(toX, toY)) {
                    if (toY == 7) {
                        variant = new MoveVariant(this.getX(), this.getY(), toX, toY, MoveVariant.CHANGE_PIECE_WEIGHT, MoveResult.CHANGE, this.getSymbol());
                    } else {
                        variant = new MoveVariant(this.getX(), this.getY(), toX, toY, MoveVariant.REGULAR_MOVE_WEIGHT, MoveResult.MOVE, this.getSymbol());
                    }
                    this.moveVariants.add(variant);
                }

                toX++;
                if (!board.isFreeCell(toX, toY)) {
                    addVariant(board, this.moveVariants, toX, toY);
                }
                toX = toX - 2;
                if (!board.isFreeCell(toX, toY)) {
                    addVariant(board, this.moveVariants, toX, toY);
                }
            }
        } else {
            toY--;
            if (board.isFreeCell(toX, toY)) {
                if (toY == 0) {
                    variant = new MoveVariant(this.getX(), this.getY(), toX, toY, MoveVariant.CHANGE_PIECE_WEIGHT, MoveResult.CHANGE, this.getSymbol());
                } else {
                    variant = new MoveVariant(this.getX(), this.getY(), toX, toY, MoveVariant.REGULAR_MOVE_WEIGHT, MoveResult.MOVE, this.getSymbol());
                }
                this.moveVariants.add(variant);
            }

            toX++;
            if (!board.isFreeCell(toX, toY)) {
                addVariant(board, this.moveVariants, toX, toY);
            }
            toX = toX - 2;
            if (!board.isFreeCell(toX, toY)) {
                addVariant(board, this.moveVariants, toX, toY);
            }
        }

        return this.moveVariants;
    }

}