package d230402;

import java.io.*;
import java.util.*;

class Solution {
    static int di[] = {0, 0, 1, -1};
    static int dj[] = {1, -1, 0, 0};
    static boolean v[][];
    static int n, m;

    public int solution(int[][] maps) {

        n = maps.length;
        m = maps[0].length;

        v = new boolean[n][m];

        return dfs(0, 0, maps);
    }

    public int dfs(int x, int y, int[][] maps) {

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 1));
        v[x][y] = true;

        while (!q.isEmpty()) {

            Node node = q.poll();
            if (node.i == n - 1 && node.j == m - 1) return node.cost;

            for (int d = 0; d < 4; d++) {
                int ni = node.i + di[d];
                int nj = node.j + dj[d];
                if (ni < n && nj < m && ni >= 0 && nj >= 0 && maps[ni][nj] != 0 && !v[ni][nj]) {
                    v[ni][nj] = true;
                    q.offer(new Node(ni, nj, node.cost + 1));
                }
            }
        }
        return -1;
    }

    public class Node {

        int i;
        int j;
        int cost;

        public Node(int i, int j, int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
    }
}