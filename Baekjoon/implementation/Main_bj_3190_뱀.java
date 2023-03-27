package d230322;

import java.io.*;
import java.util.*;

public class Main_bj_3190_뱀 {

    static int N, K, tailX, tailY, sec;
    static int map[][], save[][];
    static Queue<Node> q;

    static int di[] = {0, +1, 0, -1}; // 0 : right , 1 : down, 2 : left, 3 : up
    static int dj[] = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_3190.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        save = new int[N][N];

        map[0][0] = 2; // 처음 뱀 위치

        st = new StringTokenizer(br.readLine(), " ");
        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) { // 사과 위치 배열에 저장
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x - 1][y - 1] = 1;
        }

        q = new LinkedList<>();

        st = new StringTokenizer(br.readLine(), " ");
        int L = Integer.parseInt(st.nextToken());

        for (int i = 0; i < L; i++) { // 이동방향 저장
            st = new StringTokenizer(br.readLine(), " ");
            int X = Integer.parseInt(st.nextToken());
            String C = st.nextToken();
            q.offer(new Node(X, C));
        }

        tailX = 0;
        tailY = 0;

        sec = 0;

        dfs(0, 0, 0);

        System.out.print(sec);
    }

    static void dfs(int x, int y, int dir) {
        sec += 1;


        int dx = x + di[dir];
        int dy = y + dj[dir];

        if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
            save[dx][dy] = dir; // 몸통별 이동방향 저장
            if (map[dx][dy] == 1) { // 사과가 있다면 몸이 늘어남
                // 머리이동
                map[dx][dy] = 2;

            } else if (map[dx][dy] == 0) { // 사과가 없어서 몸이 안늘어남 꼬리 변경
                // 머리이동
                map[dx][dy] = 2;

                // 꼬리 위치 변경
                int d = save[tailX][tailY];

                map[tailX][tailY] = 0;
                save[tailX][tailY] = 0;

                tailX += di[d];
                tailY += dj[d];

            } else { // 자기 몸이랑 부딫힌 경우
                return;
            }
        } else {
            return;
        }

        if (!q.isEmpty() && sec == q.peek().sec) {
            if (q.peek().type.equals("D")) { // right
                dir = (dir + 1) % 4;
                save[dx][dy] = dir;
            } else if (q.peek().type.equals("L")) { // left
                dir = (dir + 3) % 4;
                save[dx][dy] = dir;
            }
            q.poll();
        }

        dfs(dx, dy, dir);

    }

    static class Node {

        int sec;
        String type;

        Node(int sec, String type) {

            this.sec = sec;
            this.type = type;
        }
    }
}