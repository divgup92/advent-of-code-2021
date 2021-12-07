package com.christmas.puzzles;

import java.util.Arrays;

import com.christmas.utils.FileUtils;

public class Puzzle14 {
    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day7.txt");

        int[] input = Arrays.stream(splitData[0].split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        int minSum = Integer.MAX_VALUE;

        for (int i = 1; i < 1500; i++) {
            int curSum = 0;
            for (int j = 0; j < input.length; j++) {
                int k = Math.abs(input[j] - i);
                curSum += (k*(k+1))/2;
            }

            if (curSum < minSum) {
                minSum = curSum;
            }

        }

        return minSum;
    }
}


