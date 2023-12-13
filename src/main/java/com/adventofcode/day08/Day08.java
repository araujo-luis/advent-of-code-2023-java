package com.adventofcode.day08;

import com.adventofcode.day07.Card;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day08 {


    public static void main(String[] args) {

        File file = new File("/Users/p901zto/Projects/advent-of-code-2023-java/src/main/java/com/adventofcode/day08/input.txt");
        BufferedReader br;
        String st;
        var cards = new ArrayList<Card>();
        var cards2 = new ArrayList<Card>();
        try {
            br = new BufferedReader(new FileReader(file));
            var instructions = Arrays.stream(br.readLine().split("")).toList();
            var map = new HashMap<String, List<String>>();
            var startingNodes = new HashMap<String, StartNode>();
            br.readLine();
            while ((st = br.readLine()) != null) {
                var row = st.split(" = ");

                var key = row[0];
                if (key.endsWith("A")) {
                    startingNodes.put(key, new StartNode(key));
                }
                var value = row[1].split(", ");
                map.put(key, List.of(value[0].substring(1, 4), value[1].substring(0, 3)));
            }

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            System.out.println(instructions);
            /*
            var current = "AAA";
            var end = "ZZZ";
            var steps = 0;
            var found = false;
            for (int j = 0; j < 100; j++) {
                for (String s : instructions) {
                    steps++;
                    var value = map.get(current);
                    if (s.equals("R")) {
                        current = value.get(1);
                    } else {
                        current = value.get(0);
                    }

                    if (current.equals(end)) {
                        found = true;
                        break;
                    }
                }
                if (current.equals(end)) {
                    break;
                }
            }

            System.out.println("RESULT PART 1: " + steps + " " + found);
            */

            System.out.println("startingNodes " + startingNodes);

            long steps = 0;
            var done = false;
            for (int i = 0; i < 150500; i++) {
                for (int j = 0; j < instructions.size(); j++) {
                    var endsCounter = 0;

                    var instruction = instructions.get(j);
                    //System.out.println("--------" + j + instruction);

                    steps++;

                    for (Map.Entry<String, StartNode> entry : startingNodes.entrySet()) {
                        var currentNode = entry.getValue();
                        var startNode = currentNode.getStartNode();

                        var currentMapNode = map.get(startNode);

                        //if (!currentNode.isDone) {
                        if (instruction.equals("R")) {
                            currentNode.setCurrentNode(currentMapNode.get(1));
                        } else {
                            currentNode.setCurrentNode(currentMapNode.get(0));
                        }
                        //}
                        //System.out.println("GETTING " + currentNode + instruction);
                        if (currentNode.getCurrentNode().endsWith("Z") ) {
                            currentNode.isDone = true;

                            endsCounter++;
                            //currentNode.setStartNode(startNode);
                            currentNode.setStartNode(currentNode.getCurrentNode());

                        } else {
                            currentNode.setStartNode(currentNode.getCurrentNode());
                        }

                        //System.out.println("endsCounter " + endsCounter + " steps: " + steps);

                        if (endsCounter== startingNodes.size()) {
                            done = true;
                            break;
                        }
                    }
                    if (done) {
                        break;
                    }
                }
                if (done) {
                    break;
                }
            }

            System.out.println("part 2: " + steps);

            // 1172 too low
            // 29300 TOO LOW

            //{'L': { 'AAA': 'BBB' }, 'R': {} }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
