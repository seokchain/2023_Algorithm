package d230401;

import java.io.*;
import java.util.*;

class Solution {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int sum = 0;
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < truck_weights.length; i++) {

            while (true) {
                // 다리가 비어있는 경우
                if (q.isEmpty()) {
                    q.offer(truck_weights[i]);
                    sum += truck_weights[i];
                    answer++;
                    break;
                    // 다리가 가득 차 있는 경우
                } else if (q.size() == bridge_length) {
                    sum -= q.poll();
                    // 다리에 트럭이 있는 경우
                } else {
                    // 무게가 초과인경우
                    if (sum + truck_weights[i] > weight) {
                        q.offer(0);
                        answer++;
                        // 무게가 맞는 경우
                    } else {
                        q.offer(truck_weights[i]);
                        sum += truck_weights[i];
                        answer++;
                        break;
                    }

                }
            }

        }
        return answer + bridge_length;
    }
}
