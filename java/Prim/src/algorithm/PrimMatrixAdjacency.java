package algorithm;

import java.util.Arrays;

public class PrimMatrixAdjacency {

    private static final int INF = 1 << 30;
    private static boolean [] spanningTree;
    private static int [] parent, weight;
    private static int [][] graph;

    private static int minIndex() {
        int minIdx = 0, minValue = INF;
        for (int i = 0; i < weight.length ; i++) {
            if (!spanningTree[i] && weight[i] < minValue) {
                minValue = weight[i];
                minIdx = i;
            }
        }
        return minIdx;
    }

    private static void init(int vertices) {
        weight = new int[vertices];
        Arrays.fill(weight, INF);
        graph = new int[vertices][vertices];
        parent = new int[vertices];
        spanningTree = new boolean[vertices];
    }

    private static void add(int u, int v, int w) {
        graph[u][v] = w;
        graph[v][u] = w;
    }

    private static void prim(int vertices, int source) {
        spanningTree[source] = true;
        weight[source] = 0;
        for (int i = 0; i < vertices ; i++) {
            int u = minIndex();
            spanningTree[u] = true;
            for (int v = 0; v < vertices ; v++) {
                if (graph[u][v] != 0 && ! spanningTree[v] && graph[u][v] < weight[v]) {
                    weight[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        for (int i = 0; i < vertices ; i++) {
            System.out.printf("UV(%d, %d) W: %d\n", i, parent[i], graph[i][parent[i]]);
        }
    }

    private static void run() {
        /*
        init(9);
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
             prim(9, 0);
        */
        init(5);
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
        prim(5, 0);
    }

    public static void main(String[] args) {
        run();
    }
}
