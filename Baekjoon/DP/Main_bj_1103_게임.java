package d2310;

import java.io.*;
import java.util.*;

public class Main_bj_1103_게임 {

    static int N, M, map[][], dp[][], max;
    static boolean v[][], loop;
    static int di[] = {0, 0, -1, 1};
    static int dj[] = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        v = new boolean[N][M];
        max = Integer.MIN_VALUE;
        loop = false;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        v[0][0] = true;
        dfs(0, 0, 1);

        if (loop) max = -1;
        System.out.println(max);

    }

    static void dfs(int x, int y, int cnt) {

        max = Math.max(max, cnt);

        for (int d = 0; d < 4; d++) {

            int dx = x + di[d] * map[x][y];
            int dy = y + dj[d] * map[x][y];

            if (dx < 0 || dx >= N || dy < 0 || dy >= M || map[dx][dy] == 24) continue;

            if (v[dx][dy]) {
                loop = true;
                return;
            }

            if (dp[dx][dy] >= cnt + 1) continue;

            v[dx][dy] = true;
            dp[dx][dy] = cnt + 1;
            dfs(dx, dy, cnt + 1);
            v[dx][dy] = false;
        }
    }
}