package d2307;

import java.io.*;
import java.util.*;

public class Main_bj_16397_탈출 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        int ans = -1;

        Queue<Node> q = new LinkedList<>();
        boolean v[] = new boolean[100000];

        q.offer(new Node(N, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            int num = node.num;
            int cnt = node.cnt;

            if (num == G) {
                ans = cnt;
                break;
            }

            if (cnt >= T) continue;

            int numA = num + 1;
            if (numA < 100000 && !v[numA]) {
                v[numA] = true;
                q.offer(new Node(numA, cnt + 1));
            }

            int numB = btnB(num);
            if (numB > 0 && numB < 100000 && !v[numB]) {
                v[numB] = true;
                q.offer(new Node(numB, cnt + 1));
            }
        }

        if (ans != -1) System.out.println(ans);
        else System.out.println("ANG");
    }

    static int btnB(int num) {

        int tmp = num * 2;

        if (tmp >= 100000) {
            return tmp;
        } else if (tmp >= 10000) {
            tmp = tmp - 10000;
        } else if (tmp >= 1000) {
            tmp = tmp - 1000;
        } else if (tmp >= 100) {
            tmp = tmp - 100;
        } else if (tmp >= 10) {
            tmp = tmp - 10;
        } else if (tmp >= 0) {
            tmp = tmp - 1;
        }

        return tmp;
    }

    static class Node {
        int num;
        int cnt;

        Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
