package d2308;

import java.util.*;

public class 연속부분수열합의개수Solution {

    public int solution(int[] elements) {
        int N = elements.length;
        int cnt = 1;
        int dp[][] = new int[N][N];
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            dp[i][0] = elements[i];
            set.add(dp[i][0]);
        }

        while (cnt < N) {
            for (int i = 0; i < N; i++) {
                int idx = i + cnt;
                if (idx >= N) idx -= N;
                dp[i][cnt] = dp[i][cnt - 1] + elements[idx];
                set.add(dp[i][cnt]);
            }
            cnt++;
        }

        return set.size();
    }
}