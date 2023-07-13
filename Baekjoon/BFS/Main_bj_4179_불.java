package d2307;

import java.io.*;
import java.util.*;

public class Main_bj_4179_불 {

    static int R, C;
    static char map[][];
    static boolean v[][];
    static int ni[] = {1, -1, 0, 0};
    static int nj[] = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int count = 0;

        map = new char[R][C];
        v = new boolean[R][C];

        Queue<Man> man_q = new LinkedList<>();
        Queue<Fire> fire_q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') {
                    v[i][j] = true;
                    man_q.add(new Man(i, j, 0));
                } else if (map[i][j] == 'F') {
                    fire_q.add(new Fire(i, j));
                }
            }
        }

        int flag = 0;

        here:
        while (!man_q.isEmpty()) {

            Man man = man_q.poll();

            if (map[man.x][man.y] == 'F') continue;

            for (int d = 0; d < 4; d++) {
                int di = man.x + ni[d];
                int dj = man.y + nj[d];

                if (di < 0 || di >= R || dj < 0 || dj >= C) {
                    count = man.cnt + 1;
                    break here;
                }

                if (moveCheck(di, dj) && !v[di][dj]) {
                    v[di][dj] = true;
                    man_q.add(new Man(di, dj, man.cnt + 1));
                }
            }

            if (man.cnt == flag) {

                int fireN = fire_q.size();

                for (int i = 0; i < fireN; i++) {

                    Fire fire = fire_q.poll();

                    for (int d = 0; d < 4; d++) {
                        int di = fire.x + ni[d];
                        int dj = fire.y + nj[d];

                        if (di >= 0 && di < R && dj >= 0 && dj < C) {
                            if (map[di][dj] != '#' && map[di][dj] != 'F') {
                                map[di][dj] = 'F';
                                fire_q.add(new Fire(di, dj));
                            }
                        }
                    }
                }
                flag = flag + 1;
            }
        }

        if (count == 0) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(count);
        }
    }

    static boolean moveCheck(int x, int y) {
        if (map[x][y] != 'F' && map[x][y] != '#') return true;
        else return false;
    }

    static class Man {
        int x;
        int y;
        int cnt;

        Man(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static class Fire {
        int x;
        int y;

        Fire(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}