package d2304;

import java.io.*;
import java.util.*;

public class Main_bj_1931_회의실배정 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_1931.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int arr[][] = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken()); // 시작시간
            arr[i][1] = Integer.parseInt(st.nextToken()); // 종료시간
        }

        // 2차원 배열 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });

        int cnt = 0;
        int endTime = 0;

        for (int i = 0; i < N; i++) {
            if (endTime <= arr[i][0]) {
                endTime = arr[i][1];
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}