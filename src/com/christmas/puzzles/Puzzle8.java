package com.christmas.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.christmas.utils.FileUtils;

public class Puzzle8 {
    public static int solve() {
        String[] splitData = Arrays.stream(FileUtils.readFileToStringArray("src/input/day4.txt"))
                .map(str -> str.replace("  ", " "))
                .filter(str -> !str.equals(""))
                .toArray(String[]::new);
        int[] input = Arrays.stream(splitData[0].split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        List<int[][]> boards = new ArrayList<>();
        List<boolean[][]> visited = new ArrayList<>();
        int[][] curboard = new int[5][5];
        int j = 0;

        int lastInput = 0;
        int lastboard[][] = null;
        boolean lastvisited[][] = null;
        List<Integer> indices = new ArrayList<>();

        for (int i = 1; i < splitData.length; i++) {
            curboard[j++] = Arrays.stream(splitData[i].split(" ")).filter(str -> !str.equals("")).mapToInt(Integer::parseInt).toArray();

            if (j == 5) {
                boards.add(curboard);
                visited.add(new boolean[5][5]);
                curboard = new int[5][5];
                j = 0;
            }
        }

        for (int i = 0; i < input.length && boards.size()>0; i++) {
            boolean check = false;
            for (int k = 0; k < boards.size(); k++) {
                markBoard(boards.get(k), visited.get(k), input[i]);
                if (checkBingo(boards.get(k), visited.get(k))) {
                    check = true;
                    lastInput = input[i];
                    lastboard = boards.get(k);
                    lastvisited = visited.get(k);
                    indices.add(k);
                }
            }
            if(check) {
                for(int k=indices.size()-1;k>=0;k--) {
                    boards.remove((int) indices.get(k));
                    visited.remove((int) indices.get(k));
                }

            }
            indices.clear();

        }

        return calculateScore(lastboard, lastvisited, lastInput);
    }

    private static void markBoard(int[][] board, boolean[][] visited, int val) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == val) {
                    visited[i][j] = true;
                }
            }
        }
    }

    private static boolean checkBingo(int[][] board, boolean[][] visited) {
        for (int i = 0; i < 5; i++) {
            boolean check = true;
            for (int j = 0; j < 5; j++) {
                if (visited[i][j] == false) {
                    check = false;
                }
            }
            if (check)
                return check;

            check = true;
            for (int j = 0; j < 5; j++) {
                if (visited[j][i] == false) {
                    check = false;
                }
            }
            if (check)
                return check;
        }

        return false;
    }

    private static int calculateScore(int[][] board, boolean[][] visited, int val) {
        int sumUnvisited = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j]) {
                    sumUnvisited += board[i][j];
                }
            }
        }

        return sumUnvisited * val;
    }


}
