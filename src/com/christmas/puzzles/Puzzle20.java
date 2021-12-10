package com.christmas.puzzles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.christmas.utils.FileUtils;

public class Puzzle20 {

    public static long solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day10.txt");
        List<Long> scores = new ArrayList<>();

        for(int i=0;i<splitData.length;i++) {
            long score = getScore(splitData[i]);
            if(score != 0)
                scores.add(score);
        }

        scores.sort(Long::compareTo);

        return scores.get(scores.size()/2);
    }

    private static long getScore(String s) {
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
                    return 0;
                }
            } else if(map.keySet().contains(c) && stack.empty()) {
                return 0;
            } else {
                stack.push(c);
            }
        }

        if(!stack.empty()) {
            return calculateScore(stack);
        }

        return 0;

    }

    private static long calculateScore(Stack<Character> stack) {
        long score = 0;
        while(!stack.empty()) {
            char c = stack.pop();
            score *= 5;
            switch (c) {
                case '(':
                    score += 1;
                    break;
                case '[':
                    score += 2;
                    break;
                case '{':
                    score += 3;
                    break;
                case '<':
                    score += 4;
                    break;
            }
        }
        return score;
    }
}
