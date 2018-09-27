package problems.hrank;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.hackerrank.com/challenges/primsmstsub/problem
 * */
public class SpecialSubtree {


    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

    private static final int INF = 1 << 30;


    private static int minIndex(int weight [], boolean [] mst) {
        int minIdx = 1, minValue = INF;
        for (int i = 1; i < weight.length ; i++) {
            if (weight[i] < minValue && ! mst[i]) {
                minValue = weight[i];
                minIdx = i;
            }
        }

        return minIdx;
    }

    // Complete the prims function below.
    static int prims(int n, int[][] edges, int start) {
        int [] weight = new int[n];
        Arrays.fill(weight, INF);
        weight[start] = 0;
        boolean [] mst = new boolean[n];
        mst[start] = true;
        for (int i = 1; i < n ; i++) {
            int u = minIndex(weight, mst);
            for (int v = 1; v < n; v++) {
                int d = edges[u][v];
                if (d > 0 && d < weight[v] && ! mst[v]) {
                    weight[v] = d;
                }
            }
        }
        int acc = 0;
        for (int i = 1; i < n; i++) {
            if (weight[i] == INF)
                continue;
            acc += weight[i];
        }
        return acc;
    }

    private static void run() {

        try {
            StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
            int vertices = Integer.parseInt(tk.nextToken());
            int edges= Integer.parseInt(tk.nextToken());
            int [][] graph = new int[vertices+1][vertices+1];
            for (int i = 0; i < edges ; i++) {
                tk = new StringTokenizer(reader.readLine(), " ");
                int u = Integer.parseInt(tk.nextToken());
                int v = Integer.parseInt(tk.nextToken());
                int w = Integer.parseInt(tk.nextToken());
                graph[u][v] = w;
                graph[v][u] = w;
            }
            int start = Integer.parseInt(reader.readLine());
            writer.printf("%d\n", prims(vertices+1, graph, start));

        } catch (IOException ieox) {}
    }

    public static void main(String[] args) {
        run();
    }
}
