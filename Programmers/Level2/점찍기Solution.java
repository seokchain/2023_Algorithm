package d2308;

public class 점찍기Solution {
    public long solution(long k, long d) {
        long answer = 0;

        for (long i = 0; i <= d; i = i + k) {
            long bk = (d * d) - (i * i);
            long b = (long) Math.sqrt(bk);
            answer += (b / k) + 1;
        }
        return answer;
    }
}
