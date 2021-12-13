package com.christmas.puzzles;

import java.util.Arrays;

import com.christmas.utils.FileUtils;

public class Puzzle26 {

    private static char HASH = '#';
    private static char DOT = '.';
    private static int PAPER_DIMENSION = 1500;

    public static String solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day13.txt");
        char[][] paper = new char[PAPER_DIMENSION][PAPER_DIMENSION];
        int instIndex = 0;

        for (int i = 0; i < PAPER_DIMENSION; i++) {
            for (int j = 0; j < PAPER_DIMENSION; j++) {
                paper[i][j] = DOT;
            }
        }

        for (int i = 0; i < splitData.length; i++) {
            if (splitData[i].isEmpty()) {
                instIndex = i + 1;
                break;
            }

            int[] xy = Arrays.stream(splitData[i].split(",")).mapToInt(Integer::parseInt).toArray();

            paper[xy[1]][xy[0]] = HASH;
        }

        for (int i = instIndex; i < splitData.length; i++) {
            paper = splitData[i].startsWith("fold along y=") ?
                    fold(paper, -1, Integer.parseInt(splitData[i].substring(13))) :
                    fold(paper, Integer.parseInt(splitData[i].substring(13)), -1);
        }

        return printableResult(paper);
    }

    private static char[][] fold(char[][] paper, int x, int y) {
        char[][] newPaper = null;
        if (x > -1) {
            int xDim = Math.max(x, paper[0].length - x);
            newPaper = new char[paper.length][xDim];
            for (int i = 1; i <= xDim; i++) {
                for (int j = 0; j < paper.length; j++) {
                    if (x - i >= 0 && x + i < paper[0].length) {
                        newPaper[j][i - 1] = paper[j][x + i] == HASH || paper[j][x - i] == HASH ? HASH : DOT;
                    } else if (x - i >= 0) {
                        newPaper[j][i - 1] = paper[j][x - i] == HASH ? HASH : DOT;
                    } else if (x + i < paper[0].length) {
                        newPaper[j][i - 1] = paper[j][x + i] == HASH ? HASH : DOT;
                    } else {
                        break;
                    }
                }
            }
        } else if (y > -1) {
            int yDim = Math.max(y, paper.length - y);
            newPaper = new char[yDim][paper[0].length];
            for (int i = 1; i < yDim; i++) {
                for (int j = 0; j < paper[0].length; j++) {
                    if (y - i >= 0 && y + i < paper.length) {
                        newPaper[i - 1][j] = paper[y + i][j] == HASH || paper[y - i][j] == HASH ? HASH : DOT;
                    } else if (y - i >= 0) {
                        newPaper[i - 1][j] = paper[y - i][j] == HASH ? HASH : DOT;
                    } else if (y + i < paper.length) {
                        newPaper[i - 1][j] = paper[y + i][j] == HASH ? HASH : DOT;
                    } else {
                        break;
                    }
                }
            }
        }

        return newPaper;
    }

    private static String printableResult(char[][] paper) {
        StringBuilder result = new StringBuilder();

        for (int i = paper.length - 1; i >= 0; i--) {
            StringBuilder print = new StringBuilder();
            for (int j = paper[0].length - 1; j >= 0; j--) {
                print.append(paper[i][j] + " ");
            }
            if (print.indexOf("#") > 0) {
                result.append(print.substring(print.indexOf("#"))).append("\n");
            }
        }

        return result.toString();
    }
}
