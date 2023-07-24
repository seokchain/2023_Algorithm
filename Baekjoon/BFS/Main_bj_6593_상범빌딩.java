package d2307;

import java.io.*;
import java.util.*;

public class Main_bj_6593_상범빌딩 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int di[] = {0, 0, 1, -1, 0, 0};
        int dj[] = {1, -1, 0, 0, 0, 0};
        int dz[] = {0, 0, 0, 0, -1, 1};

        while (true) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) break;

            char map[][][] = new char[L][R][C];

            Queue<Node> q = new LinkedList<>();
            boolean v[][][] = new boolean[L][R][C];

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String str = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = str.charAt(k);
                        if (map[i][j][k] == 'S') {
                            q.offer(new Node(i, j, k, 0));
                            v[i][j][k] = true;
                        }
                    }
                }
                br.readLine();
            }

            int cnt = 0;

            while (!q.isEmpty()) {

                Node node = q.poll();

                if(map[node.z][node.x][node.y] == 'E'){
                    cnt = node.cnt;
                    break;
                }

                for (int d = 0; d < 6; d++) {
                    int nz = dz[d] + node.z;
                    int ni = di[d] + node.x;
                    int nj = dj[d] + node.y;
                    if (nz < L && nz >= 0 && ni < R && ni >= 0 && nj < C && nj >= 0 && !v[nz][ni][nj]) {
                        if (map[nz][ni][nj] != '#'){
                            v[nz][ni][nj] = true;
                            q.offer(new Node(nz, ni, nj, node.cnt + 1));
                        }
                    }
                }

            }

            if (cnt == 0) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + cnt + " minute(s).");
            }
        }
    }

    static class Node {
        int z;
        int x;
        int y;
        int cnt;

        Node(int z, int x, int y, int cnt) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}