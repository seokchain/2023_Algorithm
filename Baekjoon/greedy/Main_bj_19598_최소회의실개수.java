package d2306;

import java.io.*;
import java.util.*;

public class Main_bj_19598_최소회의실개수 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ArrayList<Node> list = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Node(start, end));
        }

        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.start == o2.start) return o1.end - o2.end;
                return o1.start - o2.start;
            }
        });

        int cnt = 1; // 회의실 개수
        pq.add(list.get(0).end);

        for (int i = 1; i < N; i++) {

            if (list.get(i).start >= pq.peek()) {
                pq.poll();
                pq.add(list.get(i).end);
            } else {
                cnt = cnt + 1;
                pq.add(list.get(i).end);
            }
        }

        System.out.println(cnt);

    }

    static class Node {
        int start, end;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}