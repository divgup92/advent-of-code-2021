package com.christmas.puzzles;

import com.christmas.utils.FileUtils;

public class Puzzle4 {

    public static int solve() {
        int x = 0;
        int y = 0;
        int aim = 0;
        String data = FileUtils.readFileToString("src/input/day2.txt");

        String[] splitData = data.split("\n");

        for(int i=0;i<splitData.length;i++) {
            if(splitData[i].startsWith("forward")) {
                int value = Integer.parseInt(splitData[i].substring(splitData[i].indexOf(' ')+1));
                x += value;
                y += aim * value;
            } else if(splitData[i].startsWith("down")) {
                aim += Integer.parseInt(splitData[i].substring(splitData[i].indexOf(' ')+1));
            } else {
                aim -= Integer.parseInt(splitData[i].substring(splitData[i].indexOf(' ')+1));
            }
            System.out.println(splitData[i] + " " + x + " " + y);
        }

        return x*y;
    }
}
