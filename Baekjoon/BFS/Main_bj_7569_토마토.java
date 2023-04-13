package d2304;

import java.io.*;
import java.util.*;

public class Main_bj_7569_토마토 {

    static int N, M, H, badTomato, day;
    static int map[][][];
    static boolean v[][][];
    static Queue<Node> q;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_7569.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][M][N];
        v = new boolean[H][M][N];

        day = 0;
        badTomato = 0; // 덜익은 토마토

        q = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < N; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if(map[i][j][k] == 1){
                        q.offer(new Node(i, j, k, 0));
                        v[i][j][k] = true;
                    } else if (map[i][j][k] == 0) badTomato++;
                }
            }
        }

        if (badTomato == 0) {
            day = 0;
        } else {
            spread();
        }

        if(badTomato > 0) day = -1;

        System.out.println(day);
    }

    static void spread() {

        int di[] = {1, -1};
        int dj[] = {0, 0, 1, -1};
        int dk[] = {1, -1, 0, 0};

        while(!q.isEmpty()){

            Node node = q.poll();

            int i = node.i;
            int j = node.j;
            int k = node.k;
            int cnt = node.cnt;

            day = Math.max(cnt, day);

            for (int d = 0; d < 4; d++) {
                int nj = j + dj[d];
                int nk = k + dk[d];
                if (nj < M && nj >= 0 && nk < N && nk >= 0 && !v[i][nj][nk]) {
                    if (map[i][nj][nk] == 0) {
                        v[i][nj][nk] = true;
                        map[i][nj][nk] = 1;
                        badTomato--;
                        q.offer(new Node(i, nj, nk, cnt+1));
                    }
                }
            }

            for (int d = 0; d < 2; d++) {
                int ni = i + di[d];
                if (ni < H && ni >= 0 && !v[ni][j][k]) {
                    if (map[ni][j][k] == 0) {
                        v[ni][j][k] = true;
                        map[ni][j][k] = 1;
                        badTomato--;
                        q.offer(new Node(ni,j,k, cnt+1));
                    }
                }
            }
        }
    }

    static class Node{
        int i;
        int j;
        int k;
        int cnt;

        Node(int i, int j, int k, int cnt){
            this.i = i;
            this.j = j;
            this.k = k;
            this.cnt = cnt;
        }
    }
}