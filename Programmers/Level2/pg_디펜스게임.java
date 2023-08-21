package d2308;

import java.util.*;

class 디펜스게임Solution {
    public int solution(int n, int k, int[] enemy) {
        int round = 0;
        int num = n;
        int skill = k;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < enemy.length; i++) {

            int e = enemy[i];
            pq.offer(e);

            num -= e;
            if (num < 0) {
                if (skill == 0) {
                    break;
                } else if (skill != 0) {
                    num += pq.poll();
                    skill--;
                }
            }
            round++;
        }
        return round;
    }
}