package d2310;

import java.io.*;
import java.util.*;

public class Main_bj_20056_마법사상어와파이어볼 {

    static int N, M, K;
    static ArrayList<FireBall> list[][];
    static ArrayList<FireBall> tmpList[][];
    static int di[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dj[] = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 어레이 리스트 배열에 파이어볼 정보 저장
        list = new ArrayList[N][N];
        tmpList = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                list[i][j] = new ArrayList<FireBall>();
                tmpList[i][j] = new ArrayList<FireBall>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken()); // 질량
            int s = Integer.parseInt(st.nextToken()); // 속력
            int d = Integer.parseInt(st.nextToken()); // 방향
            list[r][c].add(new FireBall(m, s, d));
        }

        // K 만큼 로직 수행
        for (int k = 0; k < K; k++) {
            // 이동
            move();
            // 분산
            spread();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    list[i][j].clear();
                    for (int n = 0; n < tmpList[i][j].size(); n++) {
                        list[i][j].add(tmpList[i][j].get(n));
                    }
                    tmpList[i][j].clear();
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < list[i][j].size(); k++) {
                    sum = sum + list[i][j].get(k).m;
                }
            }
        }
        System.out.print(sum);
    }

    static void move() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int n = 0; n < list[i][j].size(); n++) {
                    int m = list[i][j].get(n).m;
                    int s = list[i][j].get(n).s;
                    int d = list[i][j].get(n).d;

                    int tmpS = s % N;
                    int r = i;
                    int c = j;
                    for (int k = 0; k < tmpS; k++) {
                        r = r + di[d];
                        c = c + dj[d];
                        if (r == -1) {
                            r = N - 1;
                        } else if (r == N) {
                            r = 0;
                        }

                        if (c == -1) {
                            c = N - 1;
                        } else if (c == N) {
                            c = 0;
                        }
                    }
                    tmpList[r][c].add(new FireBall(m, s, d));
                }
            }
        }
    }

    static void spread() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int size = tmpList[i][j].size();
                if (size >= 2) {
                    int sumM = 0;
                    int sumS = 0;
                    int flag = 0; // 방향이 짝수이면 1증가
                    for (int k = 0; k < tmpList[i][j].size(); k++) {
                        FireBall fb = tmpList[i][j].get(k);
                        sumM = sumM + fb.m;
                        sumS = sumS + fb.s;
                        if (fb.d % 2 == 0) flag = flag + 1;
                    }
                    tmpList[i][j].clear();

                    if (sumM / 5 != 0) {
                        if (flag == 0 || flag == size) {
                            for (int k = 0; k <= 6; k += 2) {
                                tmpList[i][j].add(new FireBall(sumM / 5, sumS / size, k));
                            }
                        } else {
                            for (int k = 1; k <= 7; k += 2) {
                                tmpList[i][j].add(new FireBall(sumM / 5, sumS / size, k));
                            }
                        }
                    }
                }
            }
        }
    }

    static class FireBall {
        int m, s, d; // 질량m 속력s 방향d

        FireBall(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}