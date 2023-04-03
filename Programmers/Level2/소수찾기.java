package d230403;

import java.io.*;
import java.util.*;

class Solution {

    static char[] arr;
    static boolean[] v;
    static HashSet<Integer> set;

    public int solution(String numbers) {

        arr = new char[numbers.length()];
        v = new boolean[numbers.length()];
        set = new HashSet<>();

        for (int i = 0; i < numbers.length(); i++) {
            arr[i] = numbers.charAt(i);
        }

        per("", 0);

        return set.size();
    }

    public void per(String str, int cnt) {

        if (str != "") {
            int num = Integer.parseInt(str);
            if (isPrimeNumber(num)) set.add(num);
        }

        if (cnt == arr.length) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {

            if (v[i]) continue;

            v[i] = true;
            per(str + arr[i], cnt + 1);
            v[i] = false;
        }
    }

    public boolean isPrimeNumber(int number) {

        if (number < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
