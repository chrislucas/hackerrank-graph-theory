package problems.hrank;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.hackerrank.com/challenges/floyd-city-of-blinding-lights/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
 * DONE 100%
 * */

public class CityBlindLights {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

    private static final int INF = 1 << 30;

    private static void apsp(int [][] dist, int n) {
        for (int k = 1; k < n ; k++) {
            for (int u = 1; u < n; u++) {
                for (int v = 1; v < n ; v++) {
                    if (dist[u][k] != INF && dist[k][v] != INF && k != u && k != v && u != v) {
                        int d = dist[u][k] + dist[k][v];
                        if (d < dist[u][v])
                            dist[u][v] = d;
                    }
                }
            }
        }
    }

    private static void run() {
        try {
            StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
            int n = Integer.parseInt(tk.nextToken());
            int e = Integer.parseInt(tk.nextToken());
            n += 1;
            int graph [][] = new int[n][n];

            for (int i = 1; i < n ; i++) {
                Arrays.fill(graph[i], INF);
                graph[i][i] = 0;
            }

            for (int i = 0; i < e ; i++) {
                tk = new StringTokenizer(reader.readLine(), " ");
                int u = Integer.parseInt(tk.nextToken());
                int v = Integer.parseInt(tk.nextToken());
                int w = Integer.parseInt(tk.nextToken());
                graph[u][v] = w;
            }

            int dist [][] = new int[n][n];
            for (int i = 0; i < n; i++)
                System.arraycopy(graph[i], 0, dist[i], 0, dist[i].length);

            apsp(dist, n);
            int queries = Integer.parseInt(reader.readLine());
            for (int i = 0; i < queries ; i++) {
                tk = new StringTokenizer(reader.readLine(), " ");
                int u = Integer.parseInt(tk.nextToken());
                int v = Integer.parseInt(tk.nextToken());
                writer.printf("%d\n", dist[u][v] == INF ? -1 : dist[u][v]);
            }

        } catch (IOException e) {}
    }


    public static void main(String[] args) {
        run();
    }
}
