package com.christmas.puzzles;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.christmas.utils.FileUtils;

public class Puzzle19 {

    private static int score = 0;

    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day10.txt");

        for(int i=0;i<splitData.length;i++) {
            checkBrackets(splitData[i]);
        }

        return score;
    }

    private static void checkBrackets(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = Map.of('}', '{',
                ']', '[',
                ')', '(',
                '>', '<');
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(map.keySet().contains(c) && !stack.empty()) {
                if(stack.peek().equals(map.get(c))) {
                    stack.pop();
                } else {
                    populateScore(c);
                    return;
                }
            } else if(map.keySet().contains(c) && stack.empty()) {
                return;
            } else {
                stack.push(c);
            }
        }

    }

    private static void populateScore(char c) {
        switch (c) {
            case ')':
                score += 3;
                break;
            case ']':
                score += 57;
                break;
            case '}':
                score += 1197;
                break;
            case '>':
                score += 25137;
                break;
        }
    }

}
