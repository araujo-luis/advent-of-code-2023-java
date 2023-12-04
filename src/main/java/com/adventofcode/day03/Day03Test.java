package com.adventofcode.day03;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.adventofcode.day03.Day03.getReuslt2;
import static org.junit.Assert.assertEquals;

public class Day03Test {

    @Test
    public void testAddition() {
        var STRING = """
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..
                """;

        var puzzle = getPuzzle(STRING);
        var result = getReuslt2(puzzle);
        assertEquals(result, 467835);
    }

    private static void print(List<List<String>> puzzle) {
        for (List<String> row : puzzle) {
            System.out.println(row);
        }
    }
    private static List<List<String>> getPuzzle(String STRING) {
        List<List<String>> puzzle = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new StringReader(STRING))) {
            String st;
            while ((st = br.readLine()) != null) {
                puzzle.add(Arrays.stream(st.split("")).collect(Collectors.toList()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return puzzle;
    }
}
