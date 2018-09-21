package problems.hackerearth;

import impl.ShortestPathPQ;
import problems.hrank.DijkstraShortestReach2;

import java.io.*;
import java.util.*;

/**
 * https://www.hackerearth.com/problem/algorithm/dijkstras/
 * DONE
 * */

public class Dijkstra {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);
    public static class Edge implements Comparable<Edge> {
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
    }

    public static class ComparatorMinPQ implements Comparator<Edge> {
        @Override
        public int compare(Edge p, Edge q) {
            return p.compareTo(q);
        }
    }

    private static LinkedHashMap<Integer, ArrayList<Edge>> graph;

    private static final int INF = 1 << 30;
    private static int [] distances, parent;
    private static boolean [] extracted;

    private static void path(int target) {
        if (target == parent[target]) {
            writer.printf("%d", target);
            return;
        }
        path(parent[target]);
        writer.printf(" %d", target);
    }

    private static void apsp(int source, int target) {
        Arrays.fill(distances, INF);
        distances[source] = 0;
        parent[source] = 0;
        PriorityQueue<Edge> heap = new PriorityQueue<Edge>(new ComparatorMinPQ());
        heap.add(new Edge(source, source, 0));
        while (!heap.isEmpty()) {
            Edge from = heap.poll();
            if (!extracted[from.u]) {
                extracted[from.u] = true;
                for (Edge to : graph.get(from.u)) {
                    int d = distances[from.u] + to.w;
                    if (d < distances[to.v]) {
                        distances[to.v] = d;
                        parent[to.v] = from.u;
                        heap.add(new Edge(to.v, to.v, d));
                    }
                }
            }
        }
        writer.printf("%d\n", distances[target]);
        path(target);
        writer.printf("\n");
    }

    private static void run() {
        try {
            int cases = Integer.parseInt(reader.readLine());
            while (cases > 0) {
                int lines = Integer.parseInt(reader.readLine());
                distances = new int[lines];
                parent = new int[lines];
                extracted = new boolean[lines];
                graph = new LinkedHashMap<>();
                for (int i = 0; i < lines ; i++) {
                    graph.put(i, new ArrayList<>());
                    StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
                    for (int j = 0; tk.hasMoreTokens() ; j++) {
                        int n = Integer.parseInt(tk.nextToken());
                        if (n > 0) {
                            graph.get(i).add(new Edge(i, j, n));
                        }
                    }
                }
                StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
                int s = Integer.parseInt(tk.nextToken());
                int t = Integer.parseInt(tk.nextToken());
                apsp(s, t);
                cases--;
            }

        } catch (IOException ioex) {}
    }

    public static void main(String[] args) {
        run();
    }

}
