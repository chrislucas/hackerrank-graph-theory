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

    // recuperar o i-esimo bit menos significativo de um numero
    private static int ithlb(long number, int i) {
        return (int) ((number >> i) & 1);
    }

    private static long countComponents(long number) {
        int bits = (int) (Math.log(number) / Math.log(2) + 1);
        long components = 0;
        boolean f [] = new boolean[bits];
        for (int i = 0; i < bits ; i++) {
            if(ithlb(number, i)==1) {
                f[i] = true;
            }
        }
        for (int i = 0; i < bits ; i++) {
            if (f[i]) {
                for (int j = i+1; j < bits ; j++) {
                    if (f[j]) {
                        components++;
                    }
                }
            }
        }
        return components;
    }


    private static int run(int q, long numbers []) {
        int acc = 0;
        LinkedHashMap<Long, Long> table = new LinkedHashMap<>();
        for (long n : numbers)
            table.put(n, -1L);

        // gerar todas as combinacoes usando tabela de bits
        for (int i = 1; i <(1<<q) ; i++) {
            int p = 64;
            for (int j = 0; j < q ; j++) {
                // (i & (1 << j)) > 0  significa que o i-esimo bit esta ligado
                if ((i & (1 << j)) > 0 && table.get(numbers[j]) == -1) {
                    table.put(numbers[j], countComponents(numbers[j]));
                    p -=  table.get(numbers[j]);
                }
                else if ((i & (1 << j)) > 0 && table.get(numbers[j]) > -1) {
                    p -=   table.get(numbers[j]);
                }
            }
            acc += p;
        }
        return acc + 64;
    }

    /**
     * 3
     * 2 5 9
     * 8
     * 4436029718484152282 7960688025537172878 8158153106283749652 816298623023398913 7910562653274884366 4146540260192962824 7707065686924684372 95813014895467638
     *
     * */
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(reader.readLine());
            long numbers [] = new long[n];
            StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
            for (int i = 0; tk.hasMoreTokens() ; i++) {
                numbers[i] = Long.parseLong(tk.nextToken());
            }
            System.out.println(run(n, numbers));
        } catch (IOException ignored) {}
    }
}
