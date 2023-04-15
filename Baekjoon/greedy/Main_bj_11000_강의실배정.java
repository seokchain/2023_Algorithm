package d2304;

import java.io.*;
import java.util.*;

public class Main_bj_11000_강의실배정 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_11000.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());

        Study study[] = new Study[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            study[i] = new Study(start, end);
        }

        Arrays.sort(study, new Comparator<Study>() {
            @Override
            public int compare(Study o1, Study o2) {

                if (o1.start == o2.start) {
                    return o1.end - o2.end;
                }

                return o1.start - o2.start;
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(study[0].end);

        for (int i = 1; i < N; i++) {
            if (pq.peek() <= study[i].start) {
                pq.poll();
                pq.offer(study[i].end);
            } else {
                pq.offer(study[i].end);
            }
        }
        System.out.println(pq.size());
    }

    static class Study {

        int start;
        int end;

        Study(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}