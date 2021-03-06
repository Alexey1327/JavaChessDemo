package ru.lanit.chess.game;

import ru.lanit.chess.exception.CheckMateException;
import ru.lanit.chess.exception.GameException;
import ru.lanit.chess.exception.MateException;
import ru.lanit.chess.piece.AbstractPiece;
import ru.lanit.chess.piece.Pawn;

import java.util.Comparator;

public class Game {

    private final static int MOVE_LIMIT_FOR_DRAW = 100;

    private static Board board;

    private static MoveVariants getAllPlayerMoves() {
        MoveVariants variants = new MoveVariants();

        for (AbstractPiece piece: board.getCurrentPlayerPieces()) {
            if (piece.isAlive()) {
                variants.addAll(piece.getMoveVariants(board));
            }
        }
        variants.sort(Comparator.comparing(MoveVariant::getResultWeight));

        return variants;
    }

    private static void updateCheckMateVariants(MoveVariants variants) {
        MoveVariants opponentVariants;
        AbstractPiece piece1, piece2;

        for (MoveVariant variant : variants) {
            // делаем ход
            piece1 = board.field[variant.getFromX()][variant.getFromY()];
            piece2 = board.field[variant.getToX()][variant.getToY()];

            piece1.setX(variant.getToX());
            piece1.setY(variant.getToY());
            board.field[variant.getFromX()][variant.getFromY()] = null;
            board.field[variant.getToX()][variant.getToY()] = piece1;
            if (piece2 != null) {
                piece2.setAlive(false);
            }

            opponentVariants = getOpponentCheckMateVariants();
            if (opponentVariants.size() > 0) {
                System.out.println("King can die from " + opponentVariants.get(0));
                variant.setCheckMateResult();
            }
            opponentVariants.clear();

            // откатываем ход
            piece1.setX(variant.getFromX());
            piece1.setY(variant.getFromY());
            board.field[variant.getFromX()][variant.getFromY()] = piece1;
            board.field[variant.getToX()][variant.getToY()] = piece2;
            if (piece2 != null) {
                piece2.setAlive(true);
            }
        }
    }

    private static MoveVariants getOpponentCheckMateVariants() {

        MoveVariants moveVariants = new MoveVariants();

        for (AbstractPiece piece : board.getOpponentPlayerPieces()) {
            if (piece.isAlive()) {
                moveVariants.addAll(piece.getMoveVariants(board));
            }
        }
        moveVariants.removeIf(variant -> !variant.isDeadKingVariant());

        return moveVariants;
    }

    private static MoveVariant getBestPlayerMove() throws MateException, CheckMateException {

        MoveVariants opponentVariants = getOpponentCheckMateVariants();

        if (opponentVariants.size() > 0) {
            System.out.println("Opposite player have Check! " + opponentVariants.get(0));
        }

        MoveVariants variants = getAllPlayerMoves();
        updateCheckMateVariants(variants);
        System.out.println("Possible variants: " + variants);
        variants.removeIf(variant -> variant.getMoveResult() == MoveResult.CHECK_MATE);

        if (variants.size() > 0) {
            MoveVariant variant = variants.get(0);
            if (variant.isDeadKingVariant()) {
                throw new CheckMateException("GameOver! Player WINS! King will be killed by move " + variant.toStringShort());
            }
            if (variant.getResultWeight() > 0) {
                return variant;
            } else {
                return variants.get((int)(Math.random() * variants.size()));
            }
        } else {
            throw new MateException("Game Over! MAT! " + board.getOpponentPlayerColor() + " player win on " + (board.getMoveCounter()-1) + " moves!");
        }
    }

    private static void movePiece(MoveVariant variant) {

        System.out.println(board.getMoveCounter() + " " + variant);

        AbstractPiece piece1 = board.field[variant.getFromX()][variant.getFromY()];
        AbstractPiece piece2 = board.field[variant.getToX()][variant.getToY()];

        board.field[variant.getFromX()][variant.getFromY()] = null;

        piece1.setX(variant.getToX());
        piece1.setY(variant.getToY());
        board.field[variant.getToX()][variant.getToY()] = piece1;

        if (piece2 != null) {
            piece2.setAlive(false);
        }

        // Pawn Change
        if (piece1 instanceof Pawn && (variant.getToY() == 0 || variant.getToY() == 7)) {
            piece1.setAlive(false);
            AbstractPiece bestDeadPiece = MoveVariant.getBestPieceForChange(board.getCurrentPlayerPieces());
            board.field[variant.getToX()][variant.getToY()] = bestDeadPiece;
            bestDeadPiece.setX(piece1.getX());
            bestDeadPiece.setY(piece1.getY());
            bestDeadPiece.setAlive(true);
            System.out.println("It's alive!!! New Piece - " + bestDeadPiece.getPieceName());
        }

        board.moveCounterInc();
    }

    public static void playGame(boolean printAsUTF8) {

        Board.setPrintTypeAsUtf8(printAsUTF8);

        board = new Board();
        board.initBoard();
        System.out.println(board);

        while (true) {
            try {
                movePiece(getBestPlayerMove());
            } catch (CheckMateException | MateException | GameException e) {
                System.out.println(e.getMessage());
                break;
            }
            System.out.println(board);
            if (board.getMoveCounter() > MOVE_LIMIT_FOR_DRAW) {
                if (board.checkDraw()) {
                    System.out.println("Nobody wins! Draw, on " + board.getMoveCounter() + " moves.");
                    break;
                }
            }
        }
    }
}
