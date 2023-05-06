package d2305;

import java.util.*;
import java.io.*;

public class Main_비밀메뉴 {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String secret = "";
        String str = "";
        String ans = "normal";

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            secret += st.nextToken();
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            str += st.nextToken();
        }

        if (str.contains(secret)) ans = "secret";
        System.out.println(ans);
    }
}