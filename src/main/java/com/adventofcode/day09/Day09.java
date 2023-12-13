package com.adventofcode.day09;

import com.adventofcode.day07.Card;
import com.adventofcode.day08.StartNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day09 {


    public static void main(String[] args) {

        File file = new File("/Users/p901zto/Projects/advent-of-code-2023-java/src/main/java/com/adventofcode/day09/input.txt");
        BufferedReader br;
        String st;

        var lists = new ArrayList<List<Integer>>();
        try {
            br = new BufferedReader(new FileReader(file));
            while ((st = br.readLine()) != null) {
                lists.add(Arrays.stream(st.split(" ")).map(Integer::parseInt).toList());
            }

            System.out.println("RESULT" + lists);

            var result = 0;
            var result2 = 0;

            for (List<Integer> currentList : lists) {
                result += calculate(currentList, false) + currentList.get(currentList.size() - 1);
                result2 += currentList.get(0) - calculate(currentList, true);
            }

            System.out.println("RESULT: " + result);
            System.out.println("RESULT2 : " + result2);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int calculate(List<Integer> currentList, boolean part2) {
        var array = new ArrayList<Integer>();
        for (int i = 0; i < currentList.size() - 1; i++) {
            array.add(currentList.get(i + 1) - currentList.get(i));
        }
        var hash = new HashSet<>(array);
        if (hash.size() == 1) {
            if (part2) {
                return array.get(0);
            } else {
                return array.get(array.size() - 1);
            }
        } else {
            if (part2) {
                return array.get(0) - calculate(array.stream().toList(), part2);
            } else {
                return array.get(array.size() - 1) + calculate(array.stream().toList(), part2);
            }

        }

    }

}
