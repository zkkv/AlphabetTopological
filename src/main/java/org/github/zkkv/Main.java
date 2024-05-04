package org.github.zkkv;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        List<String> names = new ArrayList<>(size);

        for (int i = 0; i < size; i++){
            names.add(scanner.next());
        }

        List<Character> solution = solve(names, size);

        if (solution == null) {
            System.out.println("Impossible");
        } else {
            for (Character c : solution) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static List<Character> solve(List<String> names, int n) {
        Graph graph = new Graph();

        for (int i = 0; i < n - 1; i++) {
            String str1 = names.get(i);
            String str2 = names.get(i + 1);

            int len = Math.min(str1.length(), str2.length());
            for (int j = 0; j < len; j++) {
                char c1 = str1.charAt(j);
                char c2 = str2.charAt(j);

                if (c1 != c2) {
                    graph.nodes.get(c1).add(c2);
                    graph.inDegrees.put(c2, graph.inDegrees.get(c2) + 1);
                    break;
                }
            }
        }

        if (graph.hasCycles()) {
            return null;
        }

        return graph.topologicalSort();
    }

    private static class Graph {
        public Map<Character, Set<Character>> nodes;
        public Map<Character, Integer> inDegrees;

        public Graph() {
            nodes = new HashMap<>(26);
            inDegrees = new HashMap<>(26);

            for (int i = 0; i < 26; i++) {
                char c = (char)('a' + i);
                nodes.put(c, new HashSet<>());
                inDegrees.put(c, 0);
            }
        }

        public List<Character> topologicalSort() {
            List<Character> result = new ArrayList<>();
            Stack<Character> ready = new Stack<>();

            for (char node : nodes.keySet()) {
                if (inDegrees.get(node) == 0) {
                    ready.push(node);
                }
            }

            while (!ready.isEmpty()) {
                char node = ready.pop();
                result.add(node);
                for (char neighbor : nodes.get(node)) {
                    inDegrees.put(neighbor, inDegrees.get(neighbor) - 1);
                    if (inDegrees.get(neighbor) == 0) {
                        ready.push(neighbor);
                    }
                }
            }

            return result;
        }

        public boolean hasCycles() {
            for (char start : nodes.keySet()) {
                Set<Character> visited = new HashSet<>();
                if (hasFoundCycle(start, visited)) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasFoundCycle(char start, Set<Character> visited) {
            if (visited.contains(start)) {
                return true;
            }
            visited.add(start);

            for (Character neighbor : nodes.get(start)) {
                if (hasFoundCycle(neighbor, visited)) {
                    return true;
                }
            }

            visited.remove(start);
            return false;
        }
    }
}