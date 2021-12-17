package com.christmas.puzzles;

public class Puzzle34 {

    public static long solve() {
        int[] targetx = {32, 65};
        int[] targety = {-225, -177};
        int count = 0;

        for (int i = -500; i < 500; i++) {
            for (int j = -500; j < 500; j++) {
                if (hitsTarget(new int[]{i, j}, targetx, targety)) {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean hitsTarget(int velocity[], int[] targetx, int[] targety) {
        int[] pos = {0, 0};
        while (pos[0] <= targetx[1] && pos[1] >= targety[0]) {
            executeStep(velocity, pos);
            if (pos[0] >= targetx[0] && pos[0] <= targetx[1] &&
                    pos[1] >= targety[0] && pos[1] <= targety[1]) {
                return true;
            }
        }
        return false;
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
