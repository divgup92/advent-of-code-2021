package com.christmas.puzzles;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.christmas.utils.FileUtils;

public class Puzzle28 {

    private static int ROUNDS = 40;

    public static long solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day14.txt");
        String start = splitData[0].charAt(0)+"";
        String end = splitData[0].charAt(splitData[0].length() - 1)+"";
        Map<String, Long> input = createMapFromString(splitData[0]);
        Map<String, Character> transitions = new HashMap<>();
        Map<String, Long> charMap = new HashMap<>();

        for (int i = 2; i < splitData.length; i++) {
            String[] pair = splitData[i].split(" -> ");
            transitions.put(pair[0], pair[1].charAt(0));
        }

        for (int i = 0; i < ROUNDS; i++) {
            input = applyTransitions(input, transitions);
        }

        for (String pair : input.keySet()) {
            char c1 = pair.charAt(0);
            char c2 = pair.charAt(1);

            addValueToMap(charMap, Character.toString(c1), input.get(pair));
            addValueToMap(charMap, Character.toString(c2), input.get(pair));
        }

        for (String c : charMap.keySet()) {
            charMap.put(c, charMap.get(c) / 2l);
        }

        if (!start.equals(end)) {
            charMap.put(start, charMap.get(start) + 1);
            charMap.put(end, charMap.get(end) + 1);
        } else {
            charMap.put(start, charMap.get(start) + 1);
        }

        return Collections.max(charMap.values()) - Collections.min(charMap.values());
    }

    private static Map<String, Long> createMapFromString(String input) {
        Map<String, Long> result = new HashMap<>();

        for (int i = 0; i < input.length() - 1; i++) {
            String sub = input.substring(i, i + 2);
            addValueToMap(result, sub, 1l);
        }

        return result;
    }

    private static Map<String, Long> applyTransitions(Map<String, Long> input, Map<String, Character> map) {
        Map<String, Long> result = new HashMap<>();

        for (String pair : input.keySet()) {
            long freq = input.get(pair);
            if (map.containsKey(pair)) {
                String key1 = "" + pair.charAt(0) + map.get(pair);
                String key2 = "" + map.get(pair) + pair.charAt(1);
                addValueToMap(result, key1, freq);
                addValueToMap(result, key2, freq);
            } else {
                result.put(pair, freq);
            }
        }

        return result;
    }

    private static void addValueToMap(Map<String, Long> map, String key, Long val) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + val);
        } else {
            map.put(key, val);
        }
    }
}
