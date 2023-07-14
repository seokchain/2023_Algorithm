package d2307;

import java.io.*;
import java.util.*;

public class Main_bj_16235_나무재테크 {

     /*
        봄 - 나무는 나이만큼 양분을 먹고 나이가 1 증가한다. 나무는는 1×1 크기의 칸에 있는 양분만 먹는다.
            여러개의  나이가 어린 나무부터 양분을 먹는다. 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
        여름 - 봄에 죽은 나무가 양분으로 변하게 된다. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
        가을 - 가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
        겨울 - 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.

        K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하는 프로그램을 작성하시오.
     */

    static int N, M, K, map[][], A[][];
    static ArrayList<Integer>[][] trees;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // N x N
        M = Integer.parseInt(st.nextToken()); // 나무 수
        K = Integer.parseInt(st.nextToken()); // K년후 나무의 개수

        map = new int[N][N];
        A = new int[N][N];

        trees = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                trees[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees[x - 1][y - 1].add(age);
        }

        for (int i = 0; i < K; i++) {
            year();
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count = count + trees[i][j].size();
            }
        }

        System.out.println(count);

    }

    static void year() {

        // 봄 ~ 여름
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (trees[i][j].size() != 0) {
                    feed(i, j);
                }
            }
        }

        // 가을
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (trees[i][j].size() != 0) {
                    grow(i, j);
                }
            }
        }

        // 겨울
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = map[i][j] + A[i][j];
            }
        }

    }

    static void feed(int x, int y) {

        // 나이순으로 정렬
        Collections.sort(trees[x][y]);

        int num = trees[x][y].size();
        int deadPoint = 0;
        for (int i = 0; i < num; i++) {

            int age = trees[x][y].get(0);

            if (map[x][y] >= age) {
                map[x][y] = map[x][y] - age;
                trees[x][y].remove(0);
                trees[x][y].add(age + 1);
            } else {
                deadPoint = deadPoint + age / 2;
                trees[x][y].remove(0);
            }
        }
        map[x][y] = map[x][y] + deadPoint;
    }

    static void grow(int x, int y) {

        int ni[] = {1, -1, 0, 0, 1, -1, 1, -1};
        int nj[] = {0, 0, -1, 1, 1, -1, -1, 1};

        for (int i = 0; i < trees[x][y].size(); i++) {

            if (trees[x][y].get(i) % 5 == 0) {
                for (int d = 0; d < 8; d++) {
                    int di = x + ni[d];
                    int dj = y + nj[d];
                    if(di >= 0 && di < N && dj >= 0 && dj < N){
                        trees[di][dj].add(1);
                    }
                }
            }
        }
    }
}