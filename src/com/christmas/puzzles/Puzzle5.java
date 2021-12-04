package com.christmas.puzzles;

import com.christmas.utils.FileUtils;

public class Puzzle5 {

    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day3.txt");
        int numBits = splitData[0].length();
        int[] count = new int[splitData[0].length()];
        String gamma = "";
        String epsilon = "";

        for(int i=0;i<splitData.length;i++) {
            for(int j=0;j<numBits;j++) {
                if(splitData[i].charAt(j) == '0')
                    count[j]--;
                else
                    count[j]++;
            }
        }

        for(int i=0;i<numBits;i++) {
            if(count[i] > 0) {
                gamma += "1";
                epsilon += "0";
            } else {
                gamma += "0";
                epsilon += "1";
            }
        }

        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
    }
}
