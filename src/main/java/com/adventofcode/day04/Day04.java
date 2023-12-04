package com.adventofcode.day04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day04 {


    public static void main(String[] args) {

        File file = new File("/Users/p901zto/Projects/advent-of-code-2023-java/src/main/java/com/adventofcode/day04/input.txt");
        BufferedReader br;
        String st;

        try {
            br = new BufferedReader(new FileReader(file));
            List<List<String>> puzzle = new ArrayList<>();
            var resultPart1 = 0;

            var copies = new ArrayList<>(Collections.nCopies(1000, 0));
            var i = 0;
            while ((st = br.readLine()) != null) {
                var card = st.split(": ")[1];
                var game = card.split(" \\|\\ ");
                var winningNumbers = new HashSet<>(Arrays.stream(game[0].split(" ")).filter(n -> !n.isEmpty()).map(Integer::parseInt).toList());
                var myNumbers = Arrays.stream((game[1].split(" "))).filter(n -> !n.isEmpty()).map(Integer::parseInt).toList();
                var matches = 0;
                copies.set(i, copies.get(i) + 1);

                for (Integer num : myNumbers) {
                    if (winningNumbers.contains(num)) {
                        matches++;
                    }
                }

                for (int k = 0; k < copies.get(i); k++) {
                    for (int j = 1; j <= matches; j++) {
                        copies.set(j + i, copies.get(j + i) + 1);
                    }
                }
                if (matches > 1) {
                    resultPart1 += (int) Math.pow(2, matches - 1);
                } else {
                    resultPart1 += matches;
                }
                i++;
            }

            System.out.println("PART 1 " + resultPart1);
            System.out.println("PART 2: " + copies.stream().mapToInt(A-> A).sum());

        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
