package d2307;

import java.util.*;
import java.io.*;

public class Main_bj_1238_파티 {

    static int N, M, X, dist[], Xdist[];
    static ArrayList<ArrayList<Node>> list;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int go = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(go).add(new Node(end, w));
        }

        int max = 0;

        dijkstra(X);
        Xdist = dist;

        for (int i = 1; i < N + 1; i++) {
            if (i == X) continue;
            dijkstra(i);
            max = Math.max(max, Xdist[i] + dist[X]);
        }
        System.out.println(max);
    }

    static void dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
        dist = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {

            Node curNode = pq.poll();

            if (curNode.w > dist[curNode.idx]) {
                continue;
            }

            for (int i = 0; i < list.get(curNode.idx).size(); i++) {
                Node nxtNode = list.get(curNode.idx).get(i);
                if (dist[nxtNode.idx] > dist[curNode.idx] + nxtNode.w) {
                    dist[nxtNode.idx] = dist[curNode.idx] + nxtNode.w;
                    pq.offer(new Node(nxtNode.idx, dist[nxtNode.idx]));
                }
            }
        }
    }

    static class Node {
        int idx;
        int w;

        Node(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }
    }
}
