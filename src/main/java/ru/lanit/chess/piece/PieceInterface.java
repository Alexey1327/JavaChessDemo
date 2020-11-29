package ru.lanit.chess.piece;

import ru.lanit.chess.game.Board;
import ru.lanit.chess.game.MoveVariants;

public interface PieceInterface {

    Character getSymbol();

    Character getTextSymbol();

    MoveVariants getMoveVariants(Board board);
}