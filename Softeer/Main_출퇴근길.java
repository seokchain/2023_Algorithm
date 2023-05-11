package d2305;

import java.util.*;
import java.io.*;

public class Main_출퇴근길 {

    static int n, m, S, T;
    static ArrayList<Integer> arr[], arrR[];
    static boolean v1[], v2[], vR1[], vR2[];

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n + 1];
        arrR = new ArrayList[n + 1];

        for (int i = 0; i < n + 1; i++) {
            arr[i] = new ArrayList<>();
            arrR[i] = new ArrayList<>();
        }

        v1 = new boolean[n + 1];
        v2 = new boolean[n + 1];

        vR1 = new boolean[n + 1];
        vR2 = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int go = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[go].add(end);
            arrR[end].add(go);
        }

        st = new StringTokenizer(br.readLine(), " ");
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        v1[T] = true;
        dfs(S, arr, v1);
        v2[S] = true;
        dfs(T, arr, v2);

        dfs(S, arrR, vR1);
        dfs(T, arrR, vR2);

        int cnt = 0;
        for (int i = 1; i < n + 1; i++) {
            if (v1[i] && v2[i] && vR1[i] && vR2[i]) cnt++;
        }
        System.out.println(cnt - 2);
    }

    static void dfs(int now, ArrayList<Integer>[] adj, boolean v[]) {
        if (v[now]) return;
        v[now] = true;
        for (int i = 0; i < adj[now].size(); i++) {
            dfs(adj[now].get(i), adj, v);
        }
    }
}