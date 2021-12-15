package com.christmas.puzzles;

import com.christmas.utils.FileUtils;

public class Puzzle29 {

    public static long solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day15.txt");
        int[][] data = new int[splitData.length][splitData[0].length()];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = Integer.parseInt(Character.toString(splitData[i].charAt(j)));
            }
        }

        return findMinPath(data);
    }

    private static int findMinPath(int[][] array) {

        int n = array.length;
        int[][] dist = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        dist[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int xy[] = getMinIndex(dist, visited);

                visited[xy[0]][xy[1]] = true;

                if (xy[0] < n - 1 && !visited[xy[0] + 1][xy[1]] && dist[xy[0]][xy[1]] + array[xy[0] + 1][xy[1]] < dist[xy[0] + 1][xy[1]]) {
                    dist[xy[0] + 1][xy[1]] = dist[xy[0]][xy[1]] + array[xy[0] + 1][xy[1]];
                }

                if (xy[0] > 0 && !visited[xy[0] - 1][xy[1]] && dist[xy[0]][xy[1]] + array[xy[0] - 1][xy[1]] < dist[xy[0] - 1][xy[1]]) {
                    dist[xy[0] - 1][xy[1]] = dist[xy[0]][xy[1]] + array[xy[0] - 1][xy[1]];
                }

                if (xy[1] < n - 1 && !visited[xy[0]][xy[1] + 1] && dist[xy[0]][xy[1]] + array[xy[0]][xy[1] + 1] < dist[xy[0]][xy[1] + 1]) {
                    dist[xy[0]][xy[1] + 1] = dist[xy[0]][xy[1]] + array[xy[0]][xy[1] + 1];
                }

                if (xy[1] > 0 && !visited[xy[0]][xy[1] - 1] && dist[xy[0]][xy[1]] + array[xy[0]][xy[1] - 1] < dist[xy[0]][xy[1] - 1]) {
                    dist[xy[0]][xy[1] - 1] = dist[xy[0]][xy[1]] + array[xy[0]][xy[1] - 1];
                }

            }
        }

        return dist[n - 1][n - 1];
    }

    private static int[] getMinIndex(int[][] dist, boolean visited[][]) {
        int min = Integer.MAX_VALUE;
        int index[] = new int[2];

        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[0].length; j++) {
                if (dist[i][j] <= min && !visited[i][j]) {
                    min = dist[i][j];
                    index[0] = i;
                    index[1] = j;
                }
            }
        }

        return index;
    }


}
