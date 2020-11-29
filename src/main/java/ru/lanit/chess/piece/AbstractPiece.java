package ru.lanit.chess.piece;

import ru.lanit.chess.game.Board;
import ru.lanit.chess.game.MoveVariants;

public abstract class AbstractPiece implements PieceInterface {

    private int x, y;

    private Color color;

    private boolean alive = true;

    private String name;

    public AbstractPiece(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    protected String getName() {
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

    public abstract MoveVariants getMoveVariants(Board board);

    public String getPieceName() {
        if (color == Color.WHITE) {
            return "White " + name;
        } else {
            return "Black " + name;
        }
    }

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

    public Color getColor() {
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