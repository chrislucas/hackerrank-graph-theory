package problems.uva;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1048
 * */

public class Uva10107 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);
        String in;
        long [] set = new long[10000];
        int counter = 0;
        while ( (in = reader.readLine()) != null) {
            long current = Long.parseLong(new StringTokenizer(in).nextToken());
            set[counter++] = current;
            if (counter == 1) {
                writer.printf("%d\n", current);
            }
            else {
                Arrays.sort(set, 0, counter);
                if ((counter & 1) == 0) {
                    long p = set[counter/2-1];
                    long q = set[counter/2];
                    writer.printf("%d\n", (p+q)/2);
                }
                else {
                    writer.printf("%d\n", set[counter/2]);
                }
            }
        }
    }
}
