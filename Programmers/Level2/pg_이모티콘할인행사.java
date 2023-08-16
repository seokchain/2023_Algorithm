package d2308;

import java.util.*;

class 이모티콘할인행사Solution {

    static int N, sale[], answer[], user[][], emoticon[];
    static int input[] = {10, 20, 30, 40};

    public int[] solution(int[][] users, int[] emoticons) {

        N = emoticons.length;
        sale = new int[N];
        answer = new int[2];

        user = users;
        emoticon = emoticons;

        per(0);

        return answer;
    }

    static void per(int cnt) {

        if (cnt == N) {
            buyEmoticon(sale);
            return;
        }

        for (int i = 0; i < 4; i++) {
            sale[cnt] = input[i];
            per(cnt + 1);
        }
    }

    static void buyEmoticon(int[] sale) {
        int ePlus = 0;
        int total = 0;

        here:
        for (int i = 0; i < user.length; i++) {
            int money = 0;
            for (int j = 0; j < emoticon.length; j++) {
                if (user[i][0] > sale[j]) continue;
                money += (emoticon[j] * (100 - sale[j])) / 100;
                if (money >= user[i][1]) {
                    ePlus++;
                    continue here;
                }
            }
            total = total + money;
        }

        if (ePlus > answer[0]) {
            answer[0] = ePlus;
            answer[1] = total;
        } else if (ePlus == answer[0]) {
            answer[1] = Math.max(answer[1], total);
        }
    }
}