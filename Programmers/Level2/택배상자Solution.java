package d2308;

import java.util.*;

public class 택배상자Solution {

    public int solution(int[] order) {
        int answer = 0;
        int box = 1;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < order.length; i++) {

            boolean able = false;

            if (order[i] == box) {
                box++;
                able = true;
            } else if (!stack.isEmpty() && order[i] == stack.peek()) {
                stack.pop();
                able = true;
            } else {
                while (box <= order.length) {
                    if (order[i] == box) {
                        box++;
                        able = true;
                        break;
                    }
                    stack.add(box);
                    box++;
                }
            }

            if (!able) break;
            else answer++;

        }
        return answer;
    }
}
