package d2308;

import java.util.*;

class 마법의엘레베이터Solution {
    public int solution(int storey) {
        int N = String.valueOf(storey).length();
        int s = storey;
        int answer = 0;

        int arr[] = new int[N + 1];

        for (int i = N - 1; i >= 0; i--) {
            arr[i] = (int) (s / Math.pow(10, i));
            s -= arr[i] * Math.pow(10, i);
        }

        for (int i = 0; i <= N; i++) {
            // 마지막 숫자
            if (i == N) {
                answer += arr[i];
                break;
            }

            // 5보다 큰 경우
            if (arr[i] > 5) {
                answer += 10 - arr[i];
                arr[i + 1]++;
            }

            // 5인경우 2가지로 나눠진다.
            else if (arr[i] == 5) {
                if (arr[i + 1] >= 5) {
                    answer += 10 - arr[i];
                    arr[i + 1]++;
                } else answer += arr[i];
            }
            // 5보다 작은경우
            else answer += arr[i];
        }

        return answer;
    }
}