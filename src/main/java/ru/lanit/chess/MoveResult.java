package ru.lanit.chess;

public enum MoveResult {
    MOVE,   // обычный ход
    EAT,  // фигура съедена
    EAT_AND_CHANGE, // фигура съедена и меняем фигуру
    CHECK_MATE // ход ставит короля под удар
}
