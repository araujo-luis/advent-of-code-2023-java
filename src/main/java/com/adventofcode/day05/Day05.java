package com.adventofcode.day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class Day05 {


    public static void main(String[] args) {

        File file = new File("/Users/p901zto/Projects/advent-of-code-2023-java/src/main/java/com/adventofcode/day05/input.txt");
        BufferedReader br;
        String st;

        try {
            br = new BufferedReader(new FileReader(file));

            var seeds = new ArrayList<Long>();
            var seedToSoil = new ArrayList<List<Long>>();
            var soilToFertilizer = new ArrayList<List<Long>>();
            var fertilizerToWater = new ArrayList<List<Long>>();
            var waterToLight = new ArrayList<List<Long>>();
            var lightToTemperature = new ArrayList<List<Long>>();
            var temperatureToHumidity = new ArrayList<List<Long>>();
            var humidityToLocation = new ArrayList<List<Long>>();
            var type = "";
            while ((st = br.readLine()) != null) {
                if (st.isEmpty()) {
                    continue;
                }
                if (st.contains("seeds: ")) {
                    seeds.addAll(Arrays.stream(st.split("seeds: ")[1].split(" ")).map(Long::parseLong).toList());
                    continue;
                }

                if (st.contains("seed-to-soil")) {
                    type = "seed-to-soil";
                    continue;
                } else if (st.contains("soil-to-fertilizer")) {
                    type = "soil-to-fertilizer";
                    continue;
                } else if (st.contains("fertilizer-to-water")) {
                    type = "fertilizer-to-water";
                    continue;
                } else if (st.contains("water-to-light")) {
                    type = "water-to-light";
                    continue;
                } else if (st.contains("light-to-temperature")) {
                    type = "light-to-temperature";
                    continue;
                } else if (st.contains("temperature-to-humidity")) {
                    type = "temperature-to-humidity";
                    continue;
                } else if (st.contains("humidity-to-location")) {
                    type = "humidity-to-location";
                    continue;
                }

                //System.out.println("type " + type);

                switch (type) {
                    case "seed-to-soil" -> seedToSoil.add(Arrays.stream(st.split(" ")).map(Long::parseLong).toList());
                    case "soil-to-fertilizer" ->
                            soilToFertilizer.add(Arrays.stream(st.split(" ")).map(Long::parseLong).toList());
                    case "fertilizer-to-water" ->
                            fertilizerToWater.add(Arrays.stream(st.split(" ")).map(Long::parseLong).toList());
                    case "water-to-light" ->
                            waterToLight.add(Arrays.stream(st.split(" ")).map(Long::parseLong).toList());
                    case "light-to-temperature" ->
                            lightToTemperature.add(Arrays.stream(st.split(" ")).map(Long::parseLong).toList());
                    case "temperature-to-humidity" ->
                            temperatureToHumidity.add(Arrays.stream(st.split(" ")).map(Long::parseLong).toList());
                    case "humidity-to-location" ->
                            humidityToLocation.add(Arrays.stream(st.split(" ")).map(Long::parseLong).toList());
                    default -> throw new IllegalArgumentException();
                }
            }

            //System.out.println("SEEDS: " + seeds);
            //System.out.println("seed-to-soil: " + seedToSoil);
            //System.out.println("soil-to-fertilizer: " + soilToFertilizer);
            //System.out.println("fertilizer-to-water: " + fertilizerToWater);
            //System.out.println("water-to-light: " + waterToLight);
            //System.out.println("light-to-temperature: " + lightToTemperature);
            //System.out.println("temperature-to-humidity: " + temperatureToHumidity);
            //System.out.println("humidity-to-location: " + humidityToLocation);
            var lowest = 10000000000000L;

            for (Long seed : seeds) {
                var result = 0L;
                //System.out.println("------------------");
                result = getLocation(seed, seedToSoil);
                //result = getSeedToSoil(seed, seedToSoil);
                //System.out.println("SOIL: " + result);
                result = getLocation(result, soilToFertilizer);
                //System.out.println("FERTILIZER: " + result);
                result = getLocation(result, fertilizerToWater);
                //System.out.println("WATER: " + result);
                result = getLocation(result, waterToLight);
                //System.out.println("LIGHT: " + result);
                result = getLocation(result, lightToTemperature);
                //System.out.println("TEMPERATURE: " + result);
                result = getLocation(result, temperatureToHumidity);
                //System.out.println("HUMIDITY: " + result);
                result = getLocation(result, humidityToLocation);
                //System.out.println("LOCATION: " + result);
                if (result < lowest) {
                    lowest = result;
                }
            }
            System.out.println("-------------");
            System.out.println("part 1: " + lowest);


            var lowest2 = 10000000000000L;
            var times = 0;
            System.out.println("--------- PART 2 ----------");
            for (var i = 0; i < seeds.size(); i = i + 2) {
                var result = 0L;
                var seedStart = seeds.get(i);
                var seedEnd = seeds.get(i + 1);
                //System.out.println("------------------");
                //System.out.println("J: "+ seedStart + " " + seedEnd);

                for (long j = seedStart; j < seedStart + seedEnd; j++) {
                    if (lowest2 <= j) {
                        System.out.println("SKIPPING " +j);
                        continue;
                    }
                    System.out.println("CHELCKING: "+ j);
                    result = getLocation(j, seedToSoil);
                    //result = getSeedToSoil(seed, seedToSoil);
                    //System.out.println("SOIL: " + result);
                    result = getLocation(result, soilToFertilizer);
                    //System.out.println("FERTILIZER: " + result);
                    result = getLocation(result, fertilizerToWater);
                    //System.out.println("WATER: " + result);
                    result = getLocation(result, waterToLight);
                    //System.out.println("LIGHT: " + result);
                    result = getLocation(result, lightToTemperature);
                    //System.out.println("TEMPERATURE: " + result);
                    result = getLocation(result, temperatureToHumidity);
                    //System.out.println("HUMIDITY: " + result);
                    result = getLocation(result, humidityToLocation);
                    System.out.println("LOCATION: " + result);
                    if (result < lowest2) {
                        lowest2 = result;
                        System.out.println("LOWEST " + lowest2);
                        times++;
                    } else {
                        //System.out.println("NOOO: " + result);
                    }
                }

            }
            System.out.println("part 2: " + lowest2);

            //System.out.println("FINALL: " + getLocation(82L, fertilizerToWater));

/*
 80 -> 83
 81 -> 84
 82 -> 46
 83 -> 47
 83 -> 48
 */
            var result = 0L;
            System.out.println("------------------");
            result = getLocation(82L, seedToSoil);
            //result = getSeedToSoil(seed, seedToSoil);
            System.out.println("SOIL: " + result);
            result = getLocation(result, soilToFertilizer);
            System.out.println("FERTILIZER: " + result);
            result = getLocation(result, fertilizerToWater);
            System.out.println("WATER: " + result);
            result = getLocation(result, waterToLight);
            System.out.println("LIGHT: " + result);
            result = getLocation(result, lightToTemperature);
            System.out.println("TEMPERATURE: " + result);
            result = getLocation(result, temperatureToHumidity);
            System.out.println("HUMIDITY: " + result);
            result = getLocation(result, humidityToLocation);
            System.out.println("LOCATION: " + result);


            // First guess: 107151038  too low
            //              403695602  too high
            //              4053027740 too high
            //              107151038
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        49 53 8
        0 11 42
        42 0 7
        57 7 4
    */
    private static Long getLocation(Long seed, ArrayList<List<Long>> location) {
        var result = seed;
        for (List<Long> map : location) {
            var start = map.get(1);
            var end = map.get(0);
            var range = map.get(2);
            //System.out.println("1: " + (result >= start));
            //System.out.println("2: " + (result <= start + range));
            if (seed >= start && seed < start + range) {
                result = end - start + result;
                //System.out.println("location " + map);
                //System.out.println("end - start: " + (end - start) + " result " + result);
            }
        }
        return result;
    }

}
