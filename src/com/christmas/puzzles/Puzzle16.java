package com.christmas.puzzles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.christmas.utils.FileUtils;

public class Puzzle16 {
    public static long solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day8.txt");
        long sum = 0l;

        for (int i = 0; i < splitData.length; i++) {
            String input = splitData[i].substring(0, splitData[i].indexOf(" | "));
            String[] digits = input.split(" ");
            Arrays.sort(digits, (a, b) -> Integer.compare(a.length(), b.length()));

            Map<String, Integer> map = new HashMap<>();
            String num = "";
            String one = "", four = "", seven = "", fourMinusOne = "";

            for (int j = 0; j < digits.length; j++) {
                digits[j] = sortString(digits[j]);
                switch (digits[j].length()) {
                    case 2:
                        map.put(digits[j], 1);
                        one = digits[j];
                        break;
                    case 3:
                        map.put(digits[j], 7);
                        seven = digits[j];
                        break;
                    case 4:
                        map.put(digits[j], 4);
                        four = digits[j];
                        break;
                    case 7:
                        map.put(digits[j], 8);
                        break;
                    case 5:
                        for (int k = 0; k < four.length(); k++) {
                            if (one.indexOf(four.charAt(k)) < 0)
                                fourMinusOne += four.charAt(k);
                        }
                        if (containsAllCharacters(digits[j], fourMinusOne))
                            map.put(digits[j], 5);
                        else if (containsAllCharacters(digits[j], seven)) {
                            map.put(digits[j], 3);
                        } else {
                            map.put(digits[j], 2);
                        }
                        break;
                    case 6:
                        if (containsAllCharacters(digits[j], one) &&
                                containsAllCharacters(digits[j], four) &&
                                containsAllCharacters(digits[j], seven))
                            map.put(digits[j], 9);
                        else if (containsAllCharacters(digits[j], one) &&
                                containsAllCharacters(digits[j], seven))
                            map.put(digits[j], 0);
                        else
                            map.put(digits[j], 6);
                        break;
                }
            }

            String output = splitData[i].substring(splitData[i].indexOf(" | ") + 3);
            digits = output.split(" ");

            for (int k = 0; k < digits.length; k++) {
                num += map.get(sortString(digits[k]));
            }

            sum += Long.parseLong(num);
        }

        return sum;
    }

    private static String sortString(String inputString) {
        char charArray[] = inputString.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    private static boolean containsAllCharacters(String str, String sub) {
        for (int i = 0; i < sub.length(); i++) {
            if (str.indexOf(sub.charAt(i)) < 0)
                return false;
        }

        return true;
    }

}


