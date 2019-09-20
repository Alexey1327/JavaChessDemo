package ru.lanit.chess;

public enum MoveResult {
    MOVE,   // обычный ход
    EAT,  // фигура съедена
    PROMOTION,  // меняем пешку
    EAT_AND_PROMOTION, // фигура съедена и меняем фигуру
    CHECK_MATE // ход ставит короля под удар
}
