package d2310;

import java.io.*;
import java.util.*;

public class Main_bj_17779_게리맨더링2 {

    static int N, answer;
    static int peopleCount[][], map[][];

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        peopleCount = new int[N + 1][N + 1];
        answer = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                peopleCount[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {

                for (int d1 = 1; d1 <= y - 1; d1++) {
                    for (int d2 = 1; d2 <= N - y; d2++) {

                        if (d1 + d2 <= N - x) {
                            cross(x, y, d1, d2);
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static void cross(int x, int y, int d1, int d2) {

        map = new int[N + 1][N + 1];

        for (int i = 0; i <= d1; i++) {
            int dx = x + i;
            int dy = y - i;
            map[dx][dy] = 5;
        }

        for (int i = 0; i <= d2; i++) {
            int dx = x + i;
            int dy = y + i;
            map[dx][dy] = 5;
        }

        for (int i = 0; i <= d2; i++) {
            int dx = x + d1 + i;
            int dy = y - d1 + i;
            map[dx][dy] = 5;
        }

        for (int i = 0; i <= d1; i++) {
            int dx = x + d2 + i;
            int dy = y + d2 - i;
            map[dx][dy] = 5;
        }

        fillOtherArea(x, y, d1, d2);
        getSum();
        answer = Math.min(answer, getSum());
    }

    static void fillOtherArea(int x, int y, int d1, int d2) {

        here:
        for (int i = 1; i < x + d1; i++) {
            for (int j = 1; j <= y; j++) {
                if (map[i][j] == 5) continue here;
                map[i][j] = 1;
            }
        }

        here:
        for (int i = x + d2; i >= 1; i--) {
            for (int j = N; j >= y + 1; j--) {
                if (map[i][j] == 5) continue here;
                map[i][j] = 2;
            }
        }

        here:
        for (int i = x + d1; i <= N; i++) {
            for (int j = 1; j < y - d1 + d2; j++) {
                if (map[i][j] == 5) continue here;
                map[i][j] = 3;
            }
        }

        here:
        for (int i = N; i > x + d2; i--) {
            for (int j = N; j >= y - d1 + d2; j--) {
                if (map[i][j] == 5) continue here;
                map[i][j] = 4;
            }
        }
    }

    static int getSum() {

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int count[] = new int[6];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 5구역 안쪽을 5로 채워준다.
                if (map[i][j] == 0) map[i][j] = 5;
                count[map[i][j]] += peopleCount[i][j];
            }
        }

        for (int i = 1; i <= 5; i++) {
            max = Math.max(count[i], max);
            min = Math.min(count[i], min);
        }
        return max - min;
    }
}