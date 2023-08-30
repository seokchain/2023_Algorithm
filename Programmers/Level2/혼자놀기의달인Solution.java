package d2308;

import java.util.*;

class 혼자놀기의달인Solution {
    public int solution(int[] cards) {
        int answer = 0;

        int N = cards.length;
        boolean v[] = new boolean[N];
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (v[i]) continue;

            v[i] = true;
            int size = 1;
            int num = cards[i] - 1;

            while (true) {
                if (!v[num]) {
                    v[num] = true;
                    num = cards[num] - 1;
                    size++;
                } else break;
            }
            list.add(size);
        }

        Collections.sort(list, Collections.reverseOrder());

        if (list.size() < 2) answer = 0;
        else answer = list.get(0) * list.get(1);

        return answer;
    }
}