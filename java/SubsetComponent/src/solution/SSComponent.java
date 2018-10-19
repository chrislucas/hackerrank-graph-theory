package solution;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

/**
 * https://www.hackerrank.com/challenges/subset-component/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
 * https://en.wikipedia.org/wiki/Connected_component_%28graph_theory%29
 * */

public class SSComponent {

    static class WQU {
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

        private int find(int p) {
            while (parent[p] != p) {
                p = parent[p];
            }
            return p;
        }

        @Override
        public String toString() {
            return String.format("Componentes %d", comp);
        }
    }
    // recuperar o i-esimo bit menos significativo de um numero
    private static int ithlb(long number, int i) {
        return (int) ((number >> i) & 1);
    }
    // tentar conectar os conjuntos disjuntos
    private static void build(WQU wqu, long number) {
        int bits = (int) (Math.log(number) / Math.log(2) + 1);
        boolean f [] = new boolean[bits];
        for (int i = 0; i < bits ; i++) {
            f[i] = ithlb(number, i) == 1;
        }
        for (int i = 0; i < bits ; i++) {
            if (f[i]) {
                for (int j = i+1; j < bits ; j++) {
                    if (f[j]) {
                        wqu.union(i, j);
                    }
                }
            }
        }
    }

    private static boolean ispowerof2(long p) {
        return (p & (p-1)) == 0;
    }

    // TLE
    private static int run(int q, long numbers []) {
        int acc = 0;
        WQU wqu;
        // gerar todas as combinacoes usando tabela de bits
        for (int i = 1; i <(1<<q) ; i++) {
            wqu = new WQU(64);
            for (int j = 0; j < q ; j++) {
                // (i & (1 << j)) > 0  significa que o i-esimo bit esta ligado
                if ((i & (1 << j)) > 0 && !ispowerof2(numbers[j])) {
                    build(wqu, numbers[j]);
                }
            }
            acc += wqu.comp;
        }
        return acc + 64;
    }

    private static int count(long n) {
        int acc = 0;
        while (n > 0) {
            n &= (n-1);
            acc++;
        }
        return acc;
    }

    private static long run2(int q, long numbers []) {
        long acc = 0;
        LinkedHashMap<Long, Integer> graph = new LinkedHashMap<>();
        WQU wqu;
        for (long n : numbers) {
            wqu = new WQU(64);
            build(wqu, n);
            graph.put(n, wqu.comp);
            acc += wqu.comp;
        }

        for (int i = 1; i < (1 << q) ; i++) {
            long p = 0;
            for (int j = 0; j < q ; j++) {
                if ((i & (1 << j)) > 0) {
                    p |= numbers[j];
                }
            }
            if (!graph.containsKey(p))
                graph.put(p, 64 - count(p));
        }
        return acc + 64;
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(reader.readLine());
            long numbers [] = new long[n];
            StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
            for (int i = 0; tk.hasMoreTokens() ; i++) {
                numbers[i] = Long.parseLong(tk.nextToken());
            }
            System.out.println(run2(n, numbers));
        } catch (IOException ignored) {}
    }
}
