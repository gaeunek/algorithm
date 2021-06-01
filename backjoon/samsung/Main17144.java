package samsung;

import java.util.*;
import java.io.*;


// 메모리 잡아먹는 순 Queue > List >>>>>>>> 배열
// 별짓 다해도 그냥 배열로 끝내는게 훨씬 메모리 적게 든다.
public class Main17144 {
	static int R, C, T;
	static int[][] map;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static ArrayList<Point> airCleanerPointList = new ArrayList<>();

	static boolean check(int i, int j) {
		if (i >= 0 && i < R && j >= 0 && j < C)
			return true;
		return false;
	}

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) throws IOException {
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
					airCleanerPointList.add(new Point(i, j));
				} 
			}
		}

		for (int i = 0; i < T; i++) {
			bfs(); // 미세먼지 확산
			onAirCleaner(); // 공기청정기 가동
		}

		int answer = 0;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] > 0) answer += map[i][j];
			}
		}

		System.out.println(answer);
	}

	/* 공기청정기 가동 */
	public static void onAirCleaner() {
		int[][] newMap = new int[R][C];

		for (int k = 0; k < airCleanerPointList.size(); k++) {
			Point p = airCleanerPointList.get(k);
			int d = 0;
			newMap[p.i][p.j] = -1;
			int i = p.i + dir[d][0], j = p.j + dir[d][1], ni = i, nj = j;

			while (true) {
				ni = i + dir[d][0];
				nj = j + dir[d][1];

				if (ni == p.i && nj == p.j)
					break;

				if (!check(ni, nj)) {
					ni -= dir[d][0];
					nj -= dir[d][1];
					d = (k == 0 ? (d + 3) : (d + 1)) % 4;
					ni += dir[d][0];
					nj += dir[d][1];
				}

				newMap[ni][nj] = map[i][j];
				i = ni;
				j = nj;
			}

			int st = k == 0 ? 1 : airCleanerPointList.get(k).i + 1;
			int end = k == 0 ? airCleanerPointList.get(k).i : R - 1;
			for (int r = st; r < end; r++) {
				for (int c = 1; c < C - 1; c++) {
					newMap[r][c] = map[r][c];
				}
			}
		}
		
		map = newMap;
	}

	/* 미세먼지 확산 */
	public static void bfs() {
		int[][] newMap = new int[R][C];
		for (Point p : airCleanerPointList) {
			newMap[p.i][p.j] = -1;
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] > 0) {
					int count = 0;
					int dust = map[i][j];
					for (int d = 0; d < 4; d++) {
						int ni = i + dir[d][0];
						int nj = j + dir[d][1];
		
						if (check(ni, nj) && map[ni][nj] != -1) {
							newMap[ni][nj] += dust / 5;
							count++;
						}
					}
					
					newMap[i][j] += map[i][j] - (dust / 5) * count;
				}
			}
		}

		map = newMap;
	}
}