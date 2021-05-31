package level3;

import java.util.*;

// 블록 이동하기 (포기)
class Solution60063 {
	static int N, answer;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int[][] rotate = { { 0, 1 }, { 1, 0 }, { 1, 1 } };

	static boolean isRange(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < N)
			return true;
		return false;
	}

	public int solution(int[][] board) {
		answer = Integer.MAX_VALUE;
		N = board.length;

		boolean[][] visit = new boolean[N][N];
		visit[0][0] = true;
		dfs(0, 1, 0, 0, 0, board, visit);

		return answer;
	}

	/* 갈 수 있는 경로를 찾는다 */
	public static void dfs(int i, int j, int front_i, int front_j, int count, int[][] board, boolean[][] origin_visit) {
		if (i == N - 1 && j == N - 1) {
			answer = Math.min(count, answer);
//			System.out.println(count);
//			print(origin_visit);
			return;
		}

		boolean[][] visit = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				visit[r][c] = origin_visit[r][c];
			}
		}

		visit[i][j] = true;

		// 4방향 탐색
		for (int d = 0; d < 4; d++) {
			int ni = i + dir[d][0];
			int nj = j + dir[d][1];

			if (isRange(ni, nj) && board[ni][nj] == 0 && !visit[ni][nj]) {
				if (getDistance(front_i, front_j, ni, nj) == 1) {
					if (!check(front_i, front_j, i, j, ni, nj, board))
						continue;
				}

				visit[ni][nj] = true;
				dfs(ni, nj, i, j, count + 1, board, visit);
				visit[ni][nj] = false;
			}
		}
	}

	public static boolean check(int front_i, int front_j, int i, int j, int ni, int nj, int[][] board) {
		int dist_i = front_i - ni;
		int dist_j = front_j - nj;

		int newi = dist_i + front_i;
		int newj = front_j;
		if (isRange(newi, newj) && newi != i && newj != j && board[newi][newj] == 0)
			return true;

		newi = front_i;
		newj = dist_j + front_j;
		if (isRange(newi, newj) && newi != i && newj != j && board[front_i][dist_j + front_j] == 0)
			return true;

		return false;
	}

	public static int getDistance(int front_i, int front_j, int ni, int nj) {
		return Math.abs(front_i - ni) * Math.abs(front_i - nj);
	}

	public static void print(boolean[][] visit) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(visit[r][c] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Solution60063 sol = new Solution60063();
		System.out.println(sol.solution(new int[][] { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 },
				{ 0, 0, 0, 0, 0 } }));
//		System.out.println(sol.solution(new int[][] { { 0, 0, 1, 1, 1 }, { 0, 0, 0, 1, 0 }, { 1, 0, 0, 1, 1 },
//				{ 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 } }));
	}
}
