package d2306;

import java.util.*;
import java.io.*;

public class Main_성적평가 {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Node arr[][] = new Node[4][N];

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = new Node(j, Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < N; i++) {
            int sum = arr[0][i].score + arr[1][i].score + arr[2][i].score;
            arr[3][i] = new Node(i, sum);
        }

        for (Node[] node : arr) {
            Arrays.sort(node, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o2.score - o1.score;
                }
            });
        }

        int rank[][] = new int[4][N];

        for (int i = 0; i < 4; i++) {
            int rank_cnt = 0;
            int same_cnt = 0;
            int num = -1;
            for (int j = 0; j < N; j++) {
                if (arr[i][j].score == num) {
                    same_cnt = same_cnt + 1;
                    rank[i][arr[i][j].id] = rank_cnt;
                } else {
                    num = arr[i][j].score;
                    rank_cnt = rank_cnt + 1 + same_cnt;
                    same_cnt = 0;
                    rank[i][arr[i][j].id] = rank_cnt;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(rank[i][j] + " ");
            }
            System.out.println();
        }

    }

    static class Node {
        int id, score;

        Node(int id, int score) {
            this.id = id;
            this.score = score;
        }
    }
}