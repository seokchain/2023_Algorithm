package d2310;

import java.io.*;
import java.util.*;s

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];

        int left = 0;
        int right = 0;

        int curSum = sequence[0];
        int n = sequence.length;

        ArrayList<Node> list = new ArrayList<>();

        while (true) {

            if (curSum == k) {
                int size = right - left;
                list.add(new Node(size, left, right));
            }

            if (left == n && right == n) {
                break;
            }

            if (curSum <= k && right < n) {
                right++;
                if (right < n) curSum = curSum + sequence[right];

            } else {
                curSum = curSum - sequence[left];
                left++;
            }
        }

        Collections.sort(list);

        answer[0] = list.get(0).left;
        answer[1] = list.get(0).right;


        return answer;
    }

    static class Node implements Comparable<Node> {
        int size, left, right;

        Node(int size, int left, int right) {
            this.size = size;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            if (this.size == o.size) {
                return this.left - o.left;
            } else return this.size - o.size;
        }
    }
}

public class pg_연속된부분수열의합 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] sequence = {1, 2, 3, 4, 5};
        int k = 7;
        solution.solution(sequence, k);
    }
}
