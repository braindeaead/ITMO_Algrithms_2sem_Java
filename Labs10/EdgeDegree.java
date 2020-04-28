package Labs10.EdgeDegree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;


public class EdgeDegree {

    public static class DOT {

        public final int id;
        public int degree = 0;

        public DOT(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan;
        scan = new Scanner(new FileInputStream("input.txt"));

        int n = scan.nextInt();
        int m = scan.nextInt();

        DOT[] dots = new DOT[n];

        for (int i = 0; i < dots.length; i++)
            dots[i] = new DOT(i);

        for (int i = 0; i < m; i++) {
            int from = scan.nextInt() - 1;
            int to = scan.nextInt() - 1;
            dots[from].degree++;
            dots[to].degree++;
        }

        PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));

        for (int i = 0; i < dots.length; i++) {
            out.print(dots[i].degree);
            out.print(' ');
        }

        out.close();


    }
}