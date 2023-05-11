package d2305;

import java.util.*;
import java.io.*;

public class Main_GBC {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int arr[] = new int[101];
        int answer = 0;

        int flag = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int m = Integer.parseInt(st.nextToken());
            int ms = Integer.parseInt(st.nextToken());
            for (int j = flag; j < flag + m; j++) {
                arr[j] = ms;
            }
            flag = flag + m;
        }

        int testFlag = 1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int ms = Integer.parseInt(st.nextToken());

            for (int j = testFlag; j < testFlag + m; j++) {
                answer = Math.max(ms - arr[j], answer);
            }
            testFlag = testFlag + m;
        }
        System.out.println(answer);
    }
}
