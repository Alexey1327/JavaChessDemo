package ru.lanit.chess;

public class Game {

    private static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    private static MoveVariants getAllPlayerMoves(ChessBoard board) {
        MoveVariants variants = new MoveVariants();

        for (AbstractPiece piece: board.getPlayerPieces()) {
            if (piece.isAlive()) {
                variants.addAll(piece.getMoveVariants(board));
            }
        }
        variants.sort(new MoveVariantComparator());

        return variants;
    }

    private static MoveVariant getBestPlayerMove(ChessBoard board) {

        MoveVariants variants = getAllPlayerMoves(board);

        System.out.println(variants);

        if (variants.size() > 0) {
            MoveVariant variant = variants.get(0);
            if (variant.isDeadKingVariant()) {
                throw new GameException("GameOver! Player WINS! King will was killed by move " + variant.toStringShort());
            }
            if (variant.getResultWeight() > 0) {
                return variant;
            } else {
                return variants.get(rnd(0,variants.size()-1));
            }
        } else {
            throw new GameException("Game Over! Player has no possible moves!");
        }
    }

    private static void printStat(ChessBoard board) {
        System.out.println(board);
        System.out.println("Moves: " + board.getMoveCounter());
    }

    // TODO доделать выбор фигуры при замене пешки
    private static void movePiece(ChessBoard board, MoveVariant variant) {

        System.out.println(board.getMoveCounter() + " " + variant);

        AbstractPiece piece1 = board.field[variant.getFromX()][variant.getFromY()];
        AbstractPiece piece2 = board.field[variant.getToX()][variant.getToY()];

        board.field[variant.getFromX()][variant.getFromY()] = null;

        piece1.setX(variant.getToX());
        piece1.setY(variant.getToY());
        board.field[variant.getToX()][variant.getToY()] = piece1;

        switch (variant.getMoveResult()) {
            case MOVE:
                break;
            case EAT:
                piece2.setAlive(false);
                break;
            case CHANGE:
                piece1.setAlive(false);
                // TODO выбор фигуры при замене пешки
                break;
            case EAT_AND_CHANGE:
                piece1.setAlive(false);
                piece2.setAlive(false);
                // TODO выбор фигуры при замене пешки
                break;
        }

        board.moveCounterInc();
    }

    public static void playGame() {

        ChessBoard board = new ChessBoard();
        board.initBoard();

        while (true) {
            try {
                movePiece(board, getBestPlayerMove(board));
            } catch (GameException e){
                System.out.println(e.getMessage());
                break;
            }
            System.out.println(board);
            if (board.getMoveCounter() > 500) {
                System.out.println("Nobody wins...");
                break;
            }
        }
        printStat(board);
    }
}
