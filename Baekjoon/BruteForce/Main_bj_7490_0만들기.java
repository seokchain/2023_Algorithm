package d2310;

import java.io.*;
import java.util.*;

public class Main_bj_7490_0만들기 {

    static int N;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            findZero(1,1,0,1,"1");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void findZero(int cnt, int num, int sum, int before, String str) {

        if (cnt == N) {
            sum += (before * num);
            if (sum == 0) sb.append(str).append("\n");
            return;
        }

        findZero(cnt + 1, num * 10 + (cnt + 1), sum, before, str + " " + String.valueOf(cnt + 1));
        findZero(cnt + 1, cnt + 1, sum + (before * num), 1, str + "+" + String.valueOf(cnt + 1));
        findZero(cnt + 1, cnt + 1, sum + (before * num), -1, str + "-" + String.valueOf(cnt + 1));
    }
}
