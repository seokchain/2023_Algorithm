package d2306;

import java.io.*;
import java.util.*;

public class Main_bj_16637_괄호추가하기 {

    static int max;
    static ArrayList<Integer> num;
    static ArrayList<Character> arr;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        num = new ArrayList<>();
        arr = new ArrayList<>();

        String str = br.readLine();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) num.add(str.charAt(i) - '0');
            else arr.add(str.charAt(i));
        }

        max = Integer.MIN_VALUE;
        dfs(0, num.get(0));

        System.out.println(max);
    }

    static void dfs(int cnt, int now) {

        if (cnt >= arr.size()) {
            max = Math.max(max, now);
            return;
        }

        // 괄호 안치는 경우
        int res = cal(cnt, now, num.get(cnt + 1));
        dfs(cnt + 1, res);

        // 괄호 치는 경우 ( 다음 식이 있는 경우 )
        if (cnt + 1 < arr.size()) {
            int nextRes = cal(cnt + 1, num.get(cnt + 1), num.get(cnt + 2));
            int nowRes = cal(cnt, now, nextRes);
            dfs(cnt + 2, nowRes);
        }
    }

    static int cal(int cnt, int now, int nextNum) {
        char sign = arr.get(cnt);
        if (sign == '+') {
            return now + nextNum;
        } else if (sign == '-') {
            return now - nextNum;
        } else {
            return now * nextNum;
        }
    }

}
