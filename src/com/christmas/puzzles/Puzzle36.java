package com.christmas.puzzles;

import java.util.Stack;

import com.christmas.utils.FileUtils;

public class Puzzle36 {

    private static boolean leftFlag = false;
    private static boolean rightFlag = false;

    public static long solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day18.txt");
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < splitData.length; i++) {
            for (int j = 0; j < splitData.length; j++) {
                if (i == j)
                    continue;
                Pair sum = add(readPair(splitData[i]), readPair(splitData[j]));
                explodeOrSplit(sum);
                computeMagnitude(sum);
                max = Math.max(max, sum.magnitude);
            }
        }

        return max;
    }

    private static Pair readPair(String s) {
        Stack<Pair> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                Pair p = new Pair();
                p.magnitude = Integer.parseInt(c + "");
                p.isNumber = true;
                stack.push(p);
            } else if (c == ']') {
                Pair p = new Pair();
                p.right = stack.pop();
                p.left = stack.pop();
                p.isNumber = false;
                p.magnitude = -1;
                stack.push(p);
            }
        }

        return stack.pop();
    }

    private static Pair add(Pair p1, Pair p2) {
        Pair sum = new Pair();

        sum.left = p1;
        sum.right = p2;
        sum.isNumber = false;

        return sum;
    }

    private static void explodeOrSplit(Pair p) {
        Pair target = firstPairToExplode(p, 0);

        if (target == null) {
            target = firstPairToSplit(p);
            if (target == null)
                return;
        }

        if (target.isNumber) {
            target.left = new Pair();
            target.left.magnitude = (int) Math.floor(target.magnitude / 2.0);
            target.left.isNumber = true;
            target.right = new Pair();
            target.right.magnitude = (int) Math.ceil(target.magnitude / 2.0);
            target.right.isNumber = true;
            target.isNumber = false;
            target.magnitude = -1;
        } else {
            leftFlag = false;
            rightFlag = false;
            updateLeft(p, target);
            updateRight(p, target);
            target.isNumber = true;
            target.left = null;
            target.right = null;
            target.magnitude = 0;
        }

        explodeOrSplit(p);
    }

    private static void updateLeft(Pair cur, Pair target) {
        if (cur == null)
            return;

        updateLeft(cur.right, target);

        if (!cur.equals(target.left) && !cur.equals(target.right) && leftFlag && cur.isNumber) {
            cur.magnitude += target.left.magnitude;
            leftFlag = false;
            return;
        }
        if (cur.equals(target.left)) {
            leftFlag = true;
        }

        updateLeft(cur.left, target);
    }

    private static void updateRight(Pair cur, Pair target) {

        if (cur == null)
            return;

        updateRight(cur.left, target);

        if (!cur.equals(target.left) && !cur.equals(target.right) && rightFlag && cur.isNumber) {
            cur.magnitude += target.right.magnitude;
            rightFlag = false;
            return;
        }
        if (cur.equals(target)) {
            rightFlag = true;
        }

        updateRight(cur.right, target);
    }

    private static Pair firstPairToExplode(Pair p, int height) {
        if (p == null)
            return null;

        if (height >= 4 && !p.isNumber)
            return p;

        Pair result = firstPairToExplode(p.left, height + 1);
        if (result == null)
            result = firstPairToExplode(p.right, height + 1);

        return result;
    }

    private static Pair firstPairToSplit(Pair p) {
        if (p == null)
            return null;

        if (p.isNumber && p.magnitude >= 10)
            return p;

        Pair result = firstPairToSplit(p.left);
        if (result == null)
            result = firstPairToSplit(p.right);

        return result;
    }

    private static void computeMagnitude(Pair p) {
        if (p == null || p.isNumber)
            return;

        computeMagnitude(p.left);
        computeMagnitude(p.right);

        p.magnitude = 0;
        if (p.left != null)
            p.magnitude += 3 * p.left.magnitude;
        if (p.right != null)
            p.magnitude += 2 * p.right.magnitude;
    }

    static class Pair {
        int magnitude;
        Pair left;
        Pair right;
        boolean isNumber;

        @Override
        public String toString() {
            if (isNumber)
                return Integer.toString(magnitude);
            return "[" + left + ',' + right + ']';
        }

    }
}
