package com.christmas.puzzles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.christmas.utils.FileUtils;

public class Puzzle24 {

    private static int pathCount;

    public static int solve() {
        String[] splitData = FileUtils.readFileToStringArray("src/input/day12.txt");
        Set<String> nodes = new HashSet<>();
        Map<String, List<String>> adjList = new HashMap<>();

        for(int i=0;i<splitData.length;i++) {
            String[] node = splitData[i].split("-");
            List<String> val1;
            val1 = adjList.containsKey(node[0]) ? adjList.get(node[0]) : new ArrayList<>();
            val1.add(node[1]);
            adjList.put(node[0], val1);

            val1 = adjList.containsKey(node[1]) ? adjList.get(node[1]) : new ArrayList<>();
            val1.add(node[0]);
            adjList.put(node[1], val1);

            nodes.add(node[0]);
            nodes.add(node[1]);
        }

        traverseAllPaths(adjList, new HashSet<>(), false, "start");

        return pathCount;
    }

    private static void traverseAllPaths(Map<String, List<String>> adjList, Set<String> visited, boolean visitTwice, String node) {
        if(node.equals("end")) {
            pathCount++;
            return;
        }

        if(node.equals(node.toLowerCase(Locale.ROOT))) {
            visited.add(node);
        }

        for(String adj: adjList.get(node)) {
            if(!visited.contains(adj)) {
                traverseAllPaths(adjList, new HashSet<>(visited), visitTwice, adj);
            } else if(!visitTwice && !adj.equals("start") && !adj.equals("end")) {
                traverseAllPaths(adjList, new HashSet<>(visited), true, adj);
            }
        }
    }
}
