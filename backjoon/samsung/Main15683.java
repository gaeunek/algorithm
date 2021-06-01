package samsung;

import java.util.*;
import java.io.*;

public class Main15683 {
	static int N, M, answer;
	static int[][] map;
	static ArrayList<Point> list = new ArrayList<>();
	static ArrayList<Point> fiveList = new ArrayList<>();
	static int[] cctvDirection;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] < 5)
					list.add(new Point(i, j));
				if (map[i][j] == 5)
					fiveList.add(new Point(i, j));
			}
		}

		// 모든 방향 감시가 가능한 5번 CCTV만 따로 처리해준다.
		if (fiveList.size() > 0) {
			for (int k = 0; k < fiveList.size(); k++) {
				Point p = fiveList.get(k);
				for (int d = 0; d < 4; d++) {
					int ni = p.i + dir[d][0];
					int nj = p.j + dir[d][1];

					while (true) {
						if (!check(ni, nj, map))
							break;
						
						map[ni][nj] = map[ni][nj] == 0 ? 7 : map[ni][nj];
						ni += dir[d][0];
						nj += dir[d][1];
					}
				}
			}
		}

		if (list.size() == 0) {
			System.out.println(checkBlindSpot(map));
			return;
		}

		cctvDirection = new int[list.size()];
		answer = Integer.MAX_VALUE;
		dfs(0);
		System.out.println(answer);
	}

	public static void dfs(int index) {
		if (index == list.size()) {
			// 감시카메라 작동
			checkWatchArea();
			return;
		}

		int i = list.get(index).i;
		int j = list.get(index).j;

		int len = map[i][j] == 2 ? 2 : 4;
		for (int d = 0; d < len; d++) {
			cctvDirection[index] = d;
			dfs(index + 1);
			cctvDirection[index] = 0;
		}
	}

	public static void checkWatchArea() {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}

		int ni, nj, nd;
		for (int k = 0; k < cctvDirection.length; k++) {
			Point p = list.get(k);
			int i = p.i;
			int j = p.j;
			int d = cctvDirection[k];
			int cctv_number = newMap[i][j];

			switch (cctv_number) {
			case 1:
				ni = i + dir[d][0];
				nj = j + dir[d][1];
				while (true) {
					if (!check(ni, nj, newMap))
						break;
					newMap[ni][nj] = newMap[ni][nj] == 0 ? 7 : newMap[ni][nj];
					ni += dir[d][0];
					nj += dir[d][1];
				}
				break;
			case 2: // 0 : 좌우, 1 : 상하
				for (int t = d; t < 4; t += 2) {
					ni = i + dir[t][0];
					nj = j + dir[t][1];

					while (true) {
						if (!check(ni, nj, newMap))
							break;
						newMap[ni][nj] = newMap[ni][nj] == 0 ? 7 : newMap[ni][nj];
						ni += dir[t][0];
						nj += dir[t][1];
					}
				}
				break;
			case 3: // 0 : 상우, 1 : 우하, 2 : 하좌, 3 : 좌상
				for (int t = 0; t < 2; t++) {
					nd = (d + t + 3) % 4;
					ni = i + dir[nd][0];
					nj = j + dir[nd][1];

					while (true) {
						if (!check(ni, nj, newMap))
							break;
						newMap[ni][nj] = newMap[ni][nj] == 0 ? 7 : newMap[ni][nj];
						ni += dir[nd][0];
						nj += dir[nd][1];
					}
				}
				break;
			case 4: // 0 : 상우하, 1 : 우하좌, 2 : 하좌상, 3 : 좌상우
				for (int t = 0; t < 3; t++) {
					nd = (d + t + 3) % 4;
					ni = i + dir[nd][0];
					nj = j + dir[nd][1];

					while (true) {
						if (!check(ni, nj, newMap))
							break;
						newMap[ni][nj] = newMap[ni][nj] == 0 ? 7 : newMap[ni][nj];
						ni += dir[nd][0];
						nj += dir[nd][1];
					}
				}
				break;
			}

			answer = Math.min(checkBlindSpot(newMap), answer);
		}
	}

	public static boolean check(int i, int j, int[][] newMap) {
		if (i >= 0 && i < N && j >= 0 && j < M && newMap[i][j] != 6)
			return true;
		return false;
	}

	public static int checkBlindSpot(int[][] newMap) {
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (newMap[i][j] == 0)
					result++;
			}
		}

		return result;
	}

	public static void print(int[][] newMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(newMap[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
