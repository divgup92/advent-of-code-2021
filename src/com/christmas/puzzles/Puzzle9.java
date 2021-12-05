package com.christmas.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.christmas.utils.FileUtils;

public class Puzzle9 {
    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day5.txt");
        List<int[]> lines = new ArrayList<>();
        int[][] mat = new int[1000][1000];
        int count = 0;

        for(int i=0;i<splitData.length;i++) {
            int[] line = new int[4];
            String[] splitLine = splitData[i].split(",");
            line[0] = Integer.parseInt(splitLine[0]);
            line[3] = Integer.parseInt(splitLine[2]);
            line[1] = Integer.parseInt(splitLine[1].split(" -> ")[0]);
            line[2] = Integer.parseInt(splitLine[1].split(" -> ")[1]);

            if(line[0] == line[2] || line[1] == line[3]) {
                if(line[0] > line[2])
                    swap(line, 0, 2);
                if(line[1] > line[3])
                    swap(line, 1, 3);
                lines.add(line);
            }
        }

        for(int[] line: lines) {
            for(int i=line[0];i<=line[2];i++) {
                for(int j=line[1];j<=line[3];j++) {
                    mat[i][j]++;
                }
            }
        }

        for(int i=0;i<1000;i++) {
            for(int j=0;j<1000;j++) {
                if(mat[i][j] > 1)
                    count++;
            }
        }

        return count;
    }

    private static void swap(int[] line, int x, int y) {
        int temp = line[x];
        line[x] = line[y];
        line[y] = temp;
    }

}
