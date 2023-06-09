package d2306;

import java.io.*;
import java.util.*;

public class Main_bj_2179_비슷한단어 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String arr[] = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        int max = 0;
        String str1 = "";
        String str2 = "";

        for (int i = 0; i < N-1; i++) {
            for (int j = i+1; j < N; j++) {
                int tmpCnt = 0;
                int length = Math.min(arr[i].length(), arr[j].length());
                // 한글자씩 비교
                for (int k = 0; k < length; k++) {
                    if(arr[i].charAt(k) == arr[j].charAt(k)){
                        tmpCnt = tmpCnt + 1;
                    } else { // 틀린 글자 나오면 다음 단어 비교
                        if(tmpCnt > max){
                            str1 = arr[i];
                            str2 = arr[j];
                            max = tmpCnt;
                        }
                        break;
                    }
                }
                if(tmpCnt > max){
                    str1 = arr[i];
                    str2 = arr[j];
                    max = tmpCnt;
                }
            }
        }
        System.out.println(str1);
        System.out.println(str2);
    }
}
