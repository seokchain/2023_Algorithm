package sds_algo;

import java.io.*;
import java.util.*;

/*
	출처 : 코드트리_ 삼성 SW 역량테스트 2022 상반기 오전 2번 문제 (예술성)
 */

public class 예술성_2022_상반기_02 {

	static int N, cnt;
	static int map[][], tmpMap[][];
	static int group[][];
	static int groupCount[];
	static boolean v[][];
	static boolean groupV[][];

	static int di[] = { 0, 0, 1, -1 };
	static int dj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/예술성_2022_상반기_02.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		tmpMap = new int[N][N];
		group = new int[N][N];
		v = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int totalScore = 0;

		findGroup();
		totalScore = totalScore + getScore();

		for (int i = 0; i < 3; i++) {
			group = new int[N][N];
			tmpMap = new int[N][N];
			v = new boolean[N][N];

			crossTurn();
			squareTurn();

			copyMap();

			findGroup();
			totalScore = totalScore + getScore();
		}

		System.out.println(totalScore);

	}

	static void findGroup() {

		cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!v[i][j]) {
					cnt++;
					v[i][j] = true;
					group[i][j] = cnt;
					dfs(i, j, map[i][j], cnt);
				}
			}
		}
		groupV = new boolean[cnt + 1][cnt + 1];
	}

	// 모든 그룹 조화로움 계산
	static int getScore() {

		int score = 0;
		getGroupInfo();
		for (int i = 1; i <= cnt; i++) {
			for (int j = 1; j <= cnt; j++) {
				if (i == j || groupV[i][j]) {
					continue;
				}
				score = score + sum(i, j);
			}
		}
		return score;
	}

	// 그룹쌍별 조화로움 계산
	static int sum(int n, int m) {
		int near = 0;

		// n 그룹의 숫자
		int n_num = 0;
		// m 그룹의 숫자
		int m_num = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (group[i][j] == n) {
					n_num = map[i][j];
					for (int d = 0; d < 4; d++) {
						int ni = i + di[d];
						int nj = j + dj[d];
						if (ni < N && ni >= 0 && nj < N && nj >= 0 && group[ni][nj] == m) {
							m_num = map[ni][nj];
							near++;
						}
					}
				}
			}
		}
		// 방문처리
		if (near > 0) {
			groupV[n][m] = true;
			groupV[m][n] = true;
			return (groupCount[n] + groupCount[m]) * n_num * m_num * near;
		}

		return 0;
	}

	// 그룹찾는 dfs
	static void dfs(int i, int j, int num, int cnt) {

		for (int d = 0; d < 4; d++) {

			int ni = i + di[d];
			int nj = j + dj[d];

			if (ni < N && ni >= 0 && nj < N && nj >= 0 && !v[ni][nj] && map[ni][nj] == num) {
				v[ni][nj] = true;
				group[ni][nj] = cnt;
				dfs(ni, nj, num, cnt);
			}
		}
	}

	// 각 그룹별 갯수 저장
	static void getGroupInfo() {

		groupCount = new int[cnt + 1];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				groupCount[group[i][j]]++;
			}
		}
	}

	static void crossTurn() {

		int center = (N - 1) / 2;

		tmpMap[center][center] = map[center][center];
		for (int i = 0; i < center; i++) {
			tmpMap[center][i] = map[i][center];
		}
		for (int i = 0; i < center; i++) {
			tmpMap[N - 1 - i][center] = map[center][i];
		}
		for (int i = 0; i < center; i++) {
			tmpMap[center][N - 1 - i] = map[N - 1 - i][center];
		}
		for (int i = 0; i < center; i++) {
			tmpMap[i][center] = map[center][N - 1 - i];
		}
	}

	static void squareTurn() {

		int center = (N - 1) / 2;

		// 왼쪽 위
		for (int i = 0; i < center; i++) {
			for (int j = 0; j < center; j++) {
				tmpMap[i][j] = map[center - 1 - j][i];
			}
		}

		// 오른쪽 위
		for (int i = 0; i < center; i++) {
			for (int j = 0; j < center; j++) {
				tmpMap[i][center + 1 + j] = map[center - 1 - j][center + 1 + i];
			}
		}

		// 왼쪽 아래
		for (int i = 0; i < center; i++) {
			for (int j = 0; j < center; j++) {
				tmpMap[center + 1 + i][j] = map[N - 1 - j][i];
			}
		}

		// 오른쪽 아래
		for (int i = 0; i < center; i++) {
			for (int j = 0; j < center; j++) {
				tmpMap[center + 1 + i][center + 1 + j] = map[N - 1 - j][center + 1 + i];
			}
		}
	}

	static void copyMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = tmpMap[i][j];
			}
		}
	}

}
