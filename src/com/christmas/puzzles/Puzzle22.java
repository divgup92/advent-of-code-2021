package com.christmas.puzzles;

import com.christmas.utils.FileUtils;

public class Puzzle22 {

    private static boolean visited[][];

    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day11.txt");
        int[][] data = new int[splitData.length][splitData[0].length()];
        int steps = 0;

        for(int j=0;j< data.length;j++) {
            for(int k=0;k< data[0].length;k++) {
                data[j][k] = Integer.parseInt(Character.toString(splitData[j].charAt(k)));
            }
        }

        while(true) {
            steps++;
            visited = new boolean[splitData.length][splitData[0].length()];
            for(int j=0;j< data.length;j++) {
                for(int k=0;k< data[0].length;k++) {
                    increment(data, j, k);
                }
            }
            if(checkSimultaneousFlash(data))
                return steps;
        }

    }

    private static void increment(int[][] data, int i, int j) {
        int m = data.length;
        int n = data[0].length;

        if(i<0 || j<0 || i>=m || j>=n || (visited[i][j] && data[i][j] == 0))
            return;

        data[i][j] = (data[i][j]+1) % 10;
        visited[i][j] = true;

        if(data[i][j] == 0) {
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

    private static boolean checkSimultaneousFlash(int data[][]) {
        for(int j=0;j< data.length;j++) {
            for(int k=0;k< data[0].length;k++) {
                if(data[j][k] != 0)
                    return false;
            }
        }
        return true;
    }
}
