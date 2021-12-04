package com.christmas.puzzles;

import com.christmas.utils.FileUtils;

public class Puzzle2 {

    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day1.txt");
        int prevSum = 0;
        for(int i=0;i<3;i++)
            prevSum += Integer.parseInt(splitData[i]);
        int count = 0;

        for(int i=3;i<splitData.length;i++) {
            int curSum = prevSum + Integer.parseInt(splitData[i]) - Integer.parseInt(splitData[i-3]);
            if(curSum > prevSum) {
                count++;
            }
            prevSum = curSum;
        }

        return count;
    }
}
