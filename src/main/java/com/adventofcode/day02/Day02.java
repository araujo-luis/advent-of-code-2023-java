package com.adventofcode.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day02 {

    public static void main(String[] args) {

        File file = new File("/Users/p901zto/Projects/advent-of-code-2023-java/src/main/java/com/adventofcode/day02/input.txt");
        BufferedReader br;
        String st;


        try {
            br = new BufferedReader(new FileReader(file));
            List<Game> games  = new ArrayList<>();
            while ((st = br.readLine()) != null) {
                //var myGame = new Game();
                var game = st.split(": ");
                var gameId = game[0].split(" ")[1];


                var gameSetsString = game[1].split("; ");


                List<GameSet> gameSets = new ArrayList<>(List.of());
                //System.out.println("gameSetsString length " + gameSetsString.length);

                for (String string : gameSetsString) {

                    var gameSet = string.split(", ");

                    var newGameSet = new GameSet();
                    //System.out.println("length " + gameSet.length);
                    //System.out.println("gameSet " + Arrays.toString(gameSet));
                    for (String s : gameSet) {
                        var myGame = s.split(" ");
                        if (myGame[1].contains("red")) {
                            newGameSet.setRed(myGame[0]);
                        } else if (myGame[1].contains("green")) {
                            newGameSet.setGreen(myGame[0]);
                        } else if (myGame[1].contains("blue")) {
                            newGameSet.setBlue(myGame[0]);
                        }
                    }
                    gameSets.add(newGameSet);

                }

                games.add(new Game(Integer.parseInt(gameId), gameSets));

            }
            var redContraint = 12;
            var greenContraint = 13;
            var blueContraint = 14;
            var result = 0;
            for(var game: games){
                System.out.println("GAME " + game);
                System.out.println("SIEZE " + game.getGames().size());
                var possibleGames = game.getGames().stream().filter(g -> g.getBlue() > blueContraint || g.getRed() > redContraint || g.getGreen() > greenContraint).toList();
                if (possibleGames.isEmpty()){
                    result += game.id;
                }
            }

            System.out.println("result " + result);

            var result2 =  0;
            for(var game: games){
                var blue = game.getGames().stream().map(GameSet::getBlue).max(Integer::compare).get();
                var green = game.getGames().stream().map(GameSet::getGreen).max(Integer::compare).get();
                var red = game.getGames().stream().map(GameSet::getRed).max(Integer::compare).get();
                result2 += blue*green*red;


            }

            System.out.println("result2: " + result2);





        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

}
