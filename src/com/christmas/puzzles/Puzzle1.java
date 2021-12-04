package com.christmas.puzzles;

import com.christmas.utils.FileUtils;

public class Puzzle1 {

    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day1.txt");
        int prev = Integer.parseInt(splitData[0]);
        int count = 0;

        for(int i=1;i<splitData.length;i++) {
            int cur = Integer.parseInt(splitData[i]);
            if(cur > prev) {
                count++;
            }
            prev = cur;
        }

        return count;
    }
}
