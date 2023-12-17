package com.adventofcode.day11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class Day11 {


    public static void main(String[] args) {

        File file = new File("/Users/p901zto/Projects/advent-of-code-2023-java/src/main/java/com/adventofcode/day11/input.txt");
        BufferedReader br;
        String st;

        var lists = new ArrayList<List<String>>();
        try {
            br = new BufferedReader(new FileReader(file));
            var rowIndexes = new ArrayList<Long>();
            long r = 0;
            while ((st = br.readLine()) != null) {

                var row = new ArrayList<>(Arrays.stream(st.split("")).toList());

                var rowSet = new HashSet<>(Arrays.stream(st.split("")).toList());
                if (rowSet.size() == 1) {
                    //var copies = new ArrayList<>(Collections.nCopies(row.size(), "."));
                    //lists.add(copies);
                    rowIndexes.add(r);
                }
                r++;
                lists.add(row);
            }

            System.out.println("rowIndexes " + rowIndexes);

            var columnsIndexes = new ArrayList<Integer>();
            for (int i = 0; i < lists.get(0).size(); i++) {
                var columnSet = new HashSet<String>();
                for (List<String> list : lists) {
                    columnSet.add(list.get(i));
                    if (columnSet.size() > 1) {
                        break;
                    }
                }
                if (columnSet.size() == 1) {
                    columnsIndexes.add(i);
                }
            }

            System.out.println("columnsIndexes" + columnsIndexes);
/*
            var p = 0;
            for (var i : columnsIndexes) {

                for (List<String> list : lists) {
                    list.add(((Integer) i) + p, ".");
                }
                p++;

            }
*/
            var pairs = new ArrayList<String>();
            for (int i = 0; i < lists.size(); i++) {
                for (int j = 0; j < lists.get(i).size(); j++) {
                    if (lists.get(i).get(j).equals("#")) {
                        pairs.add(i + "-" + j);
                    }
                }
            }

            System.out.println("pairs" + pairs);

            for (var list : lists) {
                System.out.println(list);
            }

            var possiblePairs = new HashSet<String>();
            for (int i = 0; i < pairs.size(); i++) {
                for (String pair : pairs) {
                    if (!pairs.get(i).equals(pair) && !possiblePairs.contains(pair + ";" + pairs.get(i))) {
                        possiblePairs.add(pairs.get(i) + ";" + pair);
                    }
                }
            }

            //System.out.println("POS" + possiblePairs + " " + possiblePairs.size());

            var pairsArray = new ArrayList<>(possiblePairs);

            long result1 = calculate(pairsArray, rowIndexes, columnsIndexes, 2);
            long result2 = calculate(pairsArray, rowIndexes, columnsIndexes, 1000000);

            System.out.println("part 1: " + (result1));
            System.out.println("part 2: " + (result2));


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static long calculate(ArrayList<String> pairsArray, ArrayList<Long> rowIndexes, ArrayList<Integer> columnsIndexes, int galaxiesTimes) {
        long result = 0;
        for (String s : pairsArray) {
            var pair = s.split(";");

            var startX = Long.parseLong(pair[0].split("-")[0]);
            var startY = Long.parseLong(pair[0].split("-")[1]);

            var endX = Long.parseLong(pair[1].split("-")[0]);
            var endY = Long.parseLong(pair[1].split("-")[1]);

            var posX = 0;
            for (Long rowIndex : rowIndexes) {
                if ((rowIndex >= startX && rowIndex <= endX) || (rowIndex >= endX && rowIndex <= startX)) {
                    posX+=galaxiesTimes-1;
                }
            }
            long posY = 0;

            for (Integer columnIndex : columnsIndexes) {
                if ((columnIndex >= startY && columnIndex <= endY) || (columnIndex >= endY && columnIndex <= startY)) {
                    posY+=galaxiesTimes-1;
                }
            }
            result += Math.abs(startX - endX) + Math.abs(startY - endY) + posY + posX;
        }
        return result;
    }
}
