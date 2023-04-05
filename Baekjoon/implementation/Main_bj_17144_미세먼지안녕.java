package d230405;

import java.io.*;
import java.util.*;

/*
 * 1. 미세먼지 확산
 * 인접한 네 방향으로 확산
 * 칸이 없거나 공기청정기가 있으면 확산 x
 * 확산되는 양은 /5 소수점 제거
 * 남은 미세먼지양은 확산된 양에서 뺀 값
 *
 * 2. 공기청정기 작동
 * 위쪽공기청정기는 반시계 방향으로 순환
 * 아래공기청정기는 시계방향으로 순환
 * 공기청정기로 들어가면 미세먼지는 모두 정화
 *
 */
public class Main_bj_17144_미세먼지안녕 {

	static Queue<Dust> q;
	static int R, C, T, top, bottom;
	static int map[][];

	static int di[] = {0, 0, 1, -1};
	static int dj[] = {1, -1, 0, 0};

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input_17144.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					bottom = i;
					top = bottom - 1;
				}
			}
		}

		q = new LinkedList<Dust>();

		for (int i = 0; i < T; i++) {
			spread();
			top();
			bottom();
		}
		System.out.println(totalDust());
	}

	// 미세먼지 확산
	static void spread() {

		checkDust();

		while (!q.isEmpty()) {

			Dust dust = q.poll();

			int r = dust.r;
			int c = dust.c;
			int size = dust.size;

			int num = size / 5;

			for (int d = 0; d < 4; d++) {
				int ni = r + di[d];
				int nj = c + dj[d];
				if (ni < R && ni >= 0 && nj < C && nj >= 0 && map[ni][nj] != -1) {
					map[ni][nj] += num;
					map[r][c] -= num;
				}
			}
		}
	}

	// 공기청정기 작동
	static void top() {

		// down
		for (int i = top - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}
		// left
		for (int i = 0; i < C - 1; i++) {
			map[0][i] = map[0][i + 1];
		}
		// up
		for (int i = 0; i < top; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}
		// right
		for (int i = C - 1; i > 1; i--) {
			map[top][i] = map[top][i - 1];
		}
		map[top][1] = 0;

	}

	static void bottom() {

		// up
		for (int i = bottom + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		// left
		for (int i = 0; i < C - 1; i++) {
			map[R - 1][i] = map[R - 1][i + 1];
		}
		// down
		for (int i = R - 1; i > bottom; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}
		// right
		for (int i = C - 1; i > 1; i--) {
			map[bottom][i] = map[bottom][i - 1];
		}
		map[bottom][1] = 0;
	}

	// 미세먼지 체크
	static void checkDust() {

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					q.add(new Dust(i, j, map[i][j]));
				}
			}
		}
	}

	// 남은 미세먼지 개수 확인
	static int totalDust() {

		int totalDust = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					totalDust += map[i][j];
				}
			}
		}
		return totalDust;
	}

	static class Dust {

		int r;
		int c;
		int size;

		Dust(int r, int c, int size) {

			this.r = r;
			this.c = c;
			this.size = size;

		}
	}
}
