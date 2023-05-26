package d2305;

import java.io.*;
import java.util.*;

public class Main_bj_12869_뮤탈리스크 {

    static int dp[][][];
    static int min;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_12869.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int arr[] = new int[3];
        dp = new int[61][61][61];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        min = Integer.MAX_VALUE;
        dfs(arr[0], arr[1], arr[2], 0);

        System.out.println(min);
    }

    static void dfs(int s1, int s2, int s3, int cnt) {

        if (cnt >= min) {
            return;
        }

        if (s1 <= 0) s1 = 0;
        if (s2 <= 0) s2 = 0;
        if (s3 <= 0) s3 = 0;

        if (s1 == 0 && s2 == 0 && s3 == 0) {
            min = Math.min(min, cnt);
            return;
        }

        if (dp[s1][s2][s3] > 0 && dp[s1][s2][s3] <= cnt) {
            return;
        }

        dp[s1][s2][s3] = cnt;

        dfs(s1 - 9, s2 - 3, s3 - 1, cnt + 1);
        dfs(s1 - 9, s2 - 1, s3 - 3, cnt + 1);
        dfs(s1 - 3, s2 - 9, s3 - 1, cnt + 1);
        dfs(s1 - 3, s2 - 1, s3 - 9, cnt + 1);
        dfs(s1 - 1, s2 - 3, s3 - 9, cnt + 1);
        dfs(s1 - 1, s2 - 9, s3 - 3, cnt + 1);
    }
}
