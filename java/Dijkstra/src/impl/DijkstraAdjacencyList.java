package impl;

import java.util.*;

public class DijkstraAdjacencyList {

    private static class Edge {
        int u, v, w;
        private Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    private static ArrayList<LinkedHashSet<Edge>> graph;
    private static boolean [] spanningTree;
    private static int [] distance;
    private static final int INF = 1 << 30;


    private static int minIndex() {
        int minIdx = 0, min = INF;
        for (int i = 0; i < distance.length ; i++) {
            if (!spanningTree[i] && distance[i] < min) {
                minIdx = i;
                min = distance[i];
            }
        }
        return minIdx;
    }

    private static void init(int vertices) {
        graph = new ArrayList<>();
        for (int i = 0; i<vertices; i++) {
            graph.add(new LinkedHashSet<Edge>());
        }
        spanningTree = new boolean[vertices];
        distance = new int[vertices];
        Arrays.fill(distance, INF);
    }

    private static void add(int u, int v, int w) {
        graph.get(u).add(new Edge(u, v, w));
        graph.get(v).add(new Edge(v, u, w));
    }

    private static void apsp(int source) {
        distance[source] = 0;
        for (int i = 0; i<graph.size(); i++) {
            int u = minIndex();
            spanningTree[u] = true;
            for (Edge v : graph.get(u)) {
                if (!spanningTree[v.v]) {
                    // distancia para chegar em u + de u ate v
                    int d = distance[u] + v.w;
                    // se for menor atualize
                    if (d < distance[v.v]) {
                        distance[v.v] = d;
                    }
                }
            }
        }
    }

    private static void runTest() {
        init(9);
    }


    public static void main(String[] args) {

    }

}
