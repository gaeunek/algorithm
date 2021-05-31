package level3;

import java.util.*;

// 포기
class Solution60063_2 {
	static int N, answer;
	static int[][] dir = { { 0, 1, 0, 1 }, { 1, 0, 1, 0 }, { 0, -1, 0, -1 }, { -1, 0, -1, 0 }, { 1, 1, 0, 0 },
			{ 0, 0, 1, -1 }, { 0, 0, -1, 1 }, { 0, 0, -1, -1 }, { 1, -1, 0, 0 }, { -1, 1, 0, 0 }, { 0, 0, -1, -1 } };

	static boolean isRange(int i1, int j1, int i2, int j2) {
		if (i1 >= 0 && i1 < N && j1 >= 0 && j1 < N && i2 >= 0 && i2 < N && j2 >= 0 && j2 < N)
			return true;
		return false;
	}

	public int solution(int[][] board) {
		answer = Integer.MAX_VALUE;
		N = board.length;

		boolean[][] visit = new boolean[N][N];
		bfs(board);

		return answer;
	}

	/* 갈 수 있는 경로를 찾는다 */
	public static void bfs(int[][] board) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][][] visit = new boolean[N][N][N][N];
		queue.add(new int[] { 0, 0, 0, 1, 0 }); // 로봇위치, 움직인 횟수

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			int i1 = now[0];
			int j1 = now[1];
			int i2 = now[2];
			int j2 = now[3];
			int move = now[4];
			visit[i1][j1][i2][j2] = true;
			
			if(i2 == N - 1 && j2 == N - 1) {
				answer = Math.min(move, answer);
				continue;
			}
			
			for (int d = 0; d < 11; d++) {
				int ni1 = i1 + dir[d][0];
				int nj1 = j1 + dir[d][1];
				int ni2 = i2 + dir[d][2];
				int nj2 = j2 + dir[d][3];

				if (isRange(ni1, nj1, ni2, nj2) && !visit[ni1][nj1][ni2][nj2] && board[ni1][nj2] == 0
						&& board[ni2][nj2] == 0) {
					queue.add(new int[] {ni1, nj1, ni2, nj2, move + 1});
				}
			}
		}
		
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
		Solution60063_2 sol = new Solution60063_2();
//		System.out.println(sol.solution(new int[][] { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 },
//				{ 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 } }));
		System.out.println(sol.solution(new int[][] { { 0, 0, 1, 1, 1 }, { 0, 0, 0, 1, 0 }, { 1, 0, 0, 1, 1 },
				{ 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 } }));
	}
}
