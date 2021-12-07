package com.christmas.puzzles;

import java.util.Arrays;

import com.christmas.utils.FileUtils;

public class Puzzle13 {
    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day7.txt");

        int[] input = Arrays.stream(splitData[0].split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        int minSum = Integer.MAX_VALUE;

        for (int i = 1; i < 1500; i++) {
            int curSum = 0;
            for (int j = 0; j < input.length; j++) {
                curSum += Math.abs(input[j] - i);
            }

            if (curSum < minSum) {
                minSum = curSum;
            }

        }

        return minSum;
    }
}


