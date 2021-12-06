package com.christmas.puzzles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.christmas.utils.FileUtils;

public class Puzzle12 {
    public static long solve() {
        String[] data = FileUtils.readFileToStringArray("src/input/day6.txt")[0].split(",");
        Map<Integer, Long> dataMap = new HashMap<>();

        for(int i=0;i<=8;i++) {
            dataMap.put(i,0l);
        }

        Arrays.stream(data)
                .map(str -> Integer.parseInt(str))
                .forEach(point -> {
                    Long value = dataMap.get(point);
                    dataMap.put(point, value + 1);
                });

        for(int i=0;i<256;i++) {

            long data0 = dataMap.get(Integer.valueOf(0));
            for(int j=1;j<=8;j++) {
                dataMap.put(j-1, dataMap.get(j));
            }
            dataMap.put(8, data0);
            dataMap.put(6, dataMap.get(6)+data0);
        }

        long sum = 0l;

        for(Long val: dataMap.values())
            sum += val;

        return sum;
    }
}


