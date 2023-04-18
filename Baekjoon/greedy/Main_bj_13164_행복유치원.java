package d2304;

import java.io.*;
import java.util.*;

public class Main_bj_13164_행복유치원 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_13164.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N - 1];

        st = new StringTokenizer(br.readLine());
        int temp = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N - 1; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num - temp;
            temp = num;
        }
        Arrays.sort(arr);

        int cnt = 0;

        for (int i = 0; i < N - K; i++) {
            cnt += arr[i];
        }

        System.out.println(cnt);
    }
}
