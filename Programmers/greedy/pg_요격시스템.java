package d2310;

import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int end = targets[0][1];
        answer++;

        for (int i = 1; i < targets.length; i++) {
            int curStart = targets[i][0];
            int curEnd = targets[i][1];

            if(curStart >= end){
                answer++;
                end = curEnd;
            }
        }

        //System.out.println(answer);
        return answer;
    }
}

public class pg_요격시스템 {

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[][] targets = {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};
        solution.solution(targets);
    }
}