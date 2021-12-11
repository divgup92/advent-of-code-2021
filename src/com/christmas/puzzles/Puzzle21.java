package com.christmas.puzzles;

import java.util.Arrays;

import com.christmas.utils.FileUtils;

public class Puzzle21 {

    private static boolean visited[][];
    private static int steps = 100;
    private static int flashes = 0;

    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day11.txt");
        int[][] data = new int[splitData.length][splitData[0].length()];

        for(int j=0;j< data.length;j++) {
            for(int k=0;k< data[0].length;k++) {
                data[j][k] = Integer.parseInt(Character.toString(splitData[j].charAt(k)));
            }
        }

        for(int i=0;i<steps;i++) {
            visited = new boolean[splitData.length][splitData[0].length()];
            for(int j=0;j< data.length;j++) {
                for(int k=0;k< data[0].length;k++) {
                    increment(data, j, k);
                }
            }
        }

        return flashes;
    }

    private static void increment(int[][] data, int i, int j) {
        int m = data.length;
        int n = data[0].length;

        if(i<0 || j<0 || i>=m || j>=n || (visited[i][j] && data[i][j] == 0))
            return;

        data[i][j] = (data[i][j]+1) % 10;
        visited[i][j] = true;

        if(data[i][j] == 0) {
            flashes++;
            increment(data, i+1, j);
            increment(data, i-1, j);
            increment(data, i, j+1);
            increment(data, i, j-1);
            increment(data, i-1, j-1);
            increment(data, i+1, j+1);
            increment(data, i-1, j+1);
            increment(data, i+1, j-1);
        }

    }
}
