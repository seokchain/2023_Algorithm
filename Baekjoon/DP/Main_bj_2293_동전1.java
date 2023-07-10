package d2307;

import java.io.*;
import java.util.*;

public class Main_bj_2293_동전1 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int num = Integer.parseInt(st.nextToken());

        int dp[] = new int[num + 1];

        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());
            for (int j = 1; j <= num; j++) {
                if (j > coin) {
                    dp[j] = dp[j] + dp[j - coin];
                } else if (j == coin) {
                    dp[j]++;
                }
            }
        }

        System.out.println(dp[num]);
    }
}
