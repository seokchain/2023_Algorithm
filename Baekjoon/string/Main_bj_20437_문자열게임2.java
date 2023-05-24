package d2305;

import java.io.*;
import java.util.*;

public class Main_bj_20437_문자열게임2 {

    static ArrayList<Integer> list[];
    static int K, min, max;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_20437.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            String str = br.readLine();
            K = Integer.parseInt(br.readLine());

            list = new ArrayList[26];

            for (int i = 0; i < 26; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < str.length(); i++) {
                list[str.charAt(i) - 97].add(i);
            }

            for (int i = 0; i < 26; i++) {
                if (list[i].size() >= K) {
                    getCount(list[i]);
                }
            }
            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(min + " " + max);
            }
        }

    }

    static void getCount(ArrayList<Integer> list) {
        for (int i = 0; i < list.size() - K + 1; i++) {
            int tmp_min = list.get(i + K - 1) - list.get(i) + 1;
            int tmp_max = list.get(i + K - 1) - list.get(i) + 1;
            min = Math.min(tmp_min, min);
            max = Math.max(tmp_max, max);
        }
    }
}
