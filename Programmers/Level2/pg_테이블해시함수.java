package d2308;

import java.util.*;

class 테이블해시함수Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) return o2[0] - o1[0];
            else return o1[col - 1] - o2[col - 1];
        });

        for (int i = row_begin - 1; i <= row_end - 1; i++) {
            int sum = 0;
            for (int j = 0; j < data[0].length; j++) {
                sum += data[i][j] % (i + 1);
            }
            answer ^= sum;
        }

        return answer;
    }
}
