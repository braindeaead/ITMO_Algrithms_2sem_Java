package Labs11;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SmallestPath {
    public static void main(String[] args) throws FileNotFoundException {
        FastScanner scan = new FastScanner(new File("pathmgep.in"));
        int N = scan.nextInt();
        int S = scan.nextInt()-1;
        int F = scan.nextInt()-1;

        int[] data = new int[N * N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                data[i * N + j] = scan.nextInt();

        long[] distance = new long[N];
        boolean[] isUsed = new boolean[N];
        Arrays.fill(distance, Long.MAX_VALUE);

        distance[S] = 0;
        for (int i = 0; i < N; i++) {
            int v = -1;
            for (int j = 0; j < N; j++)
                if(!isUsed[j] && (v == -1 || distance[j] < distance[v]))
                    v = j;

            isUsed[v] = true;

            if (distance[v] == Long.MAX_VALUE)
                break;


            for (int j = 0; j < N; j++) {
                if(j == v)
                    continue;

                if(isUsed[j])
                    continue;

                int edgeCost = data[v * N + j];
                if(edgeCost < 0)
                    continue;

                long dist = distance[v] + edgeCost;
                if (dist < distance[j])
                    distance[j] = dist;

            }
        }

        PrintWriter out = new PrintWriter(new FileOutputStream("pathmgep.out"));
        long end = distance[F];
        if(end == Long.MAX_VALUE){
            out.println(-1);
        } else {
            out.println(end);
        }

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
