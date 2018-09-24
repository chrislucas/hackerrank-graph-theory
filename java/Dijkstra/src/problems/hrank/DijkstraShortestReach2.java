package problems.hrank;

import impl.ShortestPathPQ;

import java.io.*;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/dijkstrashortreach/problem
 * */

public class DijkstraShortestReach2 {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

    private static final int INF = 1 << 27;

    private static int getMinIdxDistance(int [] distance, boolean [] spanningTree) {
        int minIdx = 1, minValue = INF;
        for (int i = 1; i<distance.length ; i++) {
            if ( ! spanningTree[i] && distance[i] <= minValue) {
                minValue = distance[i];
                minIdx = i;
            }
        }
        return minIdx;
    }

    public static class Edge implements Comparable<Edge> {
        int u, v, w;
        private Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
        @Override
        public String toString() {
            return String.format("U: %d V: %d W: %d", u, v, w);
        }

        @Override
        public int compareTo(Edge that) {
            return this.w - that.w;
        }

        @Override
        public boolean equals(Object obj) {
            return v == ((Edge) obj).v && u == ((Edge) obj).u;
        }
    }

    public static class ComparatorMinPQ implements Comparator<Edge> {
        @Override
        public int compare(Edge p, Edge q) {
            return p.compareTo(q);
        }
    }

    private static int[] apsp2(int n,  ArrayList<ArrayList<Edge>> graph, int s) {
        int distance [] = new int[n];
        boolean spanningTree [] = new boolean[n];
        Arrays.fill(distance, INF);
        distance[s] = 0;
        PriorityQueue<Edge> heap = new PriorityQueue<Edge>(new ComparatorMinPQ());
        heap.add(new Edge(s, s, 0));
        while (!heap.isEmpty()) {
            Edge from = heap.poll();
            if (!spanningTree[from.v]) {
                spanningTree[from.v] = true;
                for (Edge edge : graph.get(from.v)) {
                    int d = distance[from.v] + edge.w;
                    if (d < distance[edge.v]) {
                        distance[edge.v] = d;
                        heap.add(new Edge(edge.v, edge.v, d));
                    }
                }
            }
        }
        int rs [] = new int[n-2];
        for (int i=1, j=0; i<distance.length; i++) {
            if (i == s)
                continue;
            rs[j++] = distance[i] == INF ? -1 : distance[i];
        }
        return rs;
    }

    // Complete the shortestReach function below. // SOLUCAO com Lista de adjacency da TIMEOUT
    private static int[] apsp(int n,  ArrayList<ArrayList<Edge>> graph, int s) {
        int distance [] = new int[n];
        boolean spanningTree [] = new boolean[n];
        Arrays.fill(distance, INF);
        distance[s] = 0;
        for (int i = 1; i <n ; i++) {
            int u = getMinIdxDistance(distance, spanningTree);
            spanningTree[u] = true;
            for (Edge edge : graph.get(u)){
                if (!spanningTree[edge.v]) {
                    int w = edge.w;
                    // de w > 0 existe uma aresta entre u e v
                    if (w > 0) {
                        // distancia para chegar ao vertice u e do vertice u alcancar o v
                        int d = distance[u] + w;
                        // se a distancia entre u e v atual for menor, atualize a nova menor distancia
                        if (d < distance[edge.v]) {
                            distance[edge.v] = d;
                        }
                    }
                }
            }
        }
        int rs [] = new int[n-2];
        for (int i=1, j=0; i<distance.length; i++) {
            if (i == s)
                continue;
            rs[j++] = distance[i] == INF ? -1 : distance[i];
        }
        return rs;
    }

    private static void test(Iterator<Edge> it, Edge target, ArrayList<ArrayList<Edge>> graph, int u) {
        while (it.hasNext()) {
            Edge e = it.next();
            if (e.equals(target) && e.w > target.w) {
                it.remove();
                graph.get(u).add(target);
                break;
            }
        }
    }

    private static int[] shortestReach(int n, int[][] edges, int s) {
        n += 1;
        //int [][] matrix = new int[n][n];
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n ; i++) {
            graph.add(new ArrayList<Edge>());
        }
        for (int[] data : edges) {
            //matrix[edge[0]][edge[1]] = edge[2];
            //matrix[edge[1]][edge[0]] = edge[2];
            // verificar se ja na existe uma aresta de menor peso
            Edge uv = new Edge(data[0], data[1], data[2]);
            if (!graph.get(data[0]).contains(uv))
                graph.get(data[0]).add(uv);
            else {
                Iterator<Edge> it = graph.get(data[0]).iterator();
                test(it, uv, graph, data[0]);
            }
            Edge vu = new Edge(data[1], data[0], data[2]);
            if (!graph.get(data[1]).contains(vu))
                graph.get(data[1]).add(vu);
            else {
                Iterator<Edge> it = graph.get(data[1]).iterator();
                test(it, vu, graph, data[1]);
            }
            /*
            graph.get(data[0]).add(uv);
            Edge b = new Edge(data[0], data[2]);
            graph.get(data[1]).add(b);
            */
        }
        return apsp2(n, graph, s);
    }

    static void run() {
        try {
            int cases = Integer.parseInt(reader.readLine());
            while (cases > 0) {
                StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
                int vertices = Integer.parseInt(tk.nextToken());
                int edges = Integer.parseInt(tk.nextToken());
                int [][] graph = new int[edges][3];
                for (int i = 0; i < edges; i++) {
                    tk = new StringTokenizer(reader.readLine(), " ");
                    for (int j = 0; j < 3 ; j++) {
                        graph[i][j] = Integer.parseInt(tk.nextToken());
                    }
                }
                int distances [] = shortestReach(vertices, graph, Integer.parseInt(reader.readLine()));
                for (int i = 0; i < distances.length ; i++) {
                    System.out.printf("%d ", distances[i]);
                }
                System.out.println("");
                cases--;
            }
        } catch (IOException ioex) {}
    }

    public static void main(String[] args) {
        run();
    }
}
