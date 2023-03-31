package d230329;

import java.io.*;
import java.util.*;

public class Main_bj_15683_감시 {

    static int N, M, cctvSize, min, dir[], map[][], tmpMap[][];
    static ArrayList<Node> cctv;

    static int di[] = {0, 1, 0, -1};
    static int dj[] = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_15683.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        tmpMap = new int[N][M];

        cctv = new ArrayList<Node>();
        min = 64;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // cctv 리스트에 추가
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctv.add(new Node(map[i][j], i, j));
                }
            }
        }

        cctvSize = cctv.size();
        dir = new int[cctvSize];
        per(0);
        System.out.println(min);
    }

    static void per(int cnt) {

        if (cnt == cctvSize) {
            // 사각지대 구하는 메소드
            copyMap();
            min = Math.min(chkMin(dir), min);
            return;
        }

        for (int i = 0; i < 4; i++) {
            dir[cnt] = i;
            per(cnt + 1);
        }
    }

    static int chkMin(int dir[]) {
        int count = 0;

        for (int i = 0; i < cctvSize; i++) {

            int type = cctv.get(i).type;
            int d = dir[i];

            if (type == 1) {
                chk(cctv.get(i), d);
            } else if (type == 2) {
                chk(cctv.get(i), d % 2);
                chk(cctv.get(i), d % 2 + 2);
            } else if (type == 3) {
                if (d == 0) {
                    chk(cctv.get(i), 0);
                    chk(cctv.get(i), 3);
                } else {
                    chk(cctv.get(i), d);
                    chk(cctv.get(i), d - 1);
                }
            } else if (type == 4) {
                for (int j = 0; j < 4; j++) {
                    if (j == d) continue;
                    chk(cctv.get(i), j);
                }
            } else if (type == 5) {
                for (int j = 0; j < 4; j++) {
                    chk(cctv.get(i), j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmpMap[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    static void chk(Node node, int dir) {

        int x = node.x;
        int y = node.y;

        for (int j = 0; j < M; j++) {
            x = x + di[dir];
            y = y + dj[dir];
            if (x < N && x >= 0 && y < M && y >= 0 && tmpMap[x][y] != 6) {
                tmpMap[x][y] = 9;
            } else break;
        }
    }

    static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpMap[i][j] = map[i][j];
            }
        }
    }

    static class Node {

        int type;
        int x;
        int y;

        Node(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }
}
