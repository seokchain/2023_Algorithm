package sds_algo;

import java.io.*;
import java.util.*;

/*
 * 출처 : 코드트리_ 삼성 SW 역량테스트 2022 상반기 오전 1번 문제 (술래잡기)
 */
public class 술래잡기_2022_상반기_01 {

	static int N, M, H, K, heroX, heroY, heroD, heroType;
	static int map[][], tree[][];
	static boolean v[][];
	static Queue<Runner> q;
	static Queue<Runner> runnerlist;

	static int di[] = { 0, 0, 1, 0, -1 }; // 우, 하, 좌, 상
	static int dj[] = { 0, 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/술래잡기_2022_상반기_01.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 격자크기
		M = Integer.parseInt(st.nextToken()); // 도망자 수
		H = Integer.parseInt(st.nextToken()); // 나무 개수
		K = Integer.parseInt(st.nextToken()); // 턴수

		map = new int[N][N];
		tree = new int[N][N];
		v = new boolean[N][N];

		q = new LinkedList<Runner>();

		heroX = (N - 1) / 2;
		heroY = (N - 1) / 2;

		heroType = 0; // 시계 방향
		heroD = 4; // 술래 초기 이동방향

		int answer = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			q.add(new Runner(x - 1, y - 1, d));
		}

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			tree[x - 1][y - 1] = 5; // 나무
		}

		nonblock();

		for (int i = 1; i <= K; i++) {

			run();
			if (heroType == 0) {
				hero_move1();
			} else {
				hero_move2();
			}
			answer += i * runnerCatch();
		}

		System.out.println(answer);
	}

	// 도망자 움직임
	static void run() {

		runnerlist = new LinkedList<Runner>();

		while (!q.isEmpty()) {

			Runner runner = q.poll();

			// 술래와의 거리 체크
			int num = Math.abs(runner.x - heroX) + Math.abs(runner.y - heroY);

			if (num >= 4) {
				runnerlist.add(runner);
				continue; // 거리가 3이하가 아니면 아웃
			}

			int ni = runner.x + di[runner.d];
			int nj = runner.y + dj[runner.d];

			// 격자를 안벗어 나는 경우
			if (ni < N && ni >= 0 && nj < N && nj >= 0) {

				// 이동 위치에 술래가 있는경우
				// 러너 리스트 큐에 다시 넣어버림
				if (heroX == ni && heroY == nj) {
					runnerlist.add(new Runner(runner.x, runner.y, runner.d));
				} else {
					// 이동위치에 술래가 없는경우 이동하고 러너리스트에 넣음
					runnerlist.add(new Runner(ni, nj, runner.d));
				}

				// 격자를 벗어난 경우
			} else {

				if (runner.d <= 2) {

					ni = runner.x + di[runner.d + 2];
					nj = runner.y + dj[runner.d + 2];

					// 이동 위치에 술래가 있는경우 방향만 바꾸고
					// 러너 리스트에 다시 넣어버림
					if (heroX == ni && heroY == nj) {
						runnerlist.add(new Runner(runner.x, runner.y, runner.d + 2));

						// 이동위치에 술래가 없는경우 방향 바꾸고 이동하고 러너리스트에 넣음
					} else {
						runnerlist.add(new Runner(ni, nj, runner.d + 2));
					}

				} else {

					ni = runner.x + di[runner.d - 2];
					nj = runner.y + dj[runner.d - 2];

					// 이동 위치에 술래가 있는경우 방향만 바꾸고
					// 러너 리스트에 방향 바꾸고 다시 넣어버림
					if (heroX == ni && heroY == nj) {
						runnerlist.add(new Runner(runner.x, runner.y, runner.d - 2));
					} else {
						runnerlist.add(new Runner(ni, nj, runner.d - 2));
					}

				}
			}
		}
	}

	// 술래 움직임 정방향
	static void hero_move1() {

		heroX = heroX + di[heroD];
		heroY = heroY + dj[heroD];

		// 끝 도달 시
		if (heroX == 0 && heroY == 0) {
			heroD = 2;
			heroType = 1;
			v[heroX][heroY] = false;
			return;
		}

		if (heroX < (N - 1) / 2 && heroX == heroY) {
			nonblock();
		}

		int ni = heroX + di[heroD];
		int nj = heroY + dj[heroD];

		if (ni >= N || ni < 0 || nj >= N || nj < 0 || !v[ni][nj]) {
			heroD = heroD % 4 + 1;
		}
	}

	// 술래 움직인 역방향
	static void hero_move2() {

		map[heroX][heroY] = 0;

		heroX = heroX + di[heroD];
		heroY = heroY + dj[heroD];
		v[heroX][heroY] = false;

		if (heroX == (N - 1) / 2 && heroY == (N - 1) / 2) {
			heroD = 4;
			heroType = 0;
			nonblock();
		}

		map[heroX][heroY] = -1;

		int ni = heroX + di[heroD];
		int nj = heroY + dj[heroD];

		if (ni >= N || ni < 0 || nj >= N || nj < 0 || !v[ni][nj]) {
			heroD = heroD - 1;
			if (heroD == 0)
				heroD = 4;
		}
	}

	static void nonblock() {
		for (int i = heroX - 1; i <= N - heroX; i++) {
			for (int j = heroY - 1; j <= N - heroY; j++) {
				v[i][j] = true;
			}
		}
	}

	static int runnerCatch() {
		int cnt = 0;

		here: while (!runnerlist.isEmpty()) {

			int tmpX = heroX;
			int tmpY = heroY;

			Runner runner = runnerlist.poll();

			// 술래위치랑 도망자 위치 같은 경우 잡힘
			if (tmpX == runner.x && tmpY == runner.y && tree[runner.x][runner.y] != 5) {
				cnt++;
				continue here;
			} else {
				// 범위 내에서 잡힌 경우
				for (int i = 0; i < 2; i++) {
					tmpX = tmpX + di[heroD];
					tmpY = tmpY + dj[heroD];
					if (tmpX == runner.x && tmpY == runner.y && tree[runner.x][runner.y] != 5) {
						cnt++;
						continue here;
					}
				}
			}
			q.add(runner);
		}
		return cnt;
	}

	static class Runner {

		int x;
		int y;
		int d;

		Runner(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

}
