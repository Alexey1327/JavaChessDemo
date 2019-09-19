package ru.lanit.chess;

public enum MoveResult {
    MOVE,   // обычный ход
    EAT,  // фигура съедена
    CHANGE, // замена фигуры (для пешки)
    EAT_AND_CHANGE, // замена фигуры (для пешки)
}
