package ru.chess;

import java.util.ArrayList;

interface PieceInterface {

    MoveVariants getMoveVariants(ChessBoard board);
}