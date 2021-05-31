package kakao;

import java.util.*;

// 경주로 건설 약 1시간 정도 걸림
// 객체를 하나 만들어서 위치 i, j랑 방향 d, 비용 cost를 저장해준다.
class Solution67259 {
	static int N;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static boolean isRange(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < N)
			return true;
		return false;
	}

	static class Point {
		int i, j, d, cost;

		public Point(int i, int j, int d, int cost) {
			this.i = i;
			this.j = j;
			this.d = d;
			this.cost = cost;
		}
	}

	public int solution(int[][] board) {
		N = board.length;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 0)
					board[i][j] = Integer.MAX_VALUE;
			}
		}

		return bfs(board);
	}

	public static int bfs(int[][] board) {
		Queue<Point> queue = new LinkedList<>();

		// 0 : 오른쪽, 1 : 아래, 2 : 왼쪽, 3 : 위
		queue.add(new Point(0, 0, 0, 0));
		queue.add(new Point(0, 0, 1, 0));

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			int i = now.i;
			int j = now.j;
			int d = now.d;
			int cost = now.cost;

			for (int k = 0; k < 4; k++) {
				int ni = i + dir[k][0];
				int nj = j + dir[k][1];

				if (isRange(ni, nj) && board[ni][nj] != 1 && board[ni][nj] >= cost + (d != k ? 600 : 100)) {
					board[ni][nj] = cost + (d != k ? 600 : 100);
					queue.add(new Point(ni, nj, k, cost + (d != k ? 600 : 100)));
				}
			}
		}

		return board[N - 1][N - 1];
	}

	public static void main(String[] args) {
		Solution67259 sol = new Solution67259();
		System.out.println(sol.solution(new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } }));
		System.out
				.println(sol.solution(new int[][] { { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 1, 0, 1 }, { 1, 0, 0, 0 } }));
		System.out.println(sol.solution(new int[][] { { 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 0 }, { 0, 0, 1, 0, 0, 0 },
				{ 1, 0, 0, 1, 0, 1 }, { 0, 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0 } }));
	}
}
