package algorithm;

import java.util.*;

public class PrimPQ {

    private static class Edge implements Comparable<Edge> {
        private int u, v, w;
        private Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
        @Override
        public String toString() {
            return String.format("UV(%d, %d) %d", u, v, w);
        }

        @Override
        public int compareTo(Edge that) {
            return w - that.w;
        }

        @Override
        public boolean equals(Object edge) {
            Edge that = (Edge) edge;
            return v == that.v && w == that.w && u == that.u;
        }
    }

    private static class MinHeap implements Comparator<Edge> {
        @Override
        public int compare(Edge p, Edge q) {
            return p.compareTo(q);
        }
    }

    private static final int INF = 1 << 30;

    private static boolean [] spanningTree;
    private static int [] parent, weight;

    private static LinkedHashMap<Integer, List<Edge>> graph;

    private static void init(int vertices) {
        spanningTree = new boolean[vertices];
        parent = new int[vertices];
        weight = new int[vertices];
        Arrays.fill(weight, INF);
        graph = new LinkedHashMap<>();
        for (int i = 0; i < vertices; i++) {
            graph.put(i, new ArrayList<>());
        }
    }

    private static void add(int u, int v, int w) {
        Edge uv = new Edge(u, v, w);
        graph.get(u).add(uv);
        graph.get(v).add(new Edge(v, u, w));
    }

    private static void prim(int source) {
        PriorityQueue<Edge> heap = new PriorityQueue<>(new MinHeap());
        heap.add(new Edge(source, source, 0));
        parent[source] = source;
        weight[source] = 0;
        while (!heap.isEmpty()) {
            Edge from = heap.poll();
            int u = from.u;
            if (!spanningTree[u]) {
                spanningTree[u] = true;
                for (Edge to : graph.get(u)) {
                    if (to.w < weight[to.v]) {
                        weight[to.v] = to.w;
                        parent[to.v] = u;
                        heap.add(new Edge(to.v, to.v, to.w));
                    }
                }
            }
        }

        for (int i = 0; i < parent.length ; i++) {
            if (i != source)
                System.out.printf("%d %d\n", i, parent[i]);
        }
    }

    private static void run(int vertices, int source) {
        init(vertices);
        /*
        add(0, 1, 4);
        add(0, 7, 8);
        add(1, 2, 8);
        add(1, 7, 11);
        add(2, 3, 7);
        add(2, 8, 2);
        add(2, 5, 4);
        add(3, 4, 9);
        add(3, 5, 14);
        add(4, 5, 10);
        add(5, 6, 2);
        add(6, 7, 1);
        add(6, 8, 6);
        add(7, 8, 7);
        */
        add(0, 1, 2);
        add(0, 3, 6);
        add(1, 0, 2);
        add(1, 2, 3);
        add(1, 3, 8);
        add(1, 4, 5);
        add(2, 1, 3);
        add(2, 4, 7);
        add(3, 1, 6);
        add(3, 2, 8);
        add(3, 4, 9);
        add(4, 1, 5);
        add(4, 2, 7);
        add(4, 3, 9);
        prim(source);
    }
    public static void main(String[] args) {
        //run(9,  0);
        run(5,  0);
    }

}
