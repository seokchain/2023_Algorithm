package d2304;

import java.io.*;
import java.util.*;

class Solution {

    static boolean[] v;

    public int solution(int n, int[][] computers) {

        v = new boolean[n];
        int res = 0;
        for(int i = 0; i < n; i++){

            if(!v[i]){
                dfs(computers, i, v);
                res++;
            }
        }
        return res;
    }

    static void dfs(int[][] computers, int i, boolean[] v){
        v[i] = true;
        for(int j = 0; j < computers.length; j++){
            if(j != i && computers[i][j] == 1 && !v[j]){
                v[j] = true;
                dfs(computers, j, v);
            }
        }

    }
}
