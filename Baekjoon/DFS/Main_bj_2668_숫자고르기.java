package d2306;

import java.io.*;
import java.util.*;

public class Main_bj_2668_숫자고르기 {

    static int N, index, arr[];
    static boolean v[];
    static ArrayList<Integer> list;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        v = new boolean[N + 1];
        list = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            index = i;
            v[i] = true;
            dfs(i);
            v[i] = false;
        }

        Collections.sort(list);

        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    static void dfs(int start) {

        if (arr[start] == index) {
            list.add(index);
            return;
        }

        if (!v[arr[start]]) {
            v[arr[start]] = true;
            dfs(arr[start]);
            v[arr[start]] = false;
        }
    }

}