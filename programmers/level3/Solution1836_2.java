package level3;

import java.util.*;

//리틀 프렌즈 사천성
class Solution1836_2 {
	// 정렬 후 dfs를 이용한 백트래킹
	static char[][] map;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static List<Character> tile;
	static Point[][] alpha;
	static int N, M;
	static String answer = "";

	static boolean check(int i, int j) {
		if (i >= 0 && i < M && j >= 0 && j < N)
			return true;
		return false;
	}

	public String solution(int m, int n, String[] board) {
		M = m;
		N = n;
		map = new char[m][n];
		tile = new ArrayList<>();
		alpha = new Point[26][2];

		for (int i = 0; i < m; i++) {
			map[i] = board[i].toCharArray();

			for (int j = 0; j < n; j++) {
				if (map[i][j] == '.' || map[i][j] == '*')
					continue;

				if (!tile.contains(map[i][j])) {
					tile.add(map[i][j]);
					alpha[map[i][j] - 'A'][0] = new Point(i, j);
				} else
					alpha[map[i][j] - 'A'][1] = new Point(i, j);
			}
		}

		Collections.sort(tile);
		answer = "";
		find(new boolean[tile.size()], "");
		return answer == "" ? "IMPOSSIBLE" : answer;
	}

	static void find(boolean[] used, String result) {
		if (result.length() == used.length) {
			answer = result;
			return;
		}

		for (int i = 0; i < used.length; i++) {
			if (!used[i]) {
				used[i] = true;
				Point st = alpha[tile.get(i) - 'A'][0];
				Point end = alpha[tile.get(i) - 'A'][1];

				dfs(new boolean[M][N], st, end, -1, false);

				if (map[end.i][end.j] == '.') {
					map[st.i][st.j] = '.';
					find(used, result += tile.get(i));
				} else
					used[i] = false;
			}
		}
	}

	static void dfs(boolean[][] visited, Point st, Point end, int k, boolean coner) {
		if (st.i == end.i && st.j == end.j) {
			map[end.i][end.j] = '.';
			return;
		}

		visited[st.i][st.j] = true;

		for (int d = 0; d < 4; d++) {
			int ni = st.i + dir[d][0];
			int nj = st.j + dir[d][1];

			if (check(ni, nj) && !visited[ni][nj] && (map[ni][nj] == '.' || map[ni][nj] == map[end.i][end.j])) {
				if (k != -1 && k != d && coner)
					continue;

				boolean newConer = (k != -1 && k != d) || coner ? true : false;
				dfs(visited, new Point(ni, nj), end, d, newConer);
			}
		}

		visited[st.i][st.j] = false;
	}

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

	static void print() {
		for (int i = 0; i < M; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}

	public static void main(String[] args) {
		Solution1836_2 sol = new Solution1836_2();
//		System.out.println(sol.solution(3, 3, new String[] { "DBA", "C*A", "CDB" }));
//		System.out.println(sol.solution(5, 5, new String[] { "FGHEI", "BAB..", "D.C*.", "CA..I", "DFHGE" })); //ABCDFHGIE
//		System.out.println(sol.solution(2, 2, new String[] {"AB", "BA"}));
		System.out.println(sol.solution(2, 4, new String[] { "NRYN", "ARYA" }));
	}
}
