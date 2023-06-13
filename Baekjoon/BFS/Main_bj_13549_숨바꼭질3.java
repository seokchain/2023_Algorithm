package d2306;

import java.io.*;
import java.util.*;

public class Main_bj_13549_숨바꼭질3 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        LinkedList<Node> q = new LinkedList<>();
        boolean v[] = new boolean[100001];

        v[N] = true;
        q.offer(new Node(N, 0));

        while (!q.isEmpty()) {

            Node node = q.poll();

            int now = node.num;

            if (node.num == K) {
                System.out.println(node.sec);
                break;
            }
            if (rangeCheck(now * 2) && !v[now * 2]) {
                v[now * 2] = true;
                q.addFirst(new Node(now * 2, node.sec));
            }
            if (rangeCheck(now + 1) && !v[now + 1]) {
                v[now + 1] = true;
                q.offer(new Node(now + 1, node.sec + 1));
            }
            if (rangeCheck(now - 1) && !v[now - 1]) {
                v[now - 1] = true;
                q.offer(new Node(now - 1, node.sec + 1));
            }

        }
    }

    static boolean rangeCheck(int num) {
        if (num >= 0 && num <= 100000) return true;
        else return false;
    }

    static class Node {
        int num;
        int sec;

        Node(int num, int sec) {
            this.num = num;
            this.sec = sec;
        }
    }

}
