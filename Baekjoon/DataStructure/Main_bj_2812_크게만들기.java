package d2307;

import java.io.*;
import java.util.*;

public class Main_bj_2812_크게만들기 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String num = br.readLine();

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            int tmp = num.charAt(i) - '0';
            while (!stack.isEmpty() && tmp > stack.peek() && K != 0) {
                K--;
                stack.pop();
            }
            stack.push(tmp);
        }

        for (int i = 0; i < stack.size()-K; i++) {
            sb.append(stack.get(i));
        }

        System.out.println(sb);

    }
}
