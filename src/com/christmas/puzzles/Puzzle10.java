package com.christmas.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.christmas.utils.FileUtils;

public class Puzzle10 {
    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day5.txt");
        List<int[]> linesDiagonal = new ArrayList<>();
        List<int[]> simpleLines = new ArrayList<>();
        int[][] mat = new int[1000][1000];
        int count = 0;

        for (int i = 0; i < splitData.length; i++) {
            int[] line = new int[6];
            String[] splitLine = splitData[i].split(",");
            line[0] = Integer.parseInt(splitLine[0]);
            line[3] = Integer.parseInt(splitLine[2]);
            line[1] = Integer.parseInt(splitLine[1].split(" -> ")[0]);
            line[2] = Integer.parseInt(splitLine[1].split(" -> ")[1]);

            line[4] = (line[0] > line[2]) ? -1 : 1;
            line[5] = (line[1] > line[3]) ? -1 : 1;
            if(line[0] == line[2] || line[1] == line[3]) {
                if(line[0] > line[2])
                    swap(line, 0, 2);
                if(line[1] > line[3])
                    swap(line, 1, 3);
                simpleLines.add(line);
            } else {
                linesDiagonal.add(line);
            }

        }

        for(int[] line: simpleLines) {
            for(int i=line[0];i<=line[2];i++) {
                for(int j=line[1];j<=line[3];j++) {
                    mat[i][j]++;
                }
            }
        }

        for (int[] line : linesDiagonal) {
            if (line[4] == 1 && line[5] == 1) {
                for (int i = line[0], j = line[1]; i <= line[2] && j <= line[3]; i++, j++) {
                    mat[i][j]++;
                }
            } else if (line[4] == 1 && line[5] == -1) {
                for (int i = line[0], j = line[1]; i <= line[2] && j >= line[3]; i++, j--) {
                    mat[i][j]++;
                }
            } else if (line[4] == -1 && line[5] == 1) {
                for (int i = line[0], j = line[1]; i >= line[2] && j <= line[3]; i--, j++) {
                    mat[i][j]++;
                }
            } else {
                for (int i = line[0], j = line[1]; i >= line[2] && j >= line[3]; i--, j--) {
                    mat[i][j]++;
                }
            }

        }


        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (mat[i][j] > 1)
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
