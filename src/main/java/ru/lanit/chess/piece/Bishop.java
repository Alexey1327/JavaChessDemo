package ru.lanit.chess.piece;

import ru.lanit.chess.game.Board;
import ru.lanit.chess.game.MoveVariants;

public class Bishop extends AbstractPiece implements PieceInterface {

    public Bishop(int x, int y, Color color) {
        super(x, y, color);
        this.setName("Bishop");
    }

    @Override
    public Character getSymbol() {
        return this.getColor() == Color.WHITE ? '♗' : '♝';
    }

    @Override
    public MoveVariants getMoveVariants(Board board) {

        MoveVariants variants = new MoveVariants();
        addBishopMoveVariants(board, variants, this.getX(), this.getY());

        return variants;
    }
    
    static void addBishopMoveVariants(Board board, MoveVariants variants, int fromX, int fromY) {

        int toX = fromX;
        int toY = fromY;

        while (Board.addMoveVariant(board, variants, fromX, fromY, --toX, ++toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = fromX;
        toY = fromY;
        while (Board.addMoveVariant(board, variants, fromX, fromY, ++toX, ++toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = fromX;
        toY = fromY;
        while (Board.addMoveVariant(board, variants, fromX, fromY, ++toX, --toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }

        toX = fromX;
        toY = fromY;
        while (Board.addMoveVariant(board, variants, fromX, fromY, --toX, --toY)) {
            if (!board.isFreeCell(toX, toY)) {
                break;
            }
        }
    }
    
}