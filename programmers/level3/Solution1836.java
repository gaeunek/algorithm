package level3;

import java.util.*;

class Solution1836 {
	static List<Character> tileList;
	static Set<String> set;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static Point[][] alpha;
	static int N, M;
	static List<String> result;

	static boolean check(int i, int j) {
		if (i >= 0 && i < M && j >= 0 && j < N)
			return true;
		return false;
	}

	public String solution(int m, int n, String[] board) {
		M = m;
		N = n;
		alpha = new Point[26][2];
		tileList = new ArrayList<>();

		char[][] originMap = new char[M][N];

		for (int i = 0; i < m; i++) {
			char[] tmp = board[i].toCharArray();
			originMap[i] = tmp;

			for (int j = 0; j < n; j++) {
				if (tmp[j] == '.' || tmp[j] == '*')
					continue;

				if (!tileList.contains(tmp[j])) {
					tileList.add(tmp[j]);
					alpha[tmp[j] - 'A'][0] = new Point(i, j);
				} else {
					alpha[tmp[j] - 'A'][1] = new Point(i, j);
				}
			}
		}

		boolean[] used = new boolean[tileList.size()];
		set = new HashSet<>();
		permutation(used, "");

		Iterator<String> it = set.iterator();
		result = new ArrayList<>();

		while (it.hasNext()) {
			find(it.next(), originMap);
		}

		if (result.size() == 0)
			return "IMPOSSIBLE";
		Collections.sort(result);
		return result.get(0);
	}

	static void find(String s, char[][] originMap) {
		char[][] map = new char[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = originMap[i][j];
			}
		}

		for (int i = 0; i < s.length(); i++) {
			Point st = alpha[s.charAt(i) - 'A'][0];
			Point end = alpha[s.charAt(i) - 'A'][1];

			if (!tileRemoveCheck(st, end, map))
				return;
		}

		result.add(s);
	}

	static boolean tileRemoveCheck(Point st, Point end, char[][] map) {
		boolean[][] visited = new boolean[M][N];

//		System.out.println(map[st.i][st.j]);
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(st.i, st.j, -1, 0));

		// 경로가 여러개일 수 있다.
		// visited 처리로 인해 최종경로에 있는 Point 값이 coner값을 가지게 될 수 있다.
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			visited[now.i][now.j] = true;

//			System.out.println(now);
//			System.out.println(queue);
//			print(map);

			if (now.i == end.i && now.j == end.j) {
				map[st.i][st.j] = map[end.i][end.j] = '.';
				return true;
			}

			for (int d = 0; d < 4; d++) {
				int ni = now.i + dir[d][0];
				int nj = now.j + dir[d][1];

				if (check(ni, nj) && !visited[ni][nj] && (map[ni][nj] == '.' || map[ni][nj] == map[st.i][st.j])) {
					int coner = checkConer(now.d, d);
					if (now.coner + coner > 1)
						continue;
					
//					visited[ni][nj] = true;
					queue.add(new Point(ni, nj, d, now.coner + coner));
				}
			}
		}

		return false;
	}

	static int checkConer(int now, int next) {
		if (now != -1 && now != next)
			return 1;
		return 0;
	}

	static void permutation(boolean[] used, String result) {
		if (result.length() == used.length) {
			String tmp = result;
			set.add(tmp);
		}

		for (int i = 0; i < used.length; i++) {
			if (!used[i]) {
				used[i] = true;
				permutation(used, result + tileList.get(i));
				used[i] = false;
			}
		}
	}

	static void print(char[][] arr) {
		for (int i = 0; i < M; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}

	static class Point {
		int i, j, d, coner;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		public Point(int i, int j, int d, int coner) {
			this.i = i;
			this.j = j;
			this.coner = coner;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", d=" + d + ", coner=" + coner + "]";
		}

//		@Override
//		public String toString() {
//			return "Point [i=" + i + ", j=" + j + "]";
//		}

	}

	public static void main(String[] args) {
		Solution1836 sol = new Solution1836();
		System.out.println(sol.solution(3, 3, new String[] { "DBA", "C*A", "CDB" }));
		System.out.println(sol.solution(5, 5, new String[] { "FGHEI", "BAB..", "D.C*.", "CA..I", "DFHGE" })); //ABCDFHGIE
	}
}
