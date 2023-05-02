package d2305;

import java.util.*;
import java.io.*;

public class Main_8단변속기 {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int flag = 1;
        String str = "ascending";

        int num = Integer.parseInt(st.nextToken());

        if (num == 8) {
            flag = -1;
            str = "descending";
        }

        for (int i = 1; i < 8; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (num + flag == tmp) num = tmp;
            else {
                str = "mixed";
                break;
            }
        }
        System.out.println(str);
    }
}
