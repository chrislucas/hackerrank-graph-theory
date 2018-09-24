package study;

import java.util.ArrayList;

public class FW {


    private static final int INF = 1 << 30;


    private static void testInf() {
        System.out.println(0xf3f3f3);
        System.out.println(0xf3f3f3f3L);
        System.out.println(0xffffff);
        System.out.println(0xffffffffL);
        System.out.println(1 << 30);
    }

    private static void run(int [][] graph) {
        int v = graph.length;
        int dist [][] = new int[v][graph[0].length];
        int pre [][] = new int[v][graph[0].length];

        for (int i = 0; i < v; i++) {
            //System.arraycopy(dist[i], 0, graph[i], 0, graph[i].length);
            for (int j = 0; j < v ; j++) {
                dist[i][j] = graph[i][j];
                if (i != j /* && graph[i][j] != INF*/)
                    pre[i][j] = j;
            }
        }

        for (int k = 0; k < v ; k++) {  // vertice intermediario entre i e j
            // utilizar todos os vertices de 0 a V como vertices fontes de um percurso
            for (int i = 0; i < v ; i++) {
                // utilizar todos os vertices de 0 a V como vertices de destino de um percurso
                for (int j = 0; j < v; j++) {
                    if (i == j || i == k || k == j || dist[i][k] == INF || dist[k][j] == INF)
                        continue;
                    int iDistance = dist[i][k] + dist[k][j]; // distancia com o vertice k como intermediario
                    // a distancia como o vertice intermediario eh menor do que sem ele
                    if (iDistance < dist[i][j]) {
                        // se sim
                        dist[i][j] = iDistance;
                        pre[i][j] = pre[i][k];
                    }
                }
            }
        }

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (i != j) {
                    ArrayList<Integer> path = getPath(pre, i, j);
                    System.out.printf("U -> V(%d, %d): ", i, j);
                    for (Integer p : path) {
                        System.out.printf(" %d ", p);
                    }
                }
            }
            System.out.println("");
        }
    }

    // https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm#Path_reconstruction
    private static ArrayList<Integer> getPath(int pre[][], int u, int v) {
        if (pre[u][v] == 0)
            return new ArrayList<>();
        ArrayList<Integer> p = new ArrayList<>();
        p.add(u);
        while (u != v) {
            u = pre[u][v];
            p.add(u);
        }
        return p;
    }

    private static void test() {
        // 0  onde i == j
        // INF para os vertices i e j que nao possuem arestas conectado-os
        int graphs [][][] =
        {
            {
                        {0, 5, INF, 10}
                    , {INF, 0, 3, INF}
                    , {INF, INF, 0, 1}
                    , {INF, INF, INF, 0}
            }
            ,{
                {0, 2, 10, 5, 7}
                , {3, 0, 13, 8, 10}
                , {7, 4, 0, 12, 14}
                , {12, 9, 5, 0, 2}
                , {10, 7, 3, 15, 0}
            }
            , {{1, 3, -2}, {2, 1, 4}, {2, 3, 3}, {3, 4, 2}, {4, 2, -1}}
        };
        int graph [][] = graphs[0];
        run(graph);
        for (int i = 0; i < graph.length ; i++) {
            for (int j = 0; j < graph[i].length ; j++) {
                System.out.printf("%d ", graph[i][j]);
            }
            System.out.println("");
        }


    }

    public static void main(String[] args) {
        test();
    }
}
