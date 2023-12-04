package com.adventofcode.day03;

import com.adventofcode.day02.Game;
import com.adventofcode.day02.GameSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day03 {

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isSpecialCharacter(String str) {
        return str.matches("[^.]");
    }

    public static void main(String[] args) {

        File file = new File("/Users/p901zto/Projects/advent-of-code-2023-java/src/main/java/com/adventofcode/day03/input.txt");
        BufferedReader br;
        String st;

        try {
            br = new BufferedReader(new FileReader(file));
            List<List<String>> puzzle = new ArrayList<>();
            while ((st = br.readLine()) != null) {
                puzzle.add(Arrays.stream(st.split("")).collect(Collectors.toList()));
            }
            var reuslt = 0;
            var reuslt2 = 0;

            for (int i = 0; i < puzzle.size(); i++) {
                for (int j = 0; j < puzzle.get(i).size(); j++) {

                    var number = "";
                    var times = 0;
                    var isValid = false;
                    if (isNumeric(puzzle.get(i).get(j))) {
                        number += puzzle.get(i).get(j);
                        times++;
                        if (j + 1 < puzzle.get(i).size() && isNumeric(puzzle.get(i).get(j + 1))) {
                            number += puzzle.get(i).get(j + 1);
                            times++;
                            if (j + 2 < puzzle.get(i).size() && isNumeric(puzzle.get(i).get(j + 2))) {
                                number += puzzle.get(i).get(j + 2);
                                times++;
                            }
                        }
                        for (int k = 0; k < times + 2; k++) {
                            // UP
                            if (i - 1 >= 0 && (i - 1) <= puzzle.get(i).size() && (j - 1 + k) < puzzle.get(i).size() && j - 1 + k >= 0) {
                                if (isSpecialCharacter(puzzle.get(i - 1).get(j - 1 + k))) {
                                    isValid = true;
                                    break;
                                }
                            }
                            // DOWN
                            if (i + 1 < puzzle.size() && (i + 1) <= puzzle.get(i).size() && (j - 1 + k) < puzzle.size() && (j - 1 + k) >= 0) {
                                if (isSpecialCharacter(puzzle.get(i + 1).get(j - 1 + k))) {
                                    isValid = true;
                                    break;
                                }
                            }
                        }
                        // RIGHT
                        if ((j + times) < puzzle.get(i).size()) {
                            if (isSpecialCharacter(puzzle.get(i).get(j + times))) {
                                isValid = true;
                            }
                        }
                        // LEFT
                        if (j - 1 >= 0) {
                            if (isSpecialCharacter(puzzle.get(i).get(j - 1))) {
                                isValid = true;
                            }
                        }
                        if (isValid) {
                            reuslt += Integer.parseInt(number);
                        }

                        for (int k = 0; k < times; k++) {
                            puzzle.get(i).set(j + k, number);
                        }
                        j = j + times;
                    }

                }
            }

            System.out.println("PART 1: " + reuslt);
            /*
            for (List<String> row : puzzle) {
                System.out.println(row);
            }
            System.out.println("-------------------------");
               */
            reuslt2 = getReuslt2(puzzle);

            System.out.println("PART 2: " + reuslt2);

        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getReuslt2(List<List<String>> puzzle) {
        var numbers = new ArrayList<String>();
        int reuslt2 = 0;

        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(i).size(); j++) {
                if (puzzle.get(i).get(j).equals("*")) {
                    // UP
                    for (int k = 0; k < 3; k++) {
                        if (i - 1 >= 0 && (i - 1) <= puzzle.get(i).size() && (j - 1 + k) < puzzle.get(i).size() && j - 1 + k >= 0) {
                            if (isNumeric(puzzle.get(i - 1).get(j - 1 + k))) {
                                numbers.add(puzzle.get(i - 1).get(j - 1 + k));

                                if (!puzzle.get(i - 1).get(j).equals(".")) {
                                    break;
                                }
                            }
                        }
                    }
                    // DOWN
                    for (int k = 0; k < 3; k++) {

                        if (i + 1 < puzzle.size() && (i + 1) <= puzzle.get(i).size() && (j - 1 + k) < puzzle.get(i).size() && (j - 1 + k) >= 0) {

                            if (isNumeric(puzzle.get(i + 1).get(j - 1 + k))) {
                                numbers.add(puzzle.get(i + 1).get(j - 1 + k));


                                if (!puzzle.get(i + 1).get(j).equals(".")) {
                                    break;
                                }

                            }
                        }
                    }
                    // RIGHT
                    if ((j + 1) < puzzle.get(i).size()) {
                        if (isNumeric(puzzle.get(i).get(j + 1))) {
                            numbers.add(puzzle.get(i).get(j + 1));
                        }
                    }

                    // LEFT
                    if (j - 1 >= 0) {
                        if (isNumeric(puzzle.get(i).get(j - 1))) {
                            numbers.add(puzzle.get(i).get(j - 1));
                        }
                    }
                    if (numbers.size() == 2) {
                        reuslt2 += (Integer.parseInt(numbers.get(0)) * Integer.parseInt(numbers.get(1)));
                    }

                    numbers.clear();
                }
            }
        }
        return reuslt2;
    }

}
