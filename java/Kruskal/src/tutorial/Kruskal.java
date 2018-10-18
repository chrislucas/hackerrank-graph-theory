package tutorial;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Uma implementacao minha de um problema que usa kruskal
 * https://github.com/chrislucas/competitive-programming/blob/master/URIJudgeOnlime/src/grafos/URI1764.java
 *
 * Ponto interessate: uma arvore geradora minima (MST) eh um subgrafo G' de um grafo G conectado e nao direcionado
 * cujas arestas sao as de menor peso possível.
 *
 * Uma MST dum grafo G possui V-1 arestas, onde V eh o numero de vertices do grafo
 * */

public class Kruskal {
    // Weigthed quick Union
    public static class WQU {
        private int [] parent, weight;
        private int comp;
        WQU(int n) {
            this.comp = n;
            parent = new int[n];
            weight = new int[n];
            for (int i = 0; i < n ; i++) {
                parent[i] = i;
                weight[i] = 1;
            }
        }

        private boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        private void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) // conectados ?
                return;
            if (weight[rootP] < weight[rootQ]) {
                parent[rootP] = rootQ;
                weight[rootQ] += weight[rootP];
            }
            else {
                parent[rootQ] = rootP;
                weight[rootP] += weight[rootQ];
            }
            comp--;
        }

        private int find(int p) {
            while (parent[p] != p) {
                p = parent[p];
            }
            return p;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < parent.length ; i++)
                sb.append(String.format(i == 0 ? "(%d, %d)" : ", (%d, %d)", parent[i], weight[i]));
            return sb.toString();
        }
    }

    private static class Edge implements Comparable<Edge> {
        int from, to, w;
        Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge that) {
            return w - that.w;
        }

        @Override
        public String toString() { return String.format("From: %d To: %d Weight: %d", from, to, w); }
    }


    private static ArrayList<Edge> edges, mst;

    private static void addEdges(int u, int v, int w) {
        edges.add(new Edge(u, v, w));
    }


    private static int  solver1( int vertices) {
        int ithEdge = 0, sum = 0, minQEdges =0;
        WQU wqu = new WQU(vertices);
        while (minQEdges < vertices - 1) {
            Edge edge = edges.get(ithEdge++);
            int u = edge.from, v = edge.to;
            // se os vertices u e v nao estao conectados pelo mesmo vertice pai
            // mao a ciclos, ou seja nao tem um caminho que ligue u a v
            // , logo podemos inserir a aresta na arvore geradora minima
            if (!wqu.isConnected(u, v)) {
                wqu.union(u, v);
                mst.add(edge);
                minQEdges++;
                sum += edge.w;
            }
        }
        return sum;
    }

    private static int solver2(int vertices) {
        int sum = 0;
        WQU wqu = new WQU(vertices);
        for (Edge edge : edges) {
            int u = edge.from, v = edge.to;
            if (!wqu.isConnected(u, v)) {
                wqu.union(u, v);
                mst.add(edge);
                sum += edge.w;
            }
        }
        return sum;
    }

    private static int run() {
        int vertices = 9;
        edges = new ArrayList<>();
        mst = new ArrayList<>();
        addEdges(0, 1, 4);
        addEdges(0, 1, 3);
        addEdges(0, 7, 8);
        addEdges(1, 2, 8);
        addEdges(1, 7, 11);
        addEdges(2, 3, 7);
        addEdges(2, 5, 4);
        addEdges(2, 8, 2);
        addEdges(3, 4, 9);
        addEdges(3, 5, 14);
        addEdges(4, 5, 10);
        addEdges(5, 6, 2);
        addEdges(6, 8, 6);
        addEdges(6, 7, 1);
        addEdges(7, 8, 7);
        Collections.sort(edges);
        // numero minimo de arestas de uma MST = vertices - 1
        int sum = solver2(vertices);
        for (Edge edge : mst) {
            System.out.println(edge);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(run());
    }

}
