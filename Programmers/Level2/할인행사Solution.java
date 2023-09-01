package d2308;

import java.util.*;

class 할인행사Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0; i < want.length; i++){
            map.put(want[i], number[i]);
        }

        for(int i = 0; i < 10; i++){
            String key = discount[i];
            if(map.containsKey(key)) map.replace(key, map.get(key)-1);
        }
        if(check(map)) answer++;
        for(int i = 10; i < discount.length; i++){
            String key = discount[i-10];
            String key1 = discount[i];

            if(map.containsKey(key)) map.replace(key, map.get(key)+1);
            if(map.containsKey(key1)) map.replace(key1, map.get(key1)-1);
            if(check(map)) answer++;
        }
        return answer;
    }

    static boolean check(HashMap<String, Integer> map){

        for(String key : map.keySet()){
            if(map.get(key) != 0) return false;
        }
        return true;
    }
}