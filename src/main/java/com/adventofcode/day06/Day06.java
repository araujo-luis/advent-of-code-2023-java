package com.adventofcode.day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {


    public static void main(String[] args) {

        File file = new File("/Users/p901zto/Projects/advent-of-code-2023-java/src/main/java/com/adventofcode/day06/input.txt");
        BufferedReader br;
        String st;
        var times = new ArrayList<Long>();
        var distances = new ArrayList<Long>();

        try {
            br = new BufferedReader(new FileReader(file));

            while ((st = br.readLine()) != null) {
                if (st.contains("Time")) {
                    times.addAll(Arrays.stream(st.split("Time:")[1].split(" ")).filter(a -> !a.equals(" ") && !a.isEmpty()).map(Long::parseLong).toList());
                } else {
                    distances.addAll(Arrays.stream(st.split("Distance:")[1].split(" ")).filter(a -> !a.equals(" ") && !a.isEmpty()).map(Long::parseLong).toList());
                }
            }

            System.out.println("TIME: " + times);
            System.out.println("DISTANCE: " + distances);

            var result = 1;
            for (int i = 0; i < times.size(); i++) {
                var time = times.get(i);
                var distance = distances.get(i);
                var occurences = 0;
                for (int j = 0; j < time; j++) {
                    if (((time - j) * j) > distance) {
                        occurences++;
                    }
                }
                result *= occurences;

            }
            System.out.println("RESULT " + result);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
