package d2304;

import java.io.*;
import java.util.*;

public class Main_bj_2212_센서 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_2212.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int K = Integer.parseInt(st.nextToken());

        int sensor[] = new int[N];
        int diff[] = new int[N - 1];

        int answer = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            sensor[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensor);

        for (int i = 0; i < N - 1; i++) {
            diff[i] = sensor[i + 1] - sensor[i];
        }

        Arrays.sort(diff);

        for (int i = 0; i < N - K; i++) {
            answer = answer + diff[i];
        }

        System.out.print(answer);
    }
}