package samsung;

import java.io.*;
import java.util.*;

// 시간은 0.7 ms 정도 빠르지만 메모리는 20,000 kb 더 큼
public class Main14502_2 {
	static int N, M, total, answer;
	static int[][] map;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static ArrayList<Point> virus = new ArrayList<>();

	static boolean check(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < M)
			return true;
		return false;
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		total = N * M - 3;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					total--;
				else if (map[i][j] == 2)
					virus.add(new Point(i, j));
			}
		}

		answer = 0;
		comb(0, 0);
		System.out.println(answer);
	}

	public static void comb(int ni, int wall) { // nj까지 정해버리면 i가 다음으로 넘어갔을 때 시작점이 0이 아니라 nj가 되어버리니까
		if (wall == 3) {
			answer = Math.max(bfs(), answer);
			return;
		}

		for (int i = ni; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					comb(i, wall + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	public static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		for (int i = 0; i < virus.size(); i++) {
			queue.add(virus.get(i));
		}

		boolean[][] visited = new boolean[N][M];
		int virusCount = queue.size();

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			visited[now.i][now.j] = true;

			for (int d = 0; d < 4; d++) {
				int ni = now.i + dir[d][0];
				int nj = now.j + dir[d][1];

				if (check(ni, nj) && !visited[ni][nj] && map[ni][nj] == 0) {
					visited[ni][nj] = true;
					virusCount++;
					queue.add(new Point(ni, nj));
				}
			}
		}

		return total - virusCount;
	}

	public static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
