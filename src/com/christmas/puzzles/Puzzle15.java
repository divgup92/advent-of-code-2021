package com.christmas.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.christmas.utils.FileUtils;

public class Puzzle15 {
    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day8.txt");
        List<String> input = new ArrayList<>();
        List<String> output = new ArrayList<>();
        int count = 0;

        for(int i=0;i<splitData.length;i++) {
            splitData[i] = splitData[i].substring(splitData[i].indexOf(" | ")+3);
            output.addAll(Arrays.asList(splitData[i].split(" ")));
        }

        System.out.println(output);

        for(String str: output) {
            int len = str.length();
            if(len == 2 || len == 3 || len == 4 || len == 7)
                count++;
        }

        return count;
    }
}


