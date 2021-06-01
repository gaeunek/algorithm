package samsung;

import java.io.*;
import java.util.*;

// visited 배열의 계속된 선언으로 인한 시간초과 O(nm)
// bfs 방문처리 오류 (넣기 전말고 뺄때 방문처리 해줘야 한다.)
public class Main14500 {
	static int N, M, answer;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static boolean check(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < M)
			return true;
		return false;
	}

	static class Point implements Comparable<Point> {
		int i, j, value;

		public Point(int i, int j, int value) {
			this.i = i;
			this.j = j;
			this.value = value;
		}

		@Override
		public int compareTo(Point o) {
			return o.value - this.value;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", value=" + value + "]";
		}

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
		bfs();
		System.out.println(answer);
	}

	public static void bfs() {
		PriorityQueue<Point> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				pq.add(new Point(i, j, map[i][j]));
				clearVisited();

				for (int k = 0; k < 3; k++) {
					Point now = pq.poll();
					visited[now.i][now.j] = true;

					for (int d = 0; d < 4; d++) {
						int ni = now.i + dir[d][0];
						int nj = now.j + dir[d][1];

						if (check(ni, nj) && !visited[ni][nj]) {
							pq.add(new Point(ni, nj, now.value + map[ni][nj]));
						}
					}
				}

				answer = Math.max(pq.poll().value, answer);
				pq.clear();

				// ㅗ 모양에 대한 예외처리
				exception(i, j);
			}
		}
	}

	public static void clearVisited() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
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