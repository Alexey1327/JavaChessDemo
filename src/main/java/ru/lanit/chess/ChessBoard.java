package ru.lanit.chess;

import ru.lanit.chess.Piece.*;

public class ChessBoard {

    AbstractPiece[][] field;

    private AbstractPiece[] whitePieces;
    private AbstractPiece[] blackPieces;

    private int moveCounter = 1;

    private static boolean printAsUtf8 = false;

    public ChessBoard() {
        this.field = new AbstractPiece[8][8];
        this.blackPieces = new AbstractPiece[16];
        this.whitePieces = new AbstractPiece[16];
    }

    int getMoveCounter() {
        return moveCounter;
    }

    static void setPrintTypeAsUtf8(boolean printTypeAsUtf8) {
        printAsUtf8 = printTypeAsUtf8;
    }

    private static boolean isPrintAsUtf8() {
        return printAsUtf8;
    }

    public boolean isFirstMove() {
        return moveCounter == 1;
    }

    void moveCounterInc() {
        moveCounter++;
    }

    private char getPieceSymbolByCoords(int x, int y) {
        if (field[x][y] != null) {
            if (ChessBoard.isPrintAsUtf8()) {
                return field[x][y].getSymbol();
            } else {
                return field[x][y].getTextSymbol();
            }
        } else {
            return '.';
        }
    }

    AbstractPiece[] getCurrentPlayerPieces() {
        if (getCurrentPlayerColor() == Color.WHITE) {
            return whitePieces;
        } else {
            return blackPieces;
        }
    }

    AbstractPiece[] getOpponentPlayerPieces() {
        if (getCurrentPlayerColor() == Color.WHITE) {
            return blackPieces;
        } else {
            return whitePieces;
        }
    }

    private Color getCurrentPlayerColor() {
        if (this.moveCounter % 2 != 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    Color getOpponentPlayerColor() {
        if (this.moveCounter % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    private void initPieces() {
        // расставим пешки
        for (int x = 0; x < 8 ; x++) {
            whitePieces[x] = new Pawn(x, 1, Color.WHITE);
            blackPieces[x] = new Pawn(x,6, Color.BLACK);
        }
        whitePieces[8] = new Rook(0, 0, Color.WHITE);
        whitePieces[9] = new Knight(1, 0, Color.WHITE);
        whitePieces[10] = new Bishop(2, 0, Color.WHITE);
        whitePieces[11] = new Queen(3, 0, Color.WHITE);
        whitePieces[12] = new King(4, 0, Color.WHITE);
        whitePieces[13] = new Bishop(5, 0, Color.WHITE);
        whitePieces[14] = new Knight(6, 0, Color.WHITE);
        whitePieces[15] = new Rook(7, 0, Color.WHITE);

        blackPieces[8] = new Rook(0, 7, Color.BLACK);
        blackPieces[9] = new Knight(1, 7, Color.BLACK);
        blackPieces[10] = new Bishop(2, 7, Color.BLACK);
        blackPieces[11] = new Queen(3, 7, Color.BLACK);
        blackPieces[12] = new King(4, 7, Color.BLACK);
        blackPieces[13] = new Bishop(5, 7, Color.BLACK);
        blackPieces[14] = new Knight(6, 7, Color.BLACK);
        blackPieces[15] = new Rook(7, 7, Color.BLACK);
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
            result.append((char)('1' + y)).append(" | ");
            for (int x = 0; x < 8; x++) {
                result.append(this.getPieceSymbolByCoords(x, y)).append(" ");
            }
            result.append("| ").append((char)('1' + y)).append(" \n");
        }
        if (ChessBoard.isPrintAsUtf8()) {
            result.append("   ¯A¯B¯C¯D¯E¯F¯G¯H¯\n");
        } else {
            result.append("   -A-B-C-D-E-F-G-H-\n");
        }
        return result.toString();
    }

    public boolean isFreeCell(int x, int y) {
        return isCellValid(x,y) && field[x][y] == null || isCellValid(x,y) && field[x][y] != null && !field[x][y].isAlive();
    }

    private AbstractPiece getCell(int x, int y) {
        return field[x][y];
    }

    private boolean isCellValid(int x, int y) {
        return x >= 0 && x <=7 && y >=0 && y <= 7;
    }

    static String getChessCoords(int x, int y) {
        return String.valueOf((char) ('A' + x)) + (char)('1' + y);
    }

    boolean checkDraw() {
        int player1AliveCounter = 0, player2AliveCounter = 0;
        for (AbstractPiece piece : this.getCurrentPlayerPieces()) {
            if (piece.isAlive()) {
                player1AliveCounter++;
            }
        }

        for (AbstractPiece piece : this.getOpponentPlayerPieces()) {
            if (piece.isAlive()) {
                player2AliveCounter++;
            }
        }

        return player1AliveCounter == 1 && player2AliveCounter == 1;
    }

    /**
     * return true if move is valid and added
     */
    public static boolean addMoveVariant(ChessBoard board, MoveVariants variants, int fromX, int fromY, int toX, int toY) {

        AbstractPiece piece1, piece2;
        MoveVariant variant;
        MoveResult moveResult;

        if (board.isFreeCell(fromX,fromY)) {
            throw new GameException("Wrong move! Start coords has no alive piece!" + ChessBoard.getChessCoords(fromX, fromY));
        }

        if (!board.isCellValid(toX,toY)) {
            return false;
        }

        piece1 =  board.getCell(fromX, fromY);
        String piece1Name;

        if (ChessBoard.isPrintAsUtf8()) {
            piece1Name = Character.toString(piece1.getSymbol());
        } else {
            piece1Name = piece1.getPieceName();
        }

        piece2 = board.getCell(toX, toY);
        if (piece2 != null) {
            if (piece2.getColor() != piece1.getColor()) {
                if (piece1 instanceof Pawn && (toY == 7 || toY == 0)) {
                    moveResult = MoveResult.EAT_AND_PROMOTION;
                } else {
                    moveResult = MoveResult.EAT;
                }
                variant = new MoveVariant(fromX, fromY, toX, toY, MoveVariant.calculateEatWeight(piece1, piece2), moveResult, piece1Name);
                variants.add(variant);
            } else {
                return false;
            }
        } else {
            if (piece1 instanceof Pawn && (toY == 7 || toY == 0)) {
                moveResult = MoveResult.PROMOTION;
            } else {
                moveResult = MoveResult.MOVE;
            }
            variant = new MoveVariant(fromX, fromY, toX, toY, MoveVariant.calculateDestinationMoveWeight(piece1), moveResult, piece1Name);
            variants.add(variant);
        }

        return true;
    }

    void initBoard() {
        this.initPieces();
        this.placePieces();
    }
}