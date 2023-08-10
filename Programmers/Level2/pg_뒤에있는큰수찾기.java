package d2308;

import java.util.*;

class 뒤에있는큰수찾기Solution {
    public int[] solution(int[] numbers) {
        int N = numbers.length;
        int[] answer = new int[N];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            answer[i] = -1;

            if (stack.isEmpty()) { // 스택이 비어있으면 현재 인덱스 삽입
                stack.add(i);
            } else {
                while (!stack.isEmpty()) {
                    int index = stack.peek();
                    int value = numbers[index];

                    if (value < numbers[i]) { // 현재 인덱스 값이 이전 인덱스 보다 큰지 확인
                        answer[index] = numbers[i];
                        stack.pop();
                    } else break;
                }
                stack.add(i);
            }
        }
        return answer;
    }
}

public class pg_뒤에있는큰수찾기 {

    public static void main(String[] args) {
        뒤에있는큰수찾기Solution solution = new 뒤에있는큰수찾기Solution();
        int[] numbers = {9, 1, 5, 3, 6, 2};
        solution.solution(numbers);
    }
}
