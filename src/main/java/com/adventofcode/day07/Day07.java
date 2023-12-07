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
        var map = new HashMap<String, Integer>() {
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
        try {
            br = new BufferedReader(new FileReader(file));

            while ((st = br.readLine()) != null) {
                var cardString = st.split(" ");
                var card = new Card(cardString[0], Integer.parseInt(cardString[1]));
                cards.add(card);
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


            var i = 0;
            var resultPart1 = 0;
            var previousType = -1;
            for (var a : cards) {
                i++;
                resultPart1 += a.getBid() * i;
                System.out.println(a);
            }

            System.out.println("RESULT PART 1: " + resultPart1);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
