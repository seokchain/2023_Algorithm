package d2307;

import java.io.*;
import java.util.*;

public class Main_bj_12904_A와B {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        int N = T.length() - S.length();

        for (int i = 0; i < N; i++) {

            if (T.charAt(T.length() - 1) == 'A') {
                T = T.substring(0, T.length() - 1);
            } else {
                T = T.substring(0, T.length() - 1);
                String tmp = "";
                for (int j = T.length() - 1; j >= 0; j--) {
                    tmp = tmp + T.charAt(j);
                }
                T = tmp;
            }
        }

        if (S.equals(T)) System.out.println(1);
        else System.out.println(0);
    }
}
