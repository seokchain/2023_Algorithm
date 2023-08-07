package d2308;

import java.io.*;
import java.util.*;

class 미로탈출Solution {

    static int R, C, answer;
    static char map[][];
    static Queue<Node> q;
    static boolean v[][];

    public int solution(String[] maps) {
        answer = 0;
        R = maps.length;
        C = maps[0].length();

        map = new char[R][C];
        q = new LinkedList<>();
        v = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = maps[i];
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') q.offer(new Node(i, j, 0));
            }
        }

        if (bfs('L')) {
            v = new boolean[R][C];
            if (!bfs('E')) answer = -1;
        } else answer = -1;

        return answer;
    }

    static boolean bfs(char ch) {

        int di[] = {0, 0, 1, -1};
        int dj[] = {1, -1, 0, 0};

        while (!q.isEmpty()) {

            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int cnt = node.cnt;

            if (map[x][y] == ch) {
                answer = answer + cnt;
                q.clear();
                q.offer(new Node(x, y, 0));
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int dx = x + di[d];
                int dy = y + dj[d];
                if (dx >= 0 && dx < R && dy >= 0 && dy < C
                        && map[dx][dy] != 'X'
                        && !v[dx][dy]) {
                    v[dx][dy] = true;
                    q.offer(new Node(dx, dy, cnt + 1));
                }
            }
        }
        return false;
    }

    static class Node {
        int x, y, cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}

public class pg_미로탈출 {

    public static void main(String[] args) {
        미로탈출Solution solution = new 미로탈출Solution();
        String[] maps = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
        solution.solution(maps);
    }
}