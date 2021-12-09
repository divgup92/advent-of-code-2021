package com.christmas.puzzles;

import java.util.ArrayList;
import java.util.List;

import com.christmas.utils.FileUtils;

public class Puzzle17 {
    static List<Integer> minList = new ArrayList<>();

    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day9.txt");
        int[][] input = new int[splitData.length][splitData[0].length()];
        int sum = 0;

        for (int i = 0; i < splitData.length; i++) {
            for (int j = 0; j < splitData[i].length(); j++) {
                input[i][j] = Integer.parseInt(Character.toString(splitData[i].charAt(j)));
            }
        }

        populateMin(input);

        for(Integer i: minList) {
            sum += i+1;
        }
        return sum;
    }

    private static void populateMin(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (i > 0 && i < input.length - 1 && j > 0 && j < input[i].length - 1) {
                    if (input[i][j] < input[i + 1][j] && input[i][j] < input[i - 1][j] &&
                            input[i][j] < input[i][j + 1] && input[i][j] < input[i][j - 1]) {
                        minList.add(input[i][j]);
                    }
                } else if(i == 0 && j > 0 && j < input[i].length - 1) {
                    if (input[i][j] < input[i + 1][j] &&
                            input[i][j] < input[i][j + 1] && input[i][j] < input[i][j - 1]) {
                        minList.add(input[i][j]);
                    }
                } else if( i > 0 && i < input.length - 1 && j == 0) {
                    if (input[i][j] < input[i + 1][j] && input[i][j] < input[i - 1][j] &&
                            input[i][j] < input[i][j + 1]) {
                        minList.add(input[i][j]);
                    }
                } else if(i == input.length-1 && j > 0 && j < input[i].length - 1) {
                    if (input[i][j] < input[i - 1][j] &&
                            input[i][j] < input[i][j + 1] && input[i][j] < input[i][j - 1]) {
                        minList.add(input[i][j]);
                    }
                } else if( i > 0 && i < input.length - 1 && j == input[i].length-1) {
                    if (input[i][j] < input[i + 1][j] && input[i][j] < input[i - 1][j] &&
                            input[i][j] < input[i][j - 1]) {
                        minList.add(input[i][j]);
                    }
                } else if( i == 0 && j == 0) {
                    if (input[i][j] < input[i + 1][j] && input[i][j] < input[i][j + 1]) {
                        minList.add(input[i][j]);
                    }
                } else if( i == input.length-1 && j == input[i].length-1) {
                    if (input[i][j] < input[i - 1][j] && input[i][j] < input[i][j - 1]) {
                        minList.add(input[i][j]);
                    }
                } else if( i == 0 && j == input[i].length-1) {
                    if (input[i][j] < input[i + 1][j] && input[i][j] < input[i][j - 1]) {
                        minList.add(input[i][j]);
                    }
                } else if( i == input.length-1 && j == 0) {
                    if (input[i][j] < input[i - 1][j] && input[i][j] < input[i][j + 1]) {
                        minList.add(input[i][j]);
                    }
                }
            }
        }
    }
}


