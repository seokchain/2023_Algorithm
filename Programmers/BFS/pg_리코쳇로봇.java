package d2310;

import java.io.*;
import java.util.*;

class 리코쳇로봇Solution {
    public int solution(String[] board) {
        int answer = -1;

        int ni[] = {1, -1, 0, 0};
        int nj[] = {0, 0, 1, -1};

        int R = board.length;
        int C = board[0].length();

        char map[][] = new char[R][C];
        boolean v[][] = new boolean[R][C];
        Queue<Node> q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String str = board[i];
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    q.offer(new Node(i, j, 0));
                }
            }
        }

        while (!q.isEmpty()) {

            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int cnt = node.cnt;

            if (map[x][y] == 'G') {
                answer = cnt;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int dx = x;
                int dy = y;
                while (true) {
                    dx = dx + ni[d];
                    dy = dy + nj[d];
                    if (dx >= 0 && dx < R && dy >= 0 && dy < C && map[dx][dy] != 'D') {
                        continue;
                    } else {
                        dx = dx - ni[d];
                        dy = dy - nj[d];
                        break;
                    }
                }
                if (!v[dx][dy]) {
                    v[dx][dy] = true;
                    q.offer(new Node(dx, dy, cnt + 1));
                }
            }
        }

        return answer;
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

public class pg_리코쳇로봇 {

    public static void main(String[] args) {
        리코쳇로봇Solution solution = new 리코쳇로봇Solution();
        String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        solution.solution(board);
    }
}
