package com.adventofcode.day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class Day07 {


    public static void main(String[] args) {

        File file = new File("/Users/p901zto/Projects/advent-of-code-2023-java/src/main/java/com/adventofcode/day07/input.txt");
        BufferedReader br;
        String st;
        var cards = new ArrayList<Card>();
        var cards2 = new ArrayList<Card>();
        try {
            br = new BufferedReader(new FileReader(file));

            while ((st = br.readLine()) != null) {
                var cardString = st.split(" ");
                var card = new Card(cardString[0], Integer.parseInt(cardString[1]));
                var card2 = new Card(cardString[0], Integer.parseInt(cardString[1]), true);
                cards.add(card);
                cards2.add(card2);

            }

            // System.out.println("RESULT " + cards);


            cards.sort((a, b) -> {
                if (a.type.getValue() == b.type.getValue()) {
                    for (int i = 0; i < a.handList.size(); i++) {
                        if (!Objects.equals(a.handList.get(i), b.handList.get(i))) {
                            return a.handList.get(i) - b.handList.get(i);
                        }
                    }
                }
                return a.type.getValue() - b.type.getValue();
            });


            cards2.sort((a, b) -> {
                if (a.type.getValue() == b.type.getValue() && !a.isJoker && !b.isJoker) {
                    for (int i = 0; i < a.handList.size(); i++) {
                        if (!Objects.equals(a.handList.get(i), b.handList.get(i))) {
                            return a.handList.get(i) - b.handList.get(i);
                        }
                    }
                } else if (a.type.getValue() == b.type.getValue()) {
                    for (int i = 0; i < a.jokerList.size(); i++) {
                        if (!Objects.equals(a.jokerList.get(i), b.jokerList.get(i))) {
                            return a.jokerList.get(i) - b.jokerList.get(i);
                        }
                    }
                }
                return a.type.getValue() - b.type.getValue();
            });

            var i = 0;
            var resultPart1 = 0;
            for (var a : cards) {
                i++;
                resultPart1 += a.getBid() * i;
                //System.out.println(a);
            }

            System.out.println("RESULT PART 1: " + resultPart1);

            var j = 0;
            var resultPart2 = 0;
            for (var a : cards2) {
                j++;
                resultPart2 += a.getBid() * j;
                //System.out.println(a);
            }

            System.out.println("RESULT PART 2: " + resultPart2);

            // 253254392 is too high
            // 252868259 is too low
            // 253253225 correct
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
