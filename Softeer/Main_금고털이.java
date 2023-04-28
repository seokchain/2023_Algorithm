package d2304;

import java.util.*;
import java.io.*;

public class Main_금고털이 {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        Metal metal[] = new Metal[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            metal[i] = new Metal(m, p);
        }

        Arrays.sort(metal, new Comparator<Metal>() {
            @Override
            public int compare(Metal o1, Metal o2) {
                if (o1.p == o2.p) {
                    return o2.m - o1.m;
                }
                return o2.p - o1.p;
            }
        });

        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (W >= metal[i].m) {
                W = W - metal[i].m;
                answer += metal[i].m * metal[i].p;

                // 금속을 잘라야 하는 경우
            } else {
                answer += W * metal[i].p;
                break;
            }
        }
        System.out.println(answer);
    }

    static class Metal {

        int m;
        int p;

        Metal(int m, int p) {
            this.m = m;
            this.p = p;
        }
    }
}