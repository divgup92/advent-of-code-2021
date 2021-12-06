package com.christmas.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.christmas.utils.FileUtils;

public class Puzzle11 {
    public static int solve() {
        String[] data = FileUtils.readFileToStringArray("src/input/day6.txt")[0].split(",");

        List<Integer> splitData = Arrays.stream(data)
                .map(str -> Integer.parseInt(str))
                .collect(Collectors.toList());

        List<Integer> splitDataCopy = new ArrayList<>();

        for (int i = 0; i < 80; i++) {
            for (Integer point : splitData) {

                if (point.equals(0)) {
                    splitDataCopy.add(6);
                    splitDataCopy.add(8);
                } else {
                    splitDataCopy.add(point-1);
                }
            }

            splitData = splitDataCopy;
            splitDataCopy = new ArrayList<>();
        }

        return splitData.size();
    }

}
