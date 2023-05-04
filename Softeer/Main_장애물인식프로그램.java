package d2305;

import java.util.*;
import java.io.*;

public class Main_장애물인식프로그램 {

    static int N, tmpCnt;
    static int map[][];
    static boolean v[][];
    static ArrayList<Integer> list;
    static int di[] = {0, 0, 1, -1};
    static int dj[] = {1, -1, 0, 0};

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        v = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String str = st.nextToken();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int total = 0;
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !v[i][j]) {
                    v[i][j] = true;
                    tmpCnt = 1;
                    total = total + 1;
                    dfs(i, j);
                    list.add(tmpCnt);
                }
            }
        }

        Collections.sort(list);
        System.out.println(total);
        for (int i : list) {
            System.out.println(i);
        }
    }

    static void dfs(int i, int j) {

        for (int d = 0; d < 4; d++) {
            int ni = di[d] + i;
            int nj = dj[d] + j;

            if (ni < N && ni >= 0 && nj < N && nj >= 0 && map[ni][nj] == 1 && !v[ni][nj]) {
                tmpCnt = tmpCnt + 1;
                v[ni][nj] = true;
                dfs(ni, nj);
            }
        }
    }
}