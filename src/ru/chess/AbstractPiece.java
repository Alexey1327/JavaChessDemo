package ru.chess;

public abstract class AbstractPiece implements Piece {

    private int x, y;

    private PieceColor color;

    private boolean alive = true;

    String name;

    public AbstractPiece(int x, int y, PieceColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Character getSymbol() {
        if (this.getColor() == PieceColor.WHITE) {
            return name.charAt(0);
        } else {
            return Character.toLowerCase(name.charAt(0));
        }
    }

    public abstract MoveVariants getMoveVariants(ChessBoard board);

    @Override
    public String getPieceName() {
        if (color == PieceColor.WHITE) {
            return "White " + name;
        } else {
            return "Black " + name;
        }
    }

    boolean isAlive() {
        return alive;
    }

    void setAlive(boolean alive) {
        this.alive = alive;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    PieceColor getColor() {
        return color;
    }
}