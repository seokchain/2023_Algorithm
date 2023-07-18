package d2307;

import java.io.*;
import java.util.*;

public class Main_bj_1253_좋다 {

    static int N, arr[];

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (good(i, arr[i])) ans++;
        }
        System.out.println(ans);
    }

    static boolean good(int i, int num) {
        int start = 0;
        int end = N - 1;

        while (true) {

            if (start == i) start++;
            if (end == i) end--;
            if (start == end) break;

            int sum = arr[start] + arr[end];
            if (num == sum) return true;

            if (num > sum) {
                start++;
            } else if (num < sum) {
                end--;
            }
        }
        return false;
    }
}