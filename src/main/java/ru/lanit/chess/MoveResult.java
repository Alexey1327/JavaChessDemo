package ru.lanit.chess;

public enum MoveResult {
    MOVE,   // обычный ход
    EAT,  // фигура съедена
    CHECK_MATE // ход ставит короля под удар
}
