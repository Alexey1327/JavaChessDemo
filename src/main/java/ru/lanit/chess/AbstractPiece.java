package ru.lanit.chess;

import ru.lanit.chess.Piece.Piece;
import ru.lanit.chess.Piece.Color;

public abstract class AbstractPiece implements Piece {

    private int x, y;

    private Color color;

    private boolean alive = true;

    private String name;

    public AbstractPiece(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public abstract Character getSymbol();

    public Character getTextSymbol() {
        if (this.getColor() == Color.WHITE) {
            return name.charAt(0);
        } else {
            return Character.toLowerCase(name.charAt(0));
        }
    }

    public abstract MoveVariants getMoveVariants(ChessBoard board);

    public String getPieceName() {
        if (color == Color.WHITE) {
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

    protected int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    protected int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    protected Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "AbstractPiece{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + color +
                ", alive=" + alive +
                ", name='" + name + '\'' +
                '}';
    }
}