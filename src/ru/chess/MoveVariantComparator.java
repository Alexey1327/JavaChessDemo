package ru.chess;

import java.util.Comparator;

public class MoveVariantComparator implements Comparator<MoveVariant> {

    @Override
    public int compare(MoveVariant moveVariant, MoveVariant t1) {
        return -Integer.compare(moveVariant.getResultWeight(), t1.getResultWeight());
    }
}
