package kakao;

import java.util.*;
//블록이동 5:12
// 하 진짜 도저히 못풀겠다
// 그냥 이동 네가지와 회전이동을 따로 처리해준다.
// bfs
// 방문처리보단 최솟값을 갱신 ??
class Solution60063 {
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int N;

	static boolean isRange(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < N)
			return true;
		return false;
	}

	static class Point {
		int i1, j1, i2, j2, cost1, cost2;

		public Point(int i1, int j1, int i2, int j2, int cost1, int cost2) {
			this.i1 = i1;
			this.j1 = j1;
			this.i2 = i2;
			this.j2 = j2;
			this.cost1 = cost1;
			this.cost2 = cost2;
		}
	}

	public int solution(int[][] board) {
		N = board.length;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1)
					board[i][j] = -1;
				else
					board[i][j] = Integer.MAX_VALUE;
			}
		}
		print(board);

		return bfs(board);
	}

	public static int bfs(int[][] board) {
		Queue<Point> queue = new LinkedList<>();

		queue.add(new Point(0, 0, 0, 1, 0, 0));
		while (!queue.isEmpty()) {
			Point p = queue.poll();

			int i1 = p.i1;
			int j1 = p.j1;
			int i2 = p.i2;
			int j2 = p.j2;

			board[i1][j1] = p.cost1;
			board[i2][j2] = p.cost2;

			for (int d = 0; d < 4; d++) {
				int ni1 = i1 + dir[d][0];
				int nj1 = j1 + dir[d][1];
				int ni2 = i2 + dir[d][0];
				int nj2 = j2 + dir[d][1];

				if (isRange(ni1, nj1) && isRange(ni2, nj2) && board[ni1][nj1] != -1 && board[ni2][nj2] != -1) {
					int cost1 = p.cost1 + 1;
					int cost2 = p.cost2 + 1;

					if (board[ni1][nj1] >= cost1 || board[ni2][nj2] >= cost2) {
						queue.add(new Point(ni1, nj1, ni2, nj2, cost1, cost2));
					}
				}
			}

			// 회전 경우의 수
//			print(board);
		}

		return board[N - 1][N - 1];
	}

	public static void print(int[][] board) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == Integer.MAX_VALUE)
					System.out.print(0 + "\t");
				else
					System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Solution60063 sol = new Solution60063();
//		System.out.println(sol.solution(new int[][] { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 },
//				{ 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 } }));
		System.out.println(sol.solution(new int[][] { { 0, 0, 1, 1, 1 }, { 0, 0, 0, 1, 0 }, { 1, 0, 0, 1, 1 },
				{ 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 } }));
	}
}
