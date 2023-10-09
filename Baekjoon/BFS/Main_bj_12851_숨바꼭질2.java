package d2310;

import java.io.*;
import java.util.*;

public class Main_bj_12851_숨바꼭질2 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 수빈
        int K = Integer.parseInt(st.nextToken()); // 동생

        int v[] = new int[100001];
        int min = 0;
        int count = 0;

        Queue<SuBin> q = new LinkedList<>();
        q.offer(new SuBin(N, 0));
        v[N] = 0;

        while (!q.isEmpty()) {

            SuBin subin = q.poll();
            int next = subin.n;
            int sec = subin.sec;

            if(min != 0 && sec > min){
                break;
            }

            if (next == K) {
                min = sec;
                count++;
            }

            for (int i = 0; i < 3; i++) {
                int n = next;
                if(i == 0) n = n - 1;
                else if(i == 1) n = n +1;
                else n = n * 2;

                if (n >= 0 && n <= 100000) {
                    if (v[n] == 0 || v[n] == sec + 1) {
                        v[n] = sec+1;
                        q.offer(new SuBin(n, sec + 1));
                    }
                }
            }
        }

        if(N == K){
            min = 0;
            count =1;
        }

        System.out.println(min);
        System.out.println(count);
    }

    static class SuBin {
        int n;
        int sec;

        SuBin(int n, int sec) {
            this.n = n;
            this.sec = sec;
        }
    }
}