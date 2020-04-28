package Labs10.Spantree1;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Spantree1 {

    private static class NODE {

        public final int x, y;

        double mindDist = Double.MAX_VALUE;

        boolean isVisited = false;

        public NODE(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double getDistance(NODE u, NODE v) {
        int dx = v.x - u.x;
        int dy = v.y - u.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan;
        scan = new Scanner(new FileInputStream("spantree.in"));

        int n = scan.nextInt();

        NODE[] nodes = new NODE[n];

        for (int i = 0; i < n; i++)
            nodes[i] = new NODE(scan.nextInt(), scan.nextInt());

        nodes[0].mindDist = 0;
        nodes[0].isVisited = true;

        for (int i = 1; i < n; i++) {
            NODE node = nodes[i];
            node.mindDist = getDistance(nodes[0], node);
        }

        for (int i = 1; i < n - 1; i++) {
            NODE v = null;
            for (int j = 0; j < n; j++) {
                NODE g = nodes[j];
                if (!g.isVisited && (v == null || g.mindDist < v.mindDist))
                    v = g;
            }

            v.isVisited = true;

            for (int j = 0; j < n; j++) {

                NODE g = nodes[j];

                if(g == v || g.isVisited)
                    continue;

                double d = getDistance(v, g);

                if (d < g.mindDist)
                    g.mindDist = d;
            }
        }

        double s = 0;
        for (NODE node : nodes) {
            s += node.mindDist;
        }

        PrintWriter out = new PrintWriter(new FileOutputStream("spantree.out"));

        out.println(s);

        out.close();


    }
}
