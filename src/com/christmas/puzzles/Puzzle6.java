package com.christmas.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.christmas.utils.FileUtils;

public class Puzzle6 {

    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day3.txt");
        List<String> o2 = Arrays.asList(splitData);
        List<String> co2 = Arrays.asList(splitData);
        int pos = 0;

        while(o2.size() > 1 && co2.size() > 1) {
            char maxo2 = countMeasure(o2, pos) < 0 ? '0' : '1';
            char minco2 = countMeasure(co2, pos) < 0 ? '1' : '0';

            o2 = eliminateForO2(o2, maxo2, pos);
            co2 = eliminateForCO2(co2, minco2, pos);

            pos++;
        }

        while(o2.size() > 1) {
            char maxo2 = countMeasure(o2, pos) < 0 ? '0' : '1';
            o2 = eliminateForO2(o2, maxo2, pos);
            pos++;
        }

        while(co2.size() > 1) {
            char minco2 = countMeasure(co2, pos) > 0 ? '1' : '0';
            co2 = eliminateForCO2(co2, minco2, pos);
            pos++;
        }

        return Integer.parseInt(o2.get(0), 2) * Integer.parseInt(co2.get(0), 2);
    }

    private static List<String> eliminateForO2(List<String> o2, char max, int pos) {
        List<String> dupO2 = new ArrayList<>(o2);

        for(String str: o2) {
            if(str.charAt(pos) != max)
                dupO2.remove(str);
        }

        return dupO2;
    }

    private static List<String> eliminateForCO2(List<String> co2, char min, int pos) {
        List<String> dupCO2 = new ArrayList<>(co2);

        for(String str: co2) {
            if(str.charAt(pos) != min)
                dupCO2.remove(str);
        }

        return dupCO2;
    }

    private static int countMeasure (List<String> list, int pos) {
        int res = 0;
        for(String str: list) {
            if(str.charAt(pos) == '0')
                res--;
            else
                res++;
        }
        return res;
    }
}
