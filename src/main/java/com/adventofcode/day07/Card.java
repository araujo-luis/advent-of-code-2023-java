package com.adventofcode.day07;

import jdk.jshell.spi.ExecutionControl;

import java.sql.Array;
import java.util.*;

public class Card {
    String hand;
    int bid;
    CardType type;

    List<Integer> handList;

    List<Integer> jokerList;

    Boolean isJoker = false;

    public Card(String hand, int bid) {
        this.hand = hand;
        this.bid = bid;
        this.type = calculateCardType(hand);
        this.handList = getHandList(hand);
    }

    public Card(String hand, int bid, boolean type2) {
        this.hand = hand;
        this.bid = bid;
        this.type = calculateCardType2(hand);
        this.handList = getHandList(hand);
    }

    Map<String, Integer> map = new HashMap<>() {
        {
            put("A", 14);
            put("K", 13);
            put("Q", 12);
            put("J", 11);
            put("T", 10);
            put("9", 9);
            put("8", 8);
            put("7", 7);
            put("6", 6);
            put("5", 5);
            put("4", 4);
            put("3", 3);
            put("2", 2);
        }
    };

    private List<Integer> getHandList(String hand) {
        return Arrays.stream(hand.split("")).map(a -> {
            try {
                return Integer.parseInt(a);
            } catch (Exception e) {
                return map.get(a);
            }
        }).toList();
    }

    private List<Integer> getJokerList(String hand) {
        return Arrays.stream(hand.split("")).map(a -> {
            try {
                return Integer.parseInt(a);
            } catch (Exception e) {
                if (a.equals("J")) {
                    return 1;
                }
                return map.get(a);
            }
        }).toList();
    }

    private CardType calculateCardType(String hand) {
        var array = hand.split("");
        var set = new HashSet<>(List.of(array));
        var setArray = new ArrayList<>(set);

        if (set.size() == 1) {
            return CardType.FIVE_KIND;
        } else if (set.size() == 2) {
            var firstOccurrence = getOcurrences(hand, setArray.get(0).charAt(0));
            var secondOccurrence = getOcurrences(hand, setArray.get(1).charAt(0));

            if ((firstOccurrence == 1 && secondOccurrence == 4) || (firstOccurrence == 4 && secondOccurrence == 1)) {
                return CardType.FOUR_KIND;
            } else {
                return CardType.FULL_HOUSE;
            }
        } else if (set.size() == 3) {
            var firstOccurrence = getOcurrences(hand, setArray.get(0).charAt(0));
            var secondOccurrence = getOcurrences(hand, setArray.get(1).charAt(0));
            var thridOccurrence = getOcurrences(hand, setArray.get(2).charAt(0));

            if (firstOccurrence == 3 || secondOccurrence == 3 || thridOccurrence == 3) {
                return CardType.THREE_KIND;
            } else {
                return CardType.TWO_PAIR;
            }
        } else if (set.size() == 4) {
            return CardType.ONE_PAIR;
        } else if (set.size() == 5) {
            return CardType.HIGH_CARD;
        }
        return null;
    }

    private CardType calculateCardType2(String hand) {
        var array = hand.split("");
        var set = new HashSet<>(List.of(array));
        var setArray = new ArrayList<>(set);

        if (set.size() == 1) {
            if(set.contains("J")) {
                this.isJoker = true;
                this.jokerList = getJokerList(hand);
            } else {
                this.jokerList = getHandList(hand);
            }
            return CardType.FIVE_KIND;
        } else if (set.size() == 2) {
            var firstOccurrence = getOcurrences(hand, setArray.get(0).charAt(0));
            var secondOccurrence = getOcurrences(hand, setArray.get(1).charAt(0));
            var jokerOccurrences = getJokerOcurrences(hand);
            if (jokerOccurrences == 0) {
                this.jokerList = getHandList(hand);
                if ((firstOccurrence == 1 && secondOccurrence == 4) || (firstOccurrence == 4 && secondOccurrence == 1)) {
                    return CardType.FOUR_KIND;
                } else {
                    return CardType.FULL_HOUSE;
                }
            } else {
                this.isJoker = true;
                this.jokerList = getJokerList(hand);
                return CardType.FIVE_KIND;
            }

        } else if (set.size() == 3) {
            var firstOccurrence = getOcurrences(hand, setArray.get(0).charAt(0));
            var secondOccurrence = getOcurrences(hand, setArray.get(1).charAt(0));
            var thridOccurrence = getOcurrences(hand, setArray.get(2).charAt(0));
            var jokerOccurrences = getJokerOcurrences(hand);

            if (jokerOccurrences == 0) {
                this.jokerList = getHandList(hand);

                if (firstOccurrence == 3 || secondOccurrence == 3 || thridOccurrence == 3) {
                    return CardType.THREE_KIND;
                } else {
                    return CardType.TWO_PAIR;
                }
            } else {
                this.isJoker = true;
                this.jokerList = getJokerList(hand);
                if ((firstOccurrence == 1 && !setArray.get(0).equals("J")) || (secondOccurrence == 1 && !setArray.get(1).equals("J")) || (thridOccurrence == 1 && !setArray.get(2).equals("J"))) {

                    return CardType.FOUR_KIND;
                }
                return CardType.FULL_HOUSE;
            }
        } else if (set.size() == 4) {
            var jokerOccurrences = getJokerOcurrences(hand);
            if (jokerOccurrences == 0) {
                this.jokerList = getHandList(hand);
                return CardType.ONE_PAIR;
            } else {
                this.isJoker = true;
                this.jokerList = getJokerList(hand);
                return CardType.THREE_KIND;
            }

        } else if (set.size() == 5) {
            var jokerOccurrences = getJokerOcurrences(hand);

            if (jokerOccurrences == 0) {
                this.jokerList = getHandList(hand);
                return CardType.HIGH_CARD;
            } else {
                this.isJoker = true;
                this.jokerList = getJokerList(hand);
                return CardType.ONE_PAIR;
            }
        }
        return null;
    }

    private int getJokerIndex(String hand, HashSet<Integer> set) {
        var toArray = new ArrayList<>(set);
        return toArray.indexOf("J");
    }

    private int getOcurrences(String array, char string) {
        return (int) array.chars().filter(a -> a == string).count();
    }

    private int getJokerOcurrences(String array) {
        return (int) array.chars().filter(a -> a == 'J').count();
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public String getHand() {
        return hand;
    }

    public int getBid() {
        return bid;
    }

    public CardType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "{" +
                "hand='" + hand + '\'' +
                ", bid=" + bid +
                ", type=" + type +
                ", handList=" + handList +
                ", jokerList=" + jokerList +
                ", isJoker=" + isJoker +
                '}';
    }
}
