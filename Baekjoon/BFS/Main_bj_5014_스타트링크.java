package d2306;

import java.io.*;
import java.util.*;

public class Main_bj_5014_스타트링크 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int F = Integer.parseInt(st.nextToken()); // 건물 높이
        int S = Integer.parseInt(st.nextToken()); // 현재 위치
        int G = Integer.parseInt(st.nextToken()); // 목표 위치
        int U = Integer.parseInt(st.nextToken()); // 위로 움직일 수 있는 크기
        int D = Integer.parseInt(st.nextToken()); // 아래로 움직일 수 있는 크기

        int cnt = -1;

        boolean v[] = new boolean[F+1];
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(S, 0));
        v[S] = true;

        while (!q.isEmpty()) {

            Node node = q.poll();

            if (node.state == G) {
                cnt = node.cnt;
                break;
            }

            if (U != 0 && node.state + U <= F && !v[node.state + U]) {
                v[node.state + U] = true;
                q.offer(new Node(node.state + U, node.cnt + 1));

            }
            if (D != 0 && node.state - D > 0 && !v[node.state - D]) {
                v[node.state - D] = true;
                q.offer(new Node(node.state - D, node.cnt + 1));
            }
        }

        if (cnt == -1) System.out.println("use the stairs");
        else System.out.println(cnt);

    }

    static class Node {
        int state;
        int cnt;

        Node(int state, int cnt) {
            this.state = state;
            this.cnt = cnt;
        }
    }
}
