package Labs10.Spantree3;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Spantree3 {

    static Random random = new Random();

    private static class NODE {

        public final int id;
        public int tree;

        public NODE(int id) {
            this.id = id;
            this.tree = id;
        }
    }

    private static class EDGE implements Comparable<EDGE>{

        public NODE origin, end;
        public long w;

        public EDGE(NODE origin, NODE end, long w) {
            this.origin = origin;
            this.end = end;
            this.w = w;
        }

        @Override
        public int compareTo(EDGE o) {
            return Float.compare(w, o.w);
        }
    }

    public static void uniteSubset(int[] parent, int a, int b){
        a = getSubset(parent, a);
        b = getSubset(parent, b);

        if(a == b)
            return;

        if(random.nextBoolean()){
            int t = a;
            a = b;
            b = t;
        }

        parent[a] = b;
    }

    public static int getSubset(int[] parent, int v){
        if(v == parent[v])
            return v;
        return parent[v] = getSubset(parent, parent[v]);
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastScanner scan = new FastScanner(new File("spantree3.in"));
        int topCount = scan.nextInt();
        int edgeCount = scan.nextInt();
        int[] p = new int[topCount];
        for (int i = 0; i < topCount; i++)
            p[i] = i;

        NODE[] nodes = new NODE[topCount];
        EDGE[] edges = new EDGE[edgeCount];
        for (int i = 0; i < topCount; i++)
            nodes[i] = new NODE(i);

        for (int i = 0; i < edgeCount; i++) {
            NODE origin = nodes[scan.nextInt() - 1];
            NODE end = nodes[scan.nextInt() - 1];
            edges[i] = new EDGE(origin, end, scan.nextInt());
        }

        long sum = 0;

        Arrays.sort(edges);
        for (int i = 0; i < edgeCount; i++) {
            EDGE edge = edges[i];

            if(getSubset(p, edge.origin.id) == getSubset(p, edge.end.id))
                continue;

            sum += edge.w;

            uniteSubset(p, edge.origin.id, edge.end.id);
        }

        PrintWriter out = new PrintWriter("spantree3.out");
        out.println(sum);
        out.close();
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

