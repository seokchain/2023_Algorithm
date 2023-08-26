package d2308;

import java.util.*;

public class 롤케이크자르기Solution {
    public int solution(int[] topping) {
        int answer = 0;

        int N = topping.length;

        int left[] = new int[N];
        int right[] = new int[N];

        Set<Integer> leftSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            leftSet.add(topping[i]);
            left[i] = leftSet.size();
        }

        Set<Integer> rightSet = new HashSet<>();
        for (int i = N - 1; i >= 0; i--) {
            rightSet.add(topping[i]);
            right[i] = rightSet.size();
        }

        for (int i = 0; i < N - 1; i++) {
            if (left[i] == right[i + 1]) answer++;
        }

        return answer;
    }
}
