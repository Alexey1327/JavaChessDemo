package ru.lanit.chess;

interface Piece {

    Character getSymbol();

    String getPieceName();

    MoveVariants getMoveVariants(ChessBoard board);
}