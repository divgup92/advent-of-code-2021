package com.christmas.puzzles;

public class Puzzle33 {

    public static long solve() {
        int[] targetx = {32, 65};
        int[] targety = {-225, -177};

        int max = 0;

        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                max = Math.max(max, findMaxY(new int[]{i, j}, targetx, targety));
            }
        }

        return max;
    }

    private static int findMaxY(int velocity[], int[] targetx, int[] targety) {
        int[] pos = {0, 0};
        int maxY = 0;
        while (pos[0] <= targetx[1] && pos[1] >= targety[1]) {
            executeStep(velocity, pos);
            maxY = Math.max(maxY, pos[1]);
            if (pos[0] >= targetx[0] && pos[0] <= targetx[1] &&
                    pos[1] >= targety[0] && pos[1] <= targety[1]) {
                return maxY;
            }
        }
        return Integer.MIN_VALUE;
    }

    private static void executeStep(int[] velocity, int[] xy) {
        xy[0] += velocity[0];
        xy[1] += velocity[1];

        if (velocity[0] < 0) {
            velocity[0]++;
        } else if (velocity[0] > 0) {
            velocity[0]--;
        }

        velocity[1]--;
    }
}
