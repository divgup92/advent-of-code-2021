package com.christmas.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.christmas.utils.FileUtils;

public class Puzzle38 {
    public static long solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day19.txt");
        List<List<Coordinate>> scanners = readData(splitData);
        boolean transformed[] = new boolean[scanners.size()];
        Set<Coordinate> scannerCoordinates = new HashSet<>();

        transformed[0] = true;

        int count = 1;
        while (count < scanners.size()) {
            for (int i = 0; i < scanners.size(); i++) {
                if (!transformed[i])
                    continue;
                for (int j = 0; j < scanners.size(); j++) {
                    Map<List<Coordinate>, Integer> freq = new HashMap<>();
                    if (i == j || transformed[j])
                        continue;

                    Map<Double, List<Coordinate>> disti = getDistanceSet(scanners.get(i));
                    Map<Double, List<Coordinate>> distj = getDistanceSet(scanners.get(j));

                    Set<Double> test = disti.keySet();
                    test.retainAll(distj.keySet());

                    if (test.size() >= 12) {
                        for (Double key : test) {
                            for (Coordinate ci : disti.get(key)) {
                                for (Coordinate cj : distj.get(key)) {
                                    int value = 0;
                                    List<Coordinate> fKey = List.of(ci, cj);
                                    if (freq.containsKey(fKey))
                                        value = freq.get(fKey);
                                    freq.put(fKey, value + 1);
                                }
                            }
                        }


                        freq.entrySet().removeIf(entry -> entry.getValue() < 10);

                        int[][] matrix = new int[3][3];
                        boolean[][] sign = new boolean[3][3];

                        if (freq.size() >= 12) {
                            populateMatrix(matrix, sign, freq);
                            scannerCoordinates.add(getScannerCoordinates(matrix));
                            transform(scanners.get(j), matrix, sign);
                            transformed[j] = true;
                            count++;

                        }
                    }
                }

            }
        }

        return getLargestDistance(scannerCoordinates);
    }

    private static Coordinate getScannerCoordinates(int[][] matrix) {
        Coordinate c = new Coordinate();
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix.length;j++) {
                c.array[i] += matrix[i][j];
            }
        }
        return c;
    }

    private static int getLargestDistance(Set<Coordinate> coordinates) {
        int max = Integer.MIN_VALUE;

        for(Coordinate c1: coordinates) {
            for(Coordinate c2: coordinates) {
                max = Math.max(getDistance(c1,c2), max);
            }
        }
        return max;
    }

    private static int getDistance(Coordinate c1, Coordinate c2) {
        int dist = 0;
        for(int i=0;i<3;i++) {
            dist += Math.abs(c1.array[i]-c2.array[i]);
        }
        return dist;
    }

    private static void transform(List<Coordinate> coordinates, int[][] matrix, boolean[][] sign) {

        for (Coordinate c : coordinates) {
            int[] copy = Arrays.copyOf(c.array, 3);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matrix[i][j] != 0) {
                        if (sign[i][j])
                            c.array[i] = matrix[i][j] - copy[j];
                        else
                            c.array[i] = matrix[i][j] + copy[j];
                    }
                }
            }
        }

    }

    private static void populateMatrix(int[][] matrix, boolean[][] sign, Map<List<Coordinate>, Integer> freq) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int val = -1;
                boolean isFirst = true, mismatch = false;
                for (List<Coordinate> c : freq.keySet()) {
                    int diff = c.get(0).array[i] + c.get(1).array[j];
                    if(isFirst) {
                        val = diff;
                        isFirst = false;
                    } else if(val != diff) {
                        mismatch = true;
                        break;
                    }
                }
                if (!mismatch) {
                    matrix[i][j] = val;
                    sign[i][j] = true;
                } else {
                    isFirst = true;
                    mismatch = false;
                    for (List<Coordinate> c : freq.keySet()) {

                        int diff = c.get(0).array[i] - c.get(1).array[j];
                        if(isFirst) {
                            val = diff;
                            isFirst = false;
                        } else if(val != diff) {
                            mismatch = true;
                            break;
                        }
                    }
                    if (!mismatch) {
                        matrix[i][j] = val;
                    }
                }
            }
        }
    }

    private static Map<Double, List<Coordinate>> getDistanceSet(List<Coordinate> scanner) {
        Map<Double, List<Coordinate>> distances = new HashMap<>();
        for (int j = 0; j < scanner.size(); j++) {
            for (int k = j + 1; k < scanner.size(); k++) {

                double dist = square(scanner.get(j).array[0] - scanner.get(k).array[0]) +
                        square(scanner.get(j).array[1] - scanner.get(k).array[1]) +
                        square(scanner.get(j).array[2] - scanner.get(k).array[2]);

                distances.put(dist, List.of(scanner.get(j), scanner.get(k)));
            }
        }
        return distances;
    }

    private static List<List<Coordinate>> readData(String[] splitData) {
        List<List<Coordinate>> scanners = new ArrayList<>();
        List<Coordinate> scanner = null;

        for (int i = 0; i < splitData.length; i++) {
            if (splitData[i].startsWith("--- scanner")) {
                if (scanner != null) {
                    scanners.add(scanner);
                }
                scanner = new ArrayList<>();
            } else if (splitData[i].equals("")) {
                continue;
            } else {
                String[] cStr = splitData[i].split(",");
                Coordinate c = new Coordinate();
                c.array[0] = Integer.parseInt(cStr[0]);
                c.array[1] = Integer.parseInt(cStr[1]);
                c.array[2] = Integer.parseInt(cStr[2]);
                scanner.add(c);
            }
        }

        scanners.add(scanner);

        return scanners;
    }

    private static double square(double a) {
        return a * a;
    }

    static class Coordinate {
        int array[] = new int[3];


        @Override
        public String toString() {
            return "(" + array[0] + ", " + array[1] + ", " + array[2] + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return array[0] == that.array[0] && array[1] == that.array[1] && array[2] == that.array[2];
        }

        @Override
        public int hashCode() {
            return Objects.hash(array[0], array[1], array[2]);
        }
    }
}
