package d2306;

import java.io.*;
import java.util.*;

public class Main_bj_17070_파이프옮기기1 {

    static int N, map[][];
    static boolean v[][];

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        v = new boolean[N + 1][N + 1];
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 1, 1, 2));

        while (!q.isEmpty()) {

            if (map[N][N] == 1) break;

            Node node = q.poll();

            int x1 = node.x1;
            int y1 = node.y1;
            int x2 = node.x2;
            int y2 = node.y2;
            if (x2 == N && y2 == N) {
                cnt = cnt + 1;
            }

            // 가로
            if (x1 == x2) {
                // 가로
                if (y2 + 1 <= N && map[x2][y2 + 1] != 1) {
                    q.offer(new Node(x2, y2, x2, y2 + 1));
                }
                // 대각선
                if (check(x2, y2)) {
                    q.offer(new Node(x2, y2, x2 + 1, y2 + 1));
                }
            }

            // 세로
            else if (y1 == y2) {
                // 세로
                if (x2 + 1 <= N && map[x2 + 1][y2] != 1) {
                    q.offer(new Node(x2, y2, x2 + 1, y2));
                }
                // 대각선
                if (check(x2, y2)) {
                    q.offer(new Node(x2, y2, x2 + 1, y2 + 1));
                }
            }

            // 대각선
            else {

                // 가로
                if (y2 + 1 <= N && map[x2][y2 + 1] != 1) {
                    q.offer(new Node(x2, y2, x2, y2 + 1));
                }
                // 세로
                if (x2 + 1 <= N && map[x2 + 1][y2] != 1) {
                    q.offer(new Node(x2, y2, x2 + 1, y2));
                }
                // 대각선
                if (check(x2, y2)) {
                    q.offer(new Node(x2, y2, x2 + 1, y2 + 1));
                }
            }
        }

        System.out.println(cnt);

    }

    static boolean check(int x, int y) {
        if (x + 1 > 0 && x + 1 <= N && y + 1 > 0 && y + 1 <= N) {
            if (map[x + 1][y] != 1 && map[x][y + 1] != 1 && map[x + 1][y + 1] != 1) return true;
        }
        return false;
    }

    static class Node {

        int x1, y1, x2, y2;

        Node(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

    }
}
