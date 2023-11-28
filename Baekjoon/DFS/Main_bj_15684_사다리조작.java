package d2311;

import java.io.*;
import java.util.*;

public class Main_bj_15684_사다리조작 {

    static int N, M, H, res;
    static int map[][];

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        res = -1;
        map = new int[H][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a - 1][b - 1] = 1;
            map[a - 1][b] = 2;
        }

//       for (int ls[]:map) { System.out.println(Arrays.toString(ls));}

        if (check()) res = 0;

        else {
            for (int i = 1; i < 4; i++) {
                if (res > 0) break;
                dfs(0, i);
            }
        }
        System.out.print(res);
    }

    static void dfs(int cnt, int size) {

        if (cnt == size) {
            if (check()) res = size;
            return;
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {

                if (map[i][j] > 0 || map[i][j + 1] > 0) continue;
                map[i][j] = 1;
                map[i][j + 1] = 2;
                dfs(cnt + 1, size);
                map[i][j] = 0;
                map[i][j + 1] = 0;
            }
        }
    }

    static boolean check() {
        for (int j = 0; j < N; j++) {
            int ni = 0;
            int nj = j;
            while (ni < H) {
                if (map[ni][nj] == 1) {
                    nj++;
                } else if (map[ni][nj] == 2) {
                    nj--;
                }
                ni++;
            }
            if (j != nj) return false;
        }
        return true;
    }
}
