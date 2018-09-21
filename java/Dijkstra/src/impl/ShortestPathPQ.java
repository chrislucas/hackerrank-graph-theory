package impl;

import java.util.*;

/**
 * Usando um Set para armazenar as arestas do grafo
 * */

public class ShortestPathPQ {
    private static class Edge implements Comparable<Edge> {
        int u, v, w;
        private Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge that) {
            return this.w - that.w;
        }

        @Override
        public String toString() {
            return String.format("U: %d V: %d W: %d", u, v, w);
        }

        @Override
        public boolean equals(Object obj) {
            Edge that = (Edge) obj;
            return w == that.w && u == that.u && v == that.v;
        }

        @Override
        public int hashCode() {
            return (w + u + v) * 31;
        }
    }

    private static class MinHeap implements Comparator<Edge> {
        @Override
        public int compare(Edge p, Edge q) {
            return p.compareTo(q);
        }
    }

    private static LinkedHashMap<Integer, LinkedHashSet<Edge>> graph;
    private static final int INF = Integer.MAX_VALUE;
    private static int [] distances;
    private static boolean [] extracted;

    private static void init(int vertices) {
        graph = new LinkedHashMap<>();
        distances = new int[vertices];
        extracted = new boolean[vertices];
        for (int i = 0; i < vertices ; i++) {
            distances[i] = INF;
        }
    }

    private static void add(int u, int v, int w) {
        if (!graph.containsKey(u)) {
            graph.put(u, new LinkedHashSet<>());
        }
        graph.get(u).add(new Edge(u, v, w));
        if (!graph.containsKey(v)) {
            graph.put(v, new LinkedHashSet<>());
        }
        graph.get(v).add(new Edge(v, u, w));
    }

    private static void run(int vertices) {
        init(vertices);
        add(0, 1, 4);
        add(0, 7, 8);
        add(1, 0, 4);
        add(1, 2, 8);
        add(1, 7, 11);
        add(2, 1, 8);
        add(2, 3, 7);
        add(2, 5, 4);
        add(2, 8, 2);
        add(3, 2, 7);
        add(3, 4, 9);
        add(3, 5, 14);
        add(4, 3, 9);
        add(4, 5, 10);
        add(5, 2, 4);
        add(5, 3, 14);
        add(5, 4, 10);
        add(5, 6, 2);
        add(6, 5, 2);
        add(6, 7, 1);
        add(6, 8, 6);
        add(7, 0, 8);
        add(7, 1, 11);
        add(7, 6, 1);
        add(7, 8, 7);
        add(8, 2, 2);
        add(8, 6, 6);
        add(8, 7, 7);
        apsp(0);
        for (int n : distances) {
            System.out.printf("%d ", n);
        }
        System.out.println("");
    }

    // all pair shortest path
    private static void apsp(int source) {
        PriorityQueue<Edge> heap = new PriorityQueue<>(new MinHeap());
        heap.add(new Edge(source, source, 0));
        distances[source] = 0;
        while (!heap.isEmpty()) {
            Edge from = heap.poll();
            int u = from.u;
            if (!extracted[u]) {
                extracted[u] = true;
                for (Edge to : graph.get(u)) {
                    // vertice v
                    int v = to.v;
                    // peso para chegar ao vertice v
                    int w = to.w;
                    // a distancia para chegar ao vertice 'v' passando por 'u'
                    int c = distances[u] + w;
                    if (c < distances[v]) {
                        // se distances[v] == INF o vertice V ainda nao estava na SPT
                        // if (distances[v] != INF) {heap.remove(new Edge(v, v, distances[v]));}
                        distances[v] = c;
                        heap.add(new Edge(v, v, c));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        run(9);
    }
}
