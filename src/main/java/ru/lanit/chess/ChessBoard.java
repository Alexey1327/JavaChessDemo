package ru.lanit.chess;

public class ChessBoard {

    AbstractPiece[][] field;

    private AbstractPiece[] whitePieces;
    private AbstractPiece[] blackPieces;

    private int moveCounter = 1;

    public ChessBoard() {
        this.field = new AbstractPiece[8][8];
        this.blackPieces = new AbstractPiece[16];
        this.whitePieces = new AbstractPiece[16];
    }

    int getMoveCounter() {
        return moveCounter;
    }

    boolean isFirstMove() {
        return moveCounter == 1;
    }

    void moveCounterInc() {
        moveCounter++;
    }

    private char getPieceSymbolByCoords(int x, int y) {
        if (field[x][y] != null) {
            return field[x][y].getSymbol();
        } else {
            return '.';
        }
    }

    AbstractPiece[] getPlayerPieces() {
        if (this.moveCounter % 2 == 0) {
            return blackPieces;
        } else {
            return whitePieces;
        }
    }

    private void initPieces() {
        // расставим пешки
        for (int x = 0; x < 8 ; x++) {
            whitePieces[x] = new PiecePawn(x, 1, PieceColor.WHITE);
            blackPieces[x] = new PiecePawn(x,6, PieceColor.BLACK);
        }
        whitePieces[8] = new PieceRook(0, 0, PieceColor.WHITE);
        whitePieces[9] = new PieceKnight(1, 0, PieceColor.WHITE);
        whitePieces[10] = new PieceBishop(2, 0, PieceColor.WHITE);
        whitePieces[11] = new PieceQueen(3, 0, PieceColor.WHITE);
        whitePieces[12] = new PieceKing(4, 0, PieceColor.WHITE);
        whitePieces[13] = new PieceBishop(5, 0, PieceColor.WHITE);
        whitePieces[14] = new PieceKnight(6, 0, PieceColor.WHITE);
        whitePieces[15] = new PieceRook(7, 0, PieceColor.WHITE);

        blackPieces[8] = new PieceRook(0, 7, PieceColor.BLACK);
        blackPieces[9] = new PieceKnight(1, 7, PieceColor.BLACK);
        blackPieces[10] = new PieceBishop(2, 7, PieceColor.BLACK);
        blackPieces[11] = new PieceQueen(3, 7, PieceColor.BLACK);
        blackPieces[12] = new PieceKing(4, 7, PieceColor.BLACK);
        blackPieces[13] = new PieceBishop(5, 7, PieceColor.BLACK);
        blackPieces[14] = new PieceKnight(6, 7, PieceColor.BLACK);
        blackPieces[15] = new PieceRook(7, 7, PieceColor.BLACK);
    }

    private void placePieces() {
        for (AbstractPiece piece : whitePieces) {
            field[piece.getX()][piece.getY()] = piece;
        }
        for (AbstractPiece piece : blackPieces) {
            field[piece.getX()][piece.getY()] = piece;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("   _A_B_C_D_E_F_G_H_\n");
        for (int y = 7; y >= 0; y--) {
            result.append(Character.toString('1' + y)).append(" | ");
            for (int x = 0; x < 8; x++) {
                result.append(this.getPieceSymbolByCoords(x, y)).append(" ");
            }
            result.append("| ").append(Character.toString('1' + y)).append(" \n");
        }
        result.append("   ¯A¯B¯C¯D¯E¯F¯G¯H¯\n");
        return result.toString();
    }

    boolean isFreeCell(int x, int y) {
        return isCellValid(x,y) && field[x][y] == null || isCellValid(x,y) && field[x][y] != null && !field[x][y].isAlive();
    }

    private AbstractPiece getCell(int x, int y) {
        return field[x][y];
    }

    private boolean isCellValid(int x, int y) {
        return x >= 0 && x <=7 && y >=0 && y <= 7;
    }

    static String getChessCoords(int x, int y) {
        return Character.toString('A'+x) + Character.toString('1'+y);
    }

    /**
     * return true if move is valid and added
     */
    static boolean addMoveVariant(ChessBoard board, MoveVariants variants, int fromX, int fromY, int toX, int toY) {

        AbstractPiece destinationPiece, startPiece;
        MoveVariant variant;

        if (board.isFreeCell(fromX,fromY)) {
            throw new GameException("Wrong move! Start coords has no alive piece!" + ChessBoard.getChessCoords(fromX, fromY));
        }

        if (!board.isCellValid(toX,toY)) {
            return false;
        }

        startPiece =  board.getCell(fromX, fromY);

        destinationPiece = board.getCell(toX, toY);
        if (destinationPiece != null) {
            if (destinationPiece.getColor() != startPiece.getColor()) {
                variant = new MoveVariant(fromX, fromY, toX, toY, MoveVariant.getEatWeight(destinationPiece), MoveResult.EAT, startPiece.getPieceName());
                variants.add(variant);
            } else {
                return false;
            }
        } else {
            variant = new MoveVariant(fromX, fromY, toX, toY, MoveVariant.getRegularMoveWeight(), MoveResult.MOVE, startPiece.getPieceName());
            variants.add(variant);
        }

        return true;
    }

    void initBoard() {
        this.initPieces();
        this.placePieces();
    }
}