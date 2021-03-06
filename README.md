# JavaChessDemo

Простой шахматный движок на Java. 
Компьютер поочерёдно ходит за игроков.
Сначала вычисляются все возможые ходы всех фигур, выставляются простейшим алгоритмом приоритет наилучшего хода, и фильтруются ходы, ставявшие короля под удар. Если ходов нет, а противник поставил до этого шах, значит это мат и игра завершается.
Если остаётся по одной фигуре, значит ставится ничья.
Пешка меняется на любую фигуру по приоритету ферзь-ладья-слон-конь

Вид вывода игры на экран:
```
1 MoveVariant{White Pawn A2 -> A3, resultWeight=0, moveResult=MOVE}
   _A_B_C_D_E_F_G_H_
8 | r n b q k b n r | 8 
7 | p p p p p p p p | 7 
6 | . . . . . . . . | 6 
5 | . . . . . . . . | 5 
4 | . . . . . . . . | 4 
3 | P . . . . . . . | 3 
2 | . P P P P P P P | 2 
1 | R N B Q K B N R | 1 
   ¯A¯B¯C¯D¯E¯F¯G¯H¯

Примеры матов:
125 MoveVariant{White Rook G1 -> G8, resultWeight=0, moveResult=MOVE}
   _A_B_C_D_E_F_G_H_
8 | . . . k . . R . | 8 
7 | . R . . . . . . | 7 
6 | . . . . . P . . | 6 
5 | . . . . . . . . | 5 
4 | . P . . . . . . | 4 
3 | . . . . . . . . | 3 
2 | . . . . . . . . | 2 
1 | . . . . K . . . | 1 
   ¯A¯B¯C¯D¯E¯F¯G¯H¯

50 MoveVariant{Black Rook C3 -> C1, resultWeight=2, moveResult=EAT}
   _A_B_C_D_E_F_G_H_
8 | . . b . k . r . | 8 
7 | . . . . . p . n | 7 
6 | . . p . p . . . | 6 
5 | . . . . . . p . | 5 
4 | . . . . . . . . | 4 
3 | . . . . P . . . | 3 
2 | . . . . . P P P | 2 
1 | . . r . . . K R | 1 
   ¯A¯B¯C¯D¯E¯F¯G¯H¯
```
