package samsung;

import java.io.*;
import java.util.*;

public class Main13460 {
	static int N, M;
	static Point goal;
	static boolean[][][][] visited;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		char[][] map = new char[N][M];
		Point bead = new Point(0, 0, 0, 0);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j);
				if (map[i][j] == 'R') {
					bead.ri = i;
					bead.rj = j;
				} else if (map[i][j] == 'B') {
					bead.bi = i;
					bead.bj = j;
				} else if (map[i][j] == 'O')
					goal = new Point(i, j);
			}
		}

		visited = new boolean[N][M][N][M];
		Queue<Point> queue = new LinkedList<>();
		queue.add(bead);
		System.out.println(bfs(map, queue));
	}

	public static int bfs(char[][] map, Queue<Point> queue) {
		int count = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int k = 0; k < size; k++) {
				Point p = queue.poll();
				System.out.println(p);
				visited[p.ri][p.rj][p.bi][p.bj] = true;

				for (int d = 0; d < 4; d++) {
					int nri = p.ri + dir[d][0];
					int nrj = p.rj + dir[d][1];
					int nbi = p.bi + dir[d][0];
					int nbj = p.bj + dir[d][1];

					int ri = nri, rj = nrj, bi = nbi, bj = nbj;

					// r 굴리고 b굴리고 큐에 넣음
					int[] red = move(map, ri, rj, d);
					int[] blue = move(map, bi, bj, d);

					if (blue[0] == goal.i && blue[1] == goal.j) {
						continue;
					} else if (red[0] == goal.i && red[1] == goal.j) {
						return count;
					} else if (red[0] == blue[0] && red[1] == blue[1]) {
						// 구슬이 같은 위치라면
						if (p.ri == p.bi) { // 같은 행이라면
							if (d == 0) {
								if (p.rj < p.bj)
									red[1]--;
								else
									blue[1]--;
							} else if (d == 2) {
								if (p.rj > p.bj)
									red[1]++;
								else
									blue[1]++;
							}
						} else if (p.rj == p.bj) {
							if (d == 1) {
								if (p.ri < p.bi)
									red[0]--;
								else
									blue[0]--;
							} else if (d == 3) {
								if (p.ri > p.bi)
									red[0]++;
								else
									blue[0]++;
							}
						}
					}

					if(!visited[nri][nrj][nbi][nbj])
						queue.add(new Point(red[0], red[1], blue[0], blue[1]));
				}
			}

			count++;

			if (count > 10) {
				return -1;
			}
		}

		return count;
	}

	public static int[] move(char[][] map, int i, int j, int d) {
		while (true) {
			if (map[i][j] == 'O') {
				break;
			}

			if (map[i][j] == '#') {
				switch (d) {
				case 0:
					return new int[] { i, j - 1 };
				case 1:
					return new int[] { i - 1, j };
				case 2:
					return new int[] { i, j + 1 };
				default:
					return new int[] { i + 1, j };
				}
			}

			switch (d) {
			case 0:
				j++;
				break;
			case 1:
				i++;
				break;
			case 2:
				j--;
				break;
			default:
				i--;
				break;
			}
		}

		return new int[] { i, j };
	}

	public static void print(char[][] map, boolean[][] visited) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println();
	}

	public static class Point {
		int ri, rj, bi, bj;
		int i, j;

		public Point(int ri, int rj, int bi, int bj) {
			this.ri = ri;
			this.rj = rj;
			this.bi = bi;
			this.bj = bj;
		}

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Point [ri=" + ri + ", rj=" + rj + ", bi=" + bi + ", bj=" + bj + "]";
		}
	}
}
