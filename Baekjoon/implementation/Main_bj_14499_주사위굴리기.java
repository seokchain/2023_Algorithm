package d230328;

import java.io.*;
import java.util.*;

public class Main_bj_14499_주사위굴리기 {

    static int dice[];

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_14499.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int map[][] = new int[N][M];

        dice = new int[7];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");

        // 동1, 서2, 북3, 남4
        int di[] = {0, 0, 0, -1, 1};
        int dj[] = {0, 1, -1, 0, 0};

        for (int n = 0; n < K; n++) {

            int dir = Integer.parseInt(st.nextToken());

            int ni = x + di[dir];
            int nj = y + dj[dir];

            if (ni >= N || ni < 0 || nj >= M || nj < 0) {
                continue;
            } else {
                x = ni;
                y = nj;
            }

            roll(dir);

            if (map[x][y] == 0) {
                map[x][y] = dice[1];
            } else {
                dice[1] = map[x][y];
                map[x][y] = 0;
            }
            System.out.println(dice[6]);
        }
    }

    static void roll(int dir) {
        int tmp[];
        tmp = dice.clone();

        if (dir == 1) { // 동
            dice[4] = tmp[1];
            dice[1] = tmp[3];
            dice[3] = tmp[6];
            dice[6] = tmp[4];
        } else if (dir == 2) { // 서
            dice[4] = tmp[6];
            dice[1] = tmp[4];
            dice[3] = tmp[1];
            dice[6] = tmp[3];
        } else if (dir == 3) { // 북
            dice[2] = tmp[6];
            dice[1] = tmp[2];
            dice[5] = tmp[1];
            dice[6] = tmp[5];
        } else if (dir == 4) { // 남
            dice[2] = tmp[1];
            dice[1] = tmp[5];
            dice[5] = tmp[6];
            dice[6] = tmp[2];
        }
    }
}
