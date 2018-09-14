public class MatrixAdjacency {


    private static int [][] matrix;
    private static boolean [] visiteds;
    private static int [] distances;

    private static void minDistance() {

    }

    // All pairs shortest path
    private static void alsp() {

    }

    private static void test1() {
        //add();
    }

    private static void add(int a, int b) {
        matrix[a][b] = 1;
    }

    public static void run(int vertices) {
        matrix = new int[vertices][vertices];
        visiteds = new boolean[vertices];
        distances = new int[vertices];
        test1();
    }


    public static void main(String[] args) {

    }
}
