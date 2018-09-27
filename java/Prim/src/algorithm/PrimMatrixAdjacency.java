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

    /**
     * Para o grafo do metodo test2() existem 2 possibilidades
     * de resultados de arvore geradora minima (isso quando comecamos do vertice 0):
     * a que inclui a aresta 0-7 ou a 1-2.
     *
     * Quando usamos o metodo sem fila
     * de prioridade a aresta 1-2 entra e a aresta 0-7 eh cortada porque o metodo minIndex()
     * procura o vertice com o 'menor peso de alcance' que ainda nao foi incluido
     * na MST, e o nesse processo o vertice 1 eh achado primeiro do que o 7.
     *
     * Quando usamos uma fila de prioridade o aresta 1-2 eh cortada porque a aresta
     * 0-7 apesar de ter o mesmo 'peso' foi adicionada antes no heap
     * */
    private static void prim(int vertices, int source) {
        spanningTree[source] = true;
        weight[source] = 0;
        for (int i = 0; i < vertices ; i++) {
            int u = minIndex();
            spanningTree[u] = true;
            for (int v = 0; v < vertices ; v++) {
                int d = graph[u][v];
                if (d != 0 && ! spanningTree[v] && d < weight[v]) {
                    weight[v] = d;
                    parent[v] = u;
                }
            }
        }
        for (int i = 0; i < vertices ; i++) {
            if (i == source)
                continue;
            System.out.printf("U-V(%d, %d) W: %d\n", parent[i], i, graph[i][parent[i]]);
        }
    }

    private static void test1() {
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
    }

    private static void test2() {
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

    private static void run() {
        test1();
    }

    public static void main(String[] args) {
        run();
    }
}
