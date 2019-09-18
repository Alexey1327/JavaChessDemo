package ru.chess;

class ChessBoard {

    public AbstractPiece[][] field;

    private AbstractPiece[] whitePieces;
    private AbstractPiece[] blackPieces;

    public int moveCounter = 1;

    public ChessBoard() {
        this.field = new AbstractPiece[8][8];
        this.blackPieces = new AbstractPiece[16];
        this.whitePieces = new AbstractPiece[16];
    }

    public char getPieceSymbolByCoords(int x, int y) {
        if (field[x][y] != null) {
            return field[x][y].getSymbol();
        } else {
            return '.';
        }
    }

    public AbstractPiece[] getPlayerPieces() {
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

    public boolean isFreeCell(int x, int y) {
        return isCellValid(x,y) && field[x][y] == null || isCellValid(x,y) && field[x][y] != null && !field[x][y].isAlive();
    }

    public AbstractPiece getCell(int x, int y) {
        return field[x][y];
    }

    public boolean isCellValid(int x, int y) {
        return x >= 0 && x <=7 && y >=0 && y <= 7;
    }

    public ChessBoard initBoard() {
        this.initPieces();
        this.placePieces();

        return this;
    }


}