package ru.lanit.chess.Piece;

import ru.lanit.chess.ChessBoard;
import ru.lanit.chess.MoveVariants;

public interface Piece {

    Character getSymbol();

    Character getTextSymbol();

    MoveVariants getMoveVariants(ChessBoard board);
}