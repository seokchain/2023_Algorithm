package d2306;

import java.io.*;
import java.util.*;

public class Main_bj_2141_우체국 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        long arr[][] = new long[N][2];
        long all = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            all += arr[i][1];
        }

        Arrays.sort(arr, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {

                if(o1[0] == o2[0]){
                    return (int) (o1[1] - o2[1]);
                }
                return (int) (o1[0] - o2[0]);
            }
        });

        long tmp = 0;
        for (int i = 0; i < N; i++) {
            tmp += arr[i][1];
            if (tmp >= (all + 1) / 2) {
                System.out.println(arr[i][0]);
                break;
            }
        }
    }
}
