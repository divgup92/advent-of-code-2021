package com.christmas.puzzles;

import com.christmas.utils.FileUtils;

public class Puzzle3 {

    public static int solve() {
        int x = 0;
        int y = 0;
        String data = FileUtils.readFileToString("src/input/day2.txt");

        String[] splitData = data.split("\n");

        for(int i=0;i<splitData.length;i++) {
            if(splitData[i].startsWith("forward")) {
                x += Integer.parseInt(splitData[i].substring(splitData[i].indexOf(' ')+1));
            } else if(splitData[i].startsWith("down")) {
                y += Integer.parseInt(splitData[i].substring(splitData[i].indexOf(' ')+1));
            } else {
                y -= Integer.parseInt(splitData[i].substring(splitData[i].indexOf(' ')+1));
            }
        }

        return x*y;
    }
}
