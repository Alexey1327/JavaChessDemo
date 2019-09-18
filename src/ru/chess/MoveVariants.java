package ru.chess;

import java.util.ArrayList;

class MoveVariants extends ArrayList<MoveVariant> {

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Variants {" + "Count=").append(this.size()).append("}\n");

        for (MoveVariant variant : this) {
            result.append("\t").append(variant).append("\n");
        }

        return result.toString();
    }
}
