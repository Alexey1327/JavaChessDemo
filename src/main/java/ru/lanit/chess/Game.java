package ru.lanit.chess;

class Game {

    private final static int moveLimit = 1000;

    private static ChessBoard board;

    private static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    private static MoveVariants getAllPlayerMoves() {
        MoveVariants variants = new MoveVariants();

        for (AbstractPiece piece: board.getCurrentPlayerPieces()) {
            if (piece.isAlive()) {
                variants.addAll(piece.getMoveVariants(board));
            }
        }
        variants.sort(new MoveVariantComparator());

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

    private static MoveVariant getBestPlayerMove() {

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
                throw new GameException("GameOver! Player WINS! King will was killed by move " + variant.toStringShort());
            }
            if (variant.getResultWeight() > 0) {
                return variant;
            } else {
                return variants.get(rnd(0,variants.size()-1));
            }
        } else {
            throw new GameException("Game Over! MAT! " + board.getOpponentPlayerColor() + " player win on " + board.getMoveCounter() + " moves!");
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
        if (piece1 instanceof PiecePawn && (variant.getToY() == 0 || variant.getToY() == 7)) {
            piece1.setAlive(false);
            AbstractPiece bestDeadPiece = getBestPieceForChange();
            board.field[variant.getToX()][variant.getToY()] = bestDeadPiece;
            bestDeadPiece.setAlive(true);
            System.out.println("It's alive!!! New Piece - " + bestDeadPiece.getPieceName());
        }

        board.moveCounterInc();
    }

    private static AbstractPiece getBestPieceForChange() {
        // priority Queen, Rook, Bishop, Knight
        for (AbstractPiece deadPiece : board.getCurrentPlayerPieces()) {
            if (!deadPiece.isAlive() && deadPiece instanceof PieceQueen) {
                return deadPiece;
            }
        }

        for (AbstractPiece deadPiece : board.getCurrentPlayerPieces()) {
            if (!deadPiece.isAlive() && deadPiece instanceof PieceRook) {
                return deadPiece;
            }
        }

        for (AbstractPiece deadPiece : board.getCurrentPlayerPieces()) {
            if (!deadPiece.isAlive() && deadPiece instanceof PieceBishop) {
                return deadPiece;
            }
        }

        for (AbstractPiece deadPiece : board.getCurrentPlayerPieces()) {
            if (!deadPiece.isAlive() && deadPiece instanceof PieceKnight) {
                return deadPiece;
            }
        }

        throw new GameException("Can't find dead Piece for change!");
    }

    static void playGame() {

        board = new ChessBoard();
        board.initBoard();

        while (true) {
            try {
                movePiece(getBestPlayerMove());
            } catch (GameException e){
                System.out.println(e.getMessage());
                break;
            }
            System.out.println(board);
            if (board.getMoveCounter() == moveLimit) {
                System.out.println("Nobody wins... " + moveLimit + " moves limit exceeded");
                break;
            }
        }
    }
}
