package d2305;

import java.util.*;
import java.io.*;

public class Main_전광판 {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int T = Integer.parseInt(st.nextToken());

        int arr[][] = {
                {1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 0, 0, 0, 0},
                {1, 1, 0, 1, 1, 0, 1},
                {1, 1, 1, 1, 0, 0, 1},
                {0, 1, 1, 0, 0, 1, 1},
                {1, 0, 1, 1, 0, 1, 1},
                {1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 1, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0}
        };

        for (int tc = 0; tc < T; tc++) {
            int cnt = 0;
            st = new StringTokenizer(br.readLine(), " ");
            String num1 = st.nextToken();
            String num2 = st.nextToken();

            int num1arr[] = new int[5];
            int num2arr[] = new int[5];

            Arrays.fill(num1arr, 10);
            Arrays.fill(num2arr, 10);

            for (int i = 0; i < num1.length(); i++) {
                num1arr[i] = num1.charAt(num1.length() - 1 - i) - '0';
            }

            for (int i = 0; i < num2.length(); i++) {
                num2arr[i] = num2.charAt(num2.length() - 1 - i) - '0';
            }

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 7; j++) {
                    if (arr[num1arr[i]][j] != arr[num2arr[i]][j]) {
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}