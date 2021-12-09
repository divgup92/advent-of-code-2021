package com.christmas.puzzles;

import java.util.ArrayList;
import java.util.List;

import com.christmas.utils.FileUtils;

public class Puzzle18 {
    static List<Integer> areaList = new ArrayList<>();
    static boolean[][] visited;

    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day9.txt");
        int[][] input = new int[splitData.length][splitData[0].length()];
        visited = new boolean[splitData.length][splitData[0].length()];

        for (int i = 0; i < splitData.length; i++) {
            for (int j = 0; j < splitData[i].length(); j++) {
                input[i][j] = Integer.parseInt(Character.toString(splitData[i].charAt(j)));
            }
        }

        populateAreaList(input);

        areaList.sort(Integer::compareTo);

        return areaList.get(areaList.size() - 1) * areaList.get(areaList.size() - 2) * areaList.get(areaList.size() - 3);
    }

    private static void populateAreaList(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (i > 0 && i < input.length - 1 && j > 0 && j < input[i].length - 1) {
                    if (input[i][j] < input[i + 1][j] && input[i][j] < input[i - 1][j] &&
                            input[i][j] < input[i][j + 1] && input[i][j] < input[i][j - 1]) {
                        areaList.add(getIslandSum(input, i, j));
                    }
                } else if (i == 0 && j > 0 && j < input[i].length - 1) {
                    if (input[i][j] < input[i + 1][j] &&
                            input[i][j] < input[i][j + 1] && input[i][j] < input[i][j - 1]) {
                        areaList.add(getIslandSum(input, i, j));
                    }
                } else if (i > 0 && i < input.length - 1 && j == 0) {
                    if (input[i][j] < input[i + 1][j] && input[i][j] < input[i - 1][j] &&
                            input[i][j] < input[i][j + 1]) {
                        areaList.add(getIslandSum(input, i, j));
                    }
                } else if (i == input.length - 1 && j > 0 && j < input[i].length - 1) {
                    if (input[i][j] < input[i - 1][j] &&
                            input[i][j] < input[i][j + 1] && input[i][j] < input[i][j - 1]) {
                        areaList.add(getIslandSum(input, i, j));
                    }
                } else if (i > 0 && i < input.length - 1 && j == input[i].length - 1) {
                    if (input[i][j] < input[i + 1][j] && input[i][j] < input[i - 1][j] &&
                            input[i][j] < input[i][j - 1]) {
                        areaList.add(getIslandSum(input, i, j));
                    }
                } else if (i == 0 && j == 0) {
                    if (input[i][j] < input[i + 1][j] && input[i][j] < input[i][j + 1]) {
                        areaList.add(getIslandSum(input, i, j));
                    }
                } else if (i == input.length - 1 && j == input[i].length - 1) {
                    if (input[i][j] < input[i - 1][j] && input[i][j] < input[i][j - 1]) {
                        areaList.add(getIslandSum(input, i, j));
                    }
                } else if (i == 0 && j == input[i].length - 1) {
                    if (input[i][j] < input[i + 1][j] && input[i][j] < input[i][j - 1]) {
                        areaList.add(getIslandSum(input, i, j));
                    }
                } else if (i == input.length - 1 && j == 0) {
                    if (input[i][j] < input[i - 1][j] && input[i][j] < input[i][j + 1]) {
                        areaList.add(getIslandSum(input, i, j));
                    }
                }
            }
        }
    }

    private static int getIslandSum(int[][] input, int i, int j) {
        if (i < 0 || j < 0 || i >= input.length || j >= input[0].length || visited[i][j] || input[i][j] == 9)
            return 0;

        visited[i][j] = true;

        return 1 + getIslandSum(input, i + 1, j) +
                getIslandSum(input, i - 1, j) +
                getIslandSum(input, i, j + 1) +
                getIslandSum(input, i, j - 1);
    }
}
