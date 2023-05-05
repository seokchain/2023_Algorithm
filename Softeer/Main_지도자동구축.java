package d2305;

import java.util.*;
import java.io.*;

public class Main_지도자동구축 {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());

        /*
        0 - 2 2
        1 - 3 3
        2 - 5 5
        3 - 9 9
        4 - 17 17
        5 - 33 33
        */

        int circle = 2;

        for (int i = 0; i < N; i++) {
            circle = circle + (circle - 1);
        }

        System.out.println(circle * circle);
    }
}
