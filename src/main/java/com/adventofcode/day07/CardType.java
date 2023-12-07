package com.adventofcode.day07;

public enum CardType {
    FIVE_KIND(7),
    FOUR_KIND(6),
    FULL_HOUSE(5),
    THREE_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1)
    ;
    private final int value;
    CardType(int i) {
        this.value = i;
    }
    public int getValue() {
        return value;
    }

}
