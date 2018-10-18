package solution;

/**
 * Weighted quick union
 * */
public class WQU {
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
