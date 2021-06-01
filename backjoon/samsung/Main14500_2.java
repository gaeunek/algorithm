package samsung;

import java.io.*;
import java.util.*;

// dfs로 풀면 vistied 배열로 시간을 단축시킬 수 있다.
// 이런 부분을 생각해서 bfs 쓸지 dfs 쓸지 잘 판단하길..
public class Main14500_2 {
	static int N, M, answer;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static boolean check(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < M)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, map[i][j], 1);
				exception(i, j);
				visited[i][j] = false;
			}
		}

		System.out.println(answer);
	}

	public static void dfs(int i, int j, int value, int count) {
		if (count == 4) {
			answer = Math.max(value, answer);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int ni = i + dir[d][0];
			int nj = j + dir[d][1];

			if (check(ni, nj) && !visited[ni][nj]) {
				visited[ni][nj] = true;
				dfs(ni, nj, value + map[ni][nj], count + 1);
				visited[ni][nj] = false;
			}
		}
	}

	public static void exception(int i, int j) {
		int sum;
		for (int d = 0; d < 4; d++) {
			sum = map[i][j];
			for (int k = 0; k < 3; k++) {
				int nd = (d + k + 3) % 4;
				int ni = i + dir[nd][0];
				int nj = j + dir[nd][1];

				if (check(ni, nj))
					sum += map[ni][nj];
			}

			answer = Math.max(sum, answer);
		}
	}
}