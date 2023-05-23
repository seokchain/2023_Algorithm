package d2305;

import java.io.*;
import java.util.*;

/*
1. 집에 있는 모든 온풍기에서 바람이 나옴
2. 온도가 조절됨
3. 온도가 1 이상인 가장 바깥쪽 칸의 온도 감소
4. 초콜릿을 하나 먹는다.
5. 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사
 */
public class Main_bj_23289_온풍기안녕 {

    static int R, C, K, map[][];
    static ArrayList<Integer> wallList[][];

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_23289.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R + 1][C + 1];
        ArrayList<Node> checkList = new ArrayList<>();
        ArrayList<Heater> HeaterList = new ArrayList<>();

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= C; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 5) {
                    checkList.add(new Node(i, j));
                } else if (num > 0) {
                    HeaterList.add(new Heater(i, j, num));
                }
            }
        }

        wallList = new ArrayList[R + 1][C + 1];
        for (int i = 0; i <= R; i++) {
            for (int j = 0; j <= C; j++) {
                wallList[i][j] = new ArrayList<>();
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int W = Integer.parseInt(st.nextToken());
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            wallList[x][y].add(t);
        }

        int choco = 0;
        // 온풍기 바람
        while (true) {
            for (Heater heater : HeaterList) {
                heaterWind(heater.r, heater.c, heater.type);
            }
            // 온도 조절
            tempControl();
            // 온도가 1 이상인 가장 바깥쪽 칸의 온도 감소
            tempMinus();
            // 초콜릿 먹기
            choco = choco + 1;
            if (choco == 101) break;
            // 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사
            int num = checkList.size();
            for (Node node : checkList) {
                if (map[node.r][node.c] >= K) num = num - 1;
            }
            if (num == 0) break;
            else continue;
        }
        System.out.println(choco);
    }

    static void heaterWind(int r, int c, int type) {
        Queue<Wind> q = new LinkedList<>();
        boolean v[][] = new boolean[R + 1][C + 1];

        if (type == 1) {
            if (!chkRange(r, c + 1)) return;
            q.offer(new Wind(r, c + 1, 5));

            while (!q.isEmpty()) {
                Wind wind = q.poll();
                int wr = wind.r;
                int wc = wind.c;
                int num = wind.num;
                if (num == 0) return;

                if (chkRange(wr - 1, wc + 1) && !v[wr - 1][wc + 1] && !wallList[wr][wc].contains(0) && !wallList[wr - 1][wc].contains(1)) {
                    v[wr - 1][wc + 1] = true;
                    q.offer(new Wind(wr - 1, wc + 1, num - 1));
                }
                if (chkRange(wr, wc + 1) && !v[wr][wc + 1] && !wallList[wr][wc].contains(1)) {
                    v[wr][wc + 1] = true;
                    q.offer(new Wind(wr, wc + 1, num - 1));
                }
                if (chkRange(wr + 1, wc + 1) && !v[wr + 1][wc + 1] && !wallList[wr + 1][wc].contains(0) && !wallList[wr + 1][wc].contains(1)) {
                    v[wr + 1][wc + 1] = true;
                    q.offer(new Wind(wr + 1, wc + 1, num - 1));
                }

                map[wr][wc] = map[wr][wc] + num;
            }
        } else if (type == 2) {
            if (!chkRange(r, c - 1)) return;
            q.offer(new Wind(r, c - 1, 5));

            while (!q.isEmpty()) {
                Wind wind = q.poll();
                int wr = wind.r;
                int wc = wind.c;
                int num = wind.num;
                if (num == 0) return;

                if (chkRange(wr - 1, wc - 1) && !v[wr - 1][wc - 1] && !wallList[wr][wc].contains(0) && !wallList[wr - 1][wc - 1].contains(1)) {
                    v[wr - 1][wc - 1] = true;
                    q.offer(new Wind(wr - 1, wc - 1, num - 1));
                }
                if (chkRange(wr, wc - 1) && !v[wr][wc - 1] && !wallList[wr][wc - 1].contains(1)) {
                    v[wr][wc - 1] = true;
                    q.offer(new Wind(wr, wc - 1, num - 1));
                }
                if (chkRange(wr + 1, wc - 1) && !v[wr + 1][wc - 1] && !wallList[wr + 1][wc].contains(0) && !wallList[wr + 1][wc - 1].contains(1)) {
                    v[wr + 1][wc - 1] = true;
                    q.offer(new Wind(wr + 1, wc - 1, num - 1));
                }
                map[wr][wc] = map[wr][wc] + num;
            }
        } else if (type == 3) {
            if (!chkRange(r - 1, c)) return;
            q.offer(new Wind(r - 1, c, 5));

            while (!q.isEmpty()) {
                Wind wind = q.poll();
                int wr = wind.r;
                int wc = wind.c;
                int num = wind.num;
                if (num == 0) return;

                if (chkRange(wr - 1, wc - 1) && !v[wr - 1][wc - 1] && !wallList[wr][wc - 1].contains(1) && !wallList[wr][wc - 1].contains(0)) {
                    v[wr - 1][wc - 1] = true;
                    q.offer(new Wind(wr - 1, wc - 1, num - 1));
                }
                if (chkRange(wr - 1, wc) && !v[wr - 1][wc] && !wallList[wr][wc].contains(0)) {
                    v[wr - 1][wc] = true;
                    q.offer(new Wind(wr - 1, wc, num - 1));
                }
                if (chkRange(wr - 1, wc + 1) && !v[wr - 1][wc + 1] && !wallList[wr][wc].contains(1) && !wallList[wr][wc + 1].contains(0)) {
                    v[wr - 1][wc + 1] = true;
                    q.offer(new Wind(wr - 1, wc + 1, num - 1));
                }
                map[wr][wc] = map[wr][wc] + num;
            }
        } else if (type == 4) {
            if (!chkRange(r + 1, c)) return;
            q.offer(new Wind(r + 1, c, 5));

            while (!q.isEmpty()) {
                Wind wind = q.poll();
                int wr = wind.r;
                int wc = wind.c;
                int num = wind.num;
                if (num == 0) return;

                if (chkRange(wr + 1, wc - 1) && !v[wr + 1][wc - 1] && !wallList[wr][wc - 1].contains(1) && !wallList[wr + 1][wc - 1].contains(0)) {
                    v[wr + 1][wc - 1] = true;
                    q.offer(new Wind(wr + 1, wc - 1, num - 1));
                }
                if (chkRange(wr + 1, wc) && !v[wr + 1][wc] && !wallList[wr + 1][wc].contains(0)) {
                    v[wr + 1][wc] = true;
                    q.offer(new Wind(wr + 1, wc, num - 1));
                }
                if (chkRange(wr + 1, wc + 1) && !v[wr + 1][wc + 1] && !wallList[wr][wc].contains(1) && !wallList[wr + 1][wc + 1].contains(0)) {
                    v[wr + 1][wc + 1] = true;
                    q.offer(new Wind(wr + 1, wc + 1, num - 1));
                }
                map[wr][wc] = map[wr][wc] + num;
            }
        }
    }

    static void tempControl() {
        int di[] = {0, 0, 1, -1}; // 우, 좌, 하, 상
        int dj[] = {1, -1, 0, 0};
        int tmpMap[][] = new int[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                tmpMap[i][j] = map[i][j];
            }
        }

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                int temp = map[i][j];
                for (int d = 0; d < 4; d++) {
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    boolean wall = false;

                    if (chkRange(ni, nj)) {
                        if (d == 0) { // 우
                            if (wallList[i][j].contains(1)) wall = true;
                        } else if (d == 1) { // 좌
                            if (wallList[i][j - 1].contains(1)) wall = true;
                        } else if (d == 2) { // 하
                            if (wallList[i + 1][j].contains(0)) wall = true;
                        } else if (d == 3) { // 상
                            if (wallList[i][j].contains(0)) wall = true;
                        }
                        if (map[ni][nj] > temp && !wall) {
                            int num = (map[ni][nj] - temp) / 4;
                            tmpMap[i][j] = tmpMap[i][j] + num;
                            tmpMap[ni][nj] = tmpMap[ni][nj] - num;
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                map[i][j] = tmpMap[i][j];
            }
        }

    }

    static void tempMinus() {

        for (int i = 2; i < C; i++) {
            if (map[1][i] != 0) map[1][i] = map[1][i] - 1;
            if (map[R][i] != 0) map[R][i] = map[R][i] - 1;
        }

        for (int i = 2; i < R; i++) {
            if (map[i][1] != 0) map[i][1] = map[i][1] - 1;
            if (map[i][C] != 0) map[i][C] = map[i][C] - 1;
        }

        if (map[1][1] != 0) map[1][1] = map[1][1] - 1;
        if (map[1][C] != 0) map[1][C] = map[1][C] - 1;
        if (map[R][1] != 0) map[R][1] = map[R][1] - 1;
        if (map[R][C] != 0) map[R][C] = map[R][C] - 1;

    }

    static boolean chkRange(int r, int c) {
        if (r <= R && r > 0 && c <= C && c > 0) return true;
        else return false;
    }

    static class Node {
        int r;
        int c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Heater {
        int r;
        int c;
        int type;

        Heater(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }

    static class Wind {
        int r;
        int c;
        int num;

        Wind(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }
}