package d2307;

import java.io.*;
import java.util.*;

public class Main_bj_1753_최단경로 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int V = Integer.parseInt(st.nextToken()); // 정점
        int E = Integer.parseInt(st.nextToken()); // 간선

        int K = Integer.parseInt(br.readLine());  // 출발점

        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int go = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(go).add(new Node(end, w));
        }

        int dist[] = new int[V + 1];
        for (int i = 0; i < V + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.w, o2.w)));
        pq.offer(new Node(K, 0));
        dist[K] = 0;

        while (!pq.isEmpty()) {

            Node curNode = pq.poll();
            if (curNode.w > dist[curNode.idx]) {
                continue;
            }

            for (int i = 0; i < list.get(curNode.idx).size(); i++) {
                Node nxtNode = list.get(curNode.idx).get(i);
                if (dist[nxtNode.idx] > curNode.w + nxtNode.w) {
                    dist[nxtNode.idx] = curNode.w + nxtNode.w;
                    pq.offer(new Node(nxtNode.idx, dist[nxtNode.idx]));
                }
            }
        }

        for (int i = 1; i < V + 1; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                sb.append(dist[i]).append("\n");
            } else {
                sb.append("INF").append("\n");
            }
        }

        System.out.println(sb);
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