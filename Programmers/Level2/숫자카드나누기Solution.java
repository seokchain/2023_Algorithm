package d2308;

import java.util.*;

public class 숫자카드나누기Solution {

    static ArrayList<Integer> divs;
    static int max;

    public int solution(int[] arrayA, int[] arrayB) {
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        //A
        findDivNumList(arrayA[0]);
        findMaxNum(divs, arrayA, arrayB);

        //B
        findDivNumList(arrayB[0]);
        findMaxNum(divs, arrayB, arrayA);

        return max;
    }

    static void findDivNumList(int num) {

        divs = new ArrayList<>();

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                divs.add(i);
                divs.add(num / i);
            }
        }
        divs.add(num);
    }

    static void findMaxNum(ArrayList<Integer> list, int[] arrA, int[] arrB) {

        Collections.sort(list, Collections.reverseOrder());

        for (int i = 0; i < list.size(); i++) {
            int divNum = list.get(i);
            if (checkCanDivNum(divNum, arrA) && checkCantDivNum(divNum, arrB)) {
                max = Math.max(max, divNum);
                return;
            }
        }
    }

    static boolean checkCanDivNum(int num, int arr[]) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % num != 0) return false;
        }
        return true;
    }

    static boolean checkCantDivNum(int num, int arr[]) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % num == 0) return false;
        }
        return true;
    }
}