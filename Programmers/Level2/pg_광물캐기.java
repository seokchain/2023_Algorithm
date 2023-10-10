package d2310;

import java.io.*;
import java.util.*;

class 광물캐기Solution {

    static boolean v[];
    static int output[], N, min;
    static ArrayList<Integer> list;

    public int solution(int[] picks, String[] minerals) {

        list = new ArrayList<>();

        for (int i = 0; i < picks.length; i++) {
            for (int j = 0; j < picks[i]; j++) {
                list.add(i);
                if (list.size() > 10) break;
            }
        }

        N = list.size();
        output = new int[N];
        v = new boolean[N];
        min = Integer.MAX_VALUE;
        perm(0, minerals);

        return min;
    }

    static void mine(int[] order, String[] minerals) {
        int pirodo = 0;
        int mineralsCnt = 0;

        here:
        for (int i = 0; i < N; i++) {

            int pick = order[i];

            for (int j = 0; j < 5; j++) {

                if (pick == 0) {
                    pirodo += 1;
                } else if (pick == 1) {
                    if (minerals[mineralsCnt].equals("diamond")) pirodo += 5;
                    else pirodo += 1;
                } else {
                    if (minerals[mineralsCnt].equals("diamond")) pirodo += 25;
                    else if (minerals[mineralsCnt].equals("iron")) pirodo += 5;
                    else pirodo += 1;
                }

                if (pirodo > min) return;
                mineralsCnt = mineralsCnt + 1;
                if (mineralsCnt == minerals.length) break here;
            }
        }

        min = Math.min(pirodo, min);
    }

    static void perm(int cnt, String[] minerals) {

        if (cnt == N) {
            mine(output, minerals);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (v[i]) continue;
            v[i] = true;
            output[cnt] = list.get(i);
            perm(cnt + 1, minerals);
            v[i] = false;
        }
    }

}

public class pg_광물캐기 {
    public static void main(String[] args) {
        광물캐기Solution solution = new 광물캐기Solution();
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        solution.solution(picks, minerals);
    }
}
