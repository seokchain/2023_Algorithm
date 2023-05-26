package d2305;

import java.io.*;
import java.util.*;

public class Main_bj_1863_스카이라인쉬운거 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_1863.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Integer> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());
        int count = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (y == 0) {
                count = count + stack.size();
                stack.clear();
                continue;
            }

            if (stack.isEmpty()) {
                stack.push(y);
            } else if (stack.peek() < y) {
                stack.push(y);
            } else {
                while (!stack.isEmpty() && stack.peek() > y) {
                    stack.pop();
                    count++;
                }
                if (stack.isEmpty()) stack.push(y);
                else if (stack.peek() < y) stack.push(y);
            }
        }
        count = count + stack.size();
        System.out.println(count);
    }
}
