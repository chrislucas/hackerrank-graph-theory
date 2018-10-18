package problems.hackerrank;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * https://www.hackerrank.com/challenges/kruskalmstrsub/problem
 * DONE
 * */
public class ReallySpecialSubTree {

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

        private boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        private int find(int p) {
            while (parent[p] != p) {
                p = parent[p];
            }
            return p;
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

    private static ArrayList<Edge> edges;

    private static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
            int v = Integer.parseInt(tk.nextToken());
            int e = Integer.parseInt(tk.nextToken());
            edges = new ArrayList<>();
            for (int i = 0; i < e ; i++) {
                tk = new StringTokenizer(reader.readLine());
                int f = Integer.parseInt(tk.nextToken());
                int t = Integer.parseInt(tk.nextToken());
                int w = Integer.parseInt(tk.nextToken());
                edges.add(new Edge(f, t, w));
            }
            Collections.sort(edges);
            WQU wqu = new WQU(v);
            int acc = 0;
            for (Edge edge : edges) {
                int p = edge.from-1, q = edge.to-1;
                if (!wqu.connected(p, q)) {
                    wqu.union(p, q);
                    acc += edge.w;
                }
            }
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);
            writer.printf("%d\n", acc);
        } catch (IOException ignored) {}
    }

    public static void main(String[] args) {
        run();
    }
}
