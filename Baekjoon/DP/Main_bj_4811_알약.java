package d2306;

import java.io.*;
import java.util.*;

public class Main_bj_4811_알약 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[][] dp = new long[31][31];

        for (int h = 0; h <= 30; h++) {
            for (int w = 0; w <= 30; w++) {
                if (h > w) continue;
                if (h == 0) dp[w][h] = 1;
                else dp[w][h] = dp[w - 1][h] + dp[w][h - 1];
            }
        }

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            System.out.println(dp[N][N]);
        }

    }
}