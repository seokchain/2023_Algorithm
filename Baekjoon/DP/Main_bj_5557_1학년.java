package d2306;

import java.io.*;
import java.util.*;

public class Main_bj_5557_1학년 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        long dp[][] = new long[N - 1][21];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][arr[0]] = 1;

        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i][j] > 0) {
                    if (j + arr[i + 1] <= 20) dp[i + 1][j + arr[i + 1]] += dp[i][j];
                    if (j - arr[i + 1] >= 0) dp[i + 1][j - arr[i + 1]] += dp[i][j];
                }
            }
        }

        System.out.println(dp[N - 2][arr[N - 1]]);
    }
}
