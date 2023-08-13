package d2308;

class 시소짝꿍Solution {
    public long solution(int[] weights) {
        long answer = 0;

        long arr[] = new long[1001];

        for (int i = 0; i < weights.length; i++) {
            arr[weights[i]]++;
        }

        for (int i = 100; i < 1001; i++) {

            if (arr[i] == 0) continue;

            for (long j = arr[i]; j > 1; j--) {
                answer += j - 1;
            }

            if (2 * i <= 1000) answer += arr[i] * arr[2 * i];
            if ((3 * i) % 2 == 0 && 3 * i / 2 <= 1000) answer += arr[i] * arr[3 * i / 2];
            if ((4 * i) % 3 == 0 && 4 * i / 3 <= 1000) answer += arr[i] * arr[4 * i / 3];
        }
        return answer;
    }
}

public class pg_시소짝꿍 {
    public static void main(String[] args) {
        시소짝꿍Solution solution = new 시소짝꿍Solution();
        int weights[] = {100, 180, 360, 100, 270};
        solution.solution(weights);
    }
}