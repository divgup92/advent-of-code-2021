package com.christmas.puzzles;

import java.util.ArrayList;
import java.util.List;

import com.christmas.utils.FileUtils;

public class Puzzle32 {

    private static int curPointer = 0;

    public static long solve() {
        String data = FileUtils.readFileToStringArray("src/input/day16.txt")[0];
        String binaryData = hexToBin(data);

        Packet p = parse(binaryData);
        applyOperations(p);

        return p.val;
    }

    private static Packet parse(String s) {
        Packet packet = new Packet();

        packet.version = Integer.parseInt(s.substring(curPointer, curPointer + 3), 2);
        curPointer += 3;
        packet.typeID = Integer.parseInt(s.substring(curPointer, curPointer + 3), 2);
        curPointer += 3;

        if (packet.typeID == 4) {
            parseLiteral(s, packet);
        } else {
            parseOperator(s, packet);
        }

        return packet;
    }

    private static void parseOperator(String s, Packet operator) {
        operator.isLiteral = false;

        operator.lengthTypeID = Integer.parseInt(Character.toString(s.charAt(curPointer++)));
        operator.subPackets = new ArrayList<>();

        if (operator.lengthTypeID == 0) {
            int lengthOfSubPackets = Integer.parseInt(s.substring(curPointer, curPointer + 15), 2);
            curPointer += 15;
            int end = curPointer + lengthOfSubPackets;
            while (curPointer < end) {
                operator.subPackets.add(parse(s));
            }
            curPointer = end;
        } else {
            int numberOfSubPackets = Integer.parseInt(s.substring(curPointer, curPointer + 11), 2);
            curPointer += 11;
            for (int i = 0; i < numberOfSubPackets; i++) {
                operator.subPackets.add(parse(s));
            }
        }
    }

    private static void parseLiteral(String s, Packet literal) {
        StringBuilder val = new StringBuilder();

        literal.isLiteral = true;

        while (s.charAt(curPointer) == '1') {
            val.append(s.substring(curPointer + 1, curPointer + 5));
            curPointer += 5;
        }

        val.append(s.substring(curPointer + 1, curPointer + 5));
        curPointer += 5;

        literal.val = Long.parseLong(val.toString(), 2);
    }

    private static void applyOperations(Packet p) {
        if (p.subPackets == null)
            return;

        for (Packet c : p.subPackets) {
            applyOperations(c);
        }

        switch (p.typeID) {
            case 0:
                p.val = 0;
                for (Packet c : p.subPackets) {
                    p.val += c.val;
                }
                break;
            case 1:
                p.val = 1;
                for (Packet c : p.subPackets) {
                    p.val *= c.val;
                }
                break;
            case 2:
                p.val = Long.MAX_VALUE;
                for (Packet c : p.subPackets) {
                    p.val = Math.min(p.val, c.val);
                }
                break;
            case 3:
                p.val = Long.MIN_VALUE;
                for (Packet c : p.subPackets) {
                    p.val = Math.max(p.val, c.val);
                }
                break;
            case 5:
                p.val = p.subPackets.get(0).val > p.subPackets.get(1).val ? 1 : 0;
                break;
            case 6:
                p.val = p.subPackets.get(0).val < p.subPackets.get(1).val ? 1 : 0;
                break;
            case 7:
                p.val = p.subPackets.get(0).val == p.subPackets.get(1).val ? 1 : 0;
                break;
        }
    }

    private static String hexToBin(String hex) {
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }

    static class Packet {
        int version;
        int typeID;
        boolean isLiteral;
        long val;

        int lengthTypeID;
        List<Packet> subPackets;

    }
}
