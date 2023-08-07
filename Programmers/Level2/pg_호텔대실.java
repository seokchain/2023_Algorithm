package d2308;

import java.io.*;
import java.util.*;

class 호텔대실Solution {
    public int solution(String[][] book_time) {
        int answer = 0;

        Node[] arr = new Node[book_time.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < book_time.length; i++) {

            String st[] = book_time[i][0].split(":");
            String ed[] = book_time[i][1].split(":");

            int start = (Integer.parseInt(st[0]) * 60) + Integer.parseInt(st[1]);
            int end = (Integer.parseInt(ed[0]) * 60) + Integer.parseInt(ed[1]);

            arr[i] = new Node(start, end);
        }

        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            if (pq.isEmpty()) pq.offer(arr[i].end);
            else {
                if (arr[i].start >= pq.peek() + 10) {
                    pq.poll();
                    pq.offer(arr[i].end);
                } else {
                    pq.offer(arr[i].end);
                }
            }
        }

        answer = pq.size();
        return answer;
    }

    static class Node implements Comparable<Node> {

        int start, end;
        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            if (this.start == o.start) {
                return this.end - o.end;
            } else return this.start - o.start;
        }
    }
}

public class pg_호텔대실 {

    public static void main(String[] args) {
        호텔대실Solution solution = new 호텔대실Solution();
        String[][] book_time = {{"15:00", "17:00"},
                                {"16:40", "18:20"},
                                {"14:20", "15:20"},
                                {"14:10", "19:20"},
                                {"18:20", "21:20"}};
        solution.solution(book_time);
    }
}