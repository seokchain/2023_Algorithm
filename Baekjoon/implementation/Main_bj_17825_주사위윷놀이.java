package d2306;

import java.io.*;
import java.util.*;

public class Main_bj_17825_주사위윷놀이 {

    static Node horse[];
    static int score, dice[], order[], map[][];;
    static boolean v[][];

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        dice = new int[10];

        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        map = new int[42][2];

        for (int i = 0; i < 40; i += 2) {
            map[i][0] = i + 2;
        }

        map[10][1] = 13;
        map[13][1] = 16;
        map[16][1] = 19;
        map[19][1] = 25;
        map[20][1] = 22;
        map[22][1] = 24;
        map[24][1] = 25;
        map[28][1] = 27;
        map[27][1] = 26;
        map[26][1] = 25;
        map[25][1] = 30;
        map[30][1] = 35;
        map[35][1] = 40;

        score = Integer.MIN_VALUE;
        order = new int[10];
        per(0);
        System.out.println(score);
    }

    static int play() {
        int total = 0;

        horse = new Node[4];
        v = new boolean[42][2];

        for (int i = 0; i < 4; i++) {
            horse[i] = new Node(0,0);
        }

        for (int i = 0; i < 10; i++) {
            int count = move(dice[i], order[i]);
            if (count == -1) return -1;
            total = total + count;
        }

        return total;
    }

    static int move(int dice, int N) {
        int count = 0;

        // N번째 말 위치
        int pos = horse[N].pos;
        // 0 : 정상 노선 , 1 : 파란 노선
        int state = horse[N].state;
        // 말 위치 초기화
        v[pos][state] = false;
        // 골인한 말이면 리턴
        if (pos == 41) return -1;

        // 주사위 숫자만큼 이동해준다.
        for (int i = 0; i < dice; i++) {
            // 첫 시작이 파란색인 경우
            if(i == 0){
                if(pos == 10 || pos == 20) { // 상태를 파란맵으로 바꿔준다.
                    state = 1;
                    horse[N] = new Node(pos, state);
                }else if(pos == 30 && state == 0){ // 30인 경우 따로처리
                    state = 1;
                    horse[N] = new Node(pos, state);
                    pos = 28;
                    continue;
                }
            }
            // 말이 도착 지점에 도달하는 경우
            // 말 위치를 41번으로 이동시키고 현재까지 점수를 리턴
            if (pos == 40) {
                horse[N] = new Node(41, state);
                return count;
            }
            // 그외 이동하는 경우
            pos = map[pos][state];
        }

        // 이동을 마친 칸에 다른 말이 있으면 리턴
        // 40인 경우 방문처리를 잘 확인해준다.
        if(pos == 40){
            if(v[pos][0] || v[pos][1]) return -1;
        }
        if (v[pos][state]) return -1;
        else { // 해당 칸에 말이 없는 경우 방문처리 및 점수를 더해준다.
            v[pos][state] = true;
            count = count + pos;
            horse[N] = new Node(pos, state);
        }
        return count;
    }

    static void per(int cnt) {

        if (cnt == 10) {
            int num = play();
            if (num != -1) {
                score = Math.max(score, num);
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            order[cnt] = i;
            per(cnt + 1);
        }
    }

    static class Node {
        int pos;
        int state;
        Node(int pos, int state) {
            this.pos = pos;
            this.state = state;
        }
    }
}