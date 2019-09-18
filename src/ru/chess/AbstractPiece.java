package ru.chess;

public abstract class AbstractPiece implements PieceInterface{

    private int x, y;

    private PieceColor color;

    private boolean alive = true;

    MoveVariants moveVariants;

    public AbstractPiece(int x, int y, PieceColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
        moveVariants = new MoveVariants();
    }

    public abstract Character getSymbol();

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PieceColor getColor() {
        return color;
    }

    public abstract MoveVariants getMoveVariants(ChessBoard board);

    public byte getEatWeight(AbstractPiece piece) {
        if (piece instanceof PiecePawn) {
            return MoveVariant.EAT_PAWN_WEIGHT;
        }
        if (piece instanceof PieceKnight) {
            return MoveVariant.EAT_KNIGHT_WEIGHT;
        }
        if (piece instanceof PieceBishop) {
            return MoveVariant.EAT_BISHOP_WEIGHT;
        }
        if (piece instanceof PieceRook) {
            return MoveVariant.EAT_ROOK_WEIGHT;
        }
        if (piece instanceof PieceQueen) {
            return MoveVariant.EAT_QUEEN_WEIGHT;
        }
        if (piece instanceof PieceKing) {
            return MoveVariant.EAT_KING_WEIGHT;
        }

        throw new GameException("Unknown Piece Type!");
    }

    public static String getChessCoords(int x, int y) {
        return Character.toString('A'+x) + Character.toString('1'+y);
    }

    public boolean addVariant(ChessBoard board, MoveVariants variants, int toX, int toY) {
        AbstractPiece piece;
        MoveVariant variant;

        if (!board.isCellValid(toX,toY)) {
            return false;
        }

        piece = board.getCell(toX, toY);
        if (piece != null) {
            if (piece.getColor() != this.getColor()) {
                variant = new MoveVariant(this.getX(), this.getY(), toX, toY, getEatWeight(piece), MoveResult.EAT, this.getSymbol());
                variants.add(variant);
            } else {
                return false;
            }
        } else {
            variant = new MoveVariant(this.getX(), this.getY(), toX, toY, MoveVariant.REGULAR_MOVE_WEIGHT, MoveResult.MOVE, this.getSymbol());
            variants.add(variant);
        }

        return true;
    }

}