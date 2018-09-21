package impl;

public class MatrixAdjacency {

    private static int [][] matrix;
    private static boolean [] spanningTree; // vertices visitados
    private static int [] distances;
    private static final int  INF = Integer.MAX_VALUE;

    private static int getVertexMinDistance() {
        int min = INF, minIdx = 0;
        for (int i = 0; i < distances.length ; i++) {
            if (!spanningTree[i] && min >= distances[i]) {
                min = distances[i];
                minIdx = i;
            }
        }
        return minIdx;
    }

    // All pairs shortest path
    private static void apsp(int source) {
        distances[source] = 0;
        int limit = distances.length;
        for (int i = 0; i < limit-1 ; i++) {
            // recuperar o indice do vertice menos 'custoso'
            int u = getVertexMinDistance();
            // adicionar o vertice que possui o menor peso a arvore geradora minima
            spanningTree[u] = true;
            for (int v = 0; v < limit ; v++) {
                // distancia entre o vertice uv
                int w = matrix[u][v];
                // se ha um caminho entre u e v, w > 0
                if (w > 0) {
                    //a distancia do vertice de origem ate o vertice 'u' para chegar no vertice 'v'
                    int d = distances[u] + w;
                    // se o vertice v nao foi adicionado a arvore minima verificar se a distancia
                    // entre de u ate v atualmente e a menor registrada
                    if (!spanningTree[v] && d < distances[v]) {
                        // se sim atualize
                        distances[v] = d;
                    }
                }
            }
        }
    }

    private static void init(int vertices) {
        matrix = new int[vertices][vertices];
        spanningTree = new boolean[vertices];
        distances = new int[vertices];
        for (int i = 0; i <vertices ; i++) {
            distances[i] = INF;
        }
    }
    private static void test1(int vertices) {
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

    private static void add(int a, int b, int w) {
        matrix[a][b] = w;
    }

    public static void main(String[] args) {
        test1(9);
    }
}
