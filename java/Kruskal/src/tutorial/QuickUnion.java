package tutorial;

public class QuickUnion {

    private int components;
    private int struct [];

    public QuickUnion(int n) {
        this.components = n;
        this.struct = new int[n];
        for (int i = 0; i < n ; i++) {
            this.struct[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        this.struct[rootP] = rootQ;
        this.components--;
    }

    public int find(int p) {
        while (struct[p] != p) {
            p = struct[p];
        }
        return p;
    }

    public int getComponents() {
        return components;
    }

    public static void main(String[] args) {

    }
}
