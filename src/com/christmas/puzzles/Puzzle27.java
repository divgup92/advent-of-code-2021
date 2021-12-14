package com.christmas.puzzles;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.christmas.utils.FileUtils;

public class Puzzle27 {

    private static int ROUNDS = 10;

    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day14.txt");
        String input = splitData[0];
        Map<String, Character> map = new HashMap<>();
        Map<Character, Integer> charMap = new HashMap<>();

        for (int i = 2; i < splitData.length; i++) {
            String[] pair = splitData[i].split(" -> ");
            map.put(pair[0], pair[1].charAt(0));
        }

        for (int i = 0; i < ROUNDS; i++) {
            input = applyTransitions(input, map);
        }

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int val = 0;

            if (charMap.containsKey(c)) {
                val = charMap.get(c);
            }

            charMap.put(c, val + 1);
        }

        return Collections.max(charMap.values()) - Collections.min(charMap.values());
    }

    private static String applyTransitions(String input, Map<String, Character> map) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length() - 1; i++) {
            String sub = input.substring(i, i + 2);
            if (i == 0) {
                sb.append(input.charAt(0));
            }
            if (map.containsKey(sub)) {
                sb.append(map.get(sub));
            }
            sb.append(sub.charAt(1));
        }

        return sb.toString();
    }
}
