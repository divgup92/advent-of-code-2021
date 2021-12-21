package com.christmas.puzzles;

import com.christmas.utils.FileUtils;

public class Puzzle40 {
    private static int PADDING = 60;

    public static long solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day20.txt");
        String imageEnhancementAlgo = splitData[0];
        char image[][] = new char[splitData.length - 2 + (2 * PADDING)][splitData[2].length() + (2 * PADDING)];
        int numRows = splitData.length - 2;
        int numCols = splitData[2].length();

        initializeImage(image, '.');

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                image[i + PADDING][j + PADDING] = splitData[i + 2].charAt(j);
            }
        }

        for(int i=1;i<=50;i++) {
            convertImageToZeroOne(image);
            image = applyAlgo(image, imageEnhancementAlgo, i);
        }

        return countLitPixels(image);
    }

    private static void initializeImage(char image[][], char c) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                image[i][j] = c;
            }
        }
    }

    private static char[][] applyAlgo(char[][] image, String algo, int itr) {
        char newImage[][] = new char[image.length][image[0].length];

        initializeImage(newImage, (itr % 2 == 1) ? '#' : '.');

        for (int i = 1; i < image.length - 1; i++) {
            for (int j = 1; j < image[0].length - 1; j++) {
                int pos = getBinaryForPixel(image, i, j);
                newImage[i][j] = algo.charAt(pos);
            }
        }

        return newImage;
    }

    private static void convertImageToZeroOne(char[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                image[i][j] = image[i][j] == '.' ? '0' : '1';
            }
        }
    }

    private static int countLitPixels(char[][] image) {
        int count = 0;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                count += image[i][j] == '#' ? 1 : 0;
            }
        }
        return count;
    }

    private static int getBinaryForPixel(char[][] image, int x, int y) {
        String matrix = "";
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                matrix += image[i][j];
            }
        }
        return Integer.parseInt(matrix, 2);
    }
}
