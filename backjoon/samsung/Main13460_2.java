package samsung;

import java.io.*;
import java.util.*;

public class Main13460_2 {
	static Queue<Point> queue = new LinkedList<>();
	static char[][] map;
	static boolean visited[][][][];
	static int N, M;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static class Point {
		int rx, ry, bx, by, count;

		@Override
		public String toString() {
			return "Point [rx=" + rx + ", ry=" + ry + ", bx=" + bx + ", by=" + by + ", count=" + count + "]";
		}
	}

	static Point startPoint = new Point();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M][N][M];

		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j);
				if (map[i][j] == 'R') {
					startPoint.rx = i;
					startPoint.ry = j;
				}

				if (map[i][j] == 'B') {
					startPoint.bx = i;
					startPoint.by = j;
				}
			}
		}

		startPoint.count = 0;
		System.out.println(bfs());
	}

	public static int bfs() {
		int result = -1;

		/* 1. 큐에 시작점 넣고 방문처리 해주기 */
		queue.add(startPoint);
		visited[startPoint.rx][startPoint.ry][startPoint.bx][startPoint.by] = true;

		/* 2. bfs 돌리기 */
		while (!queue.isEmpty()) {
			Point now = queue.poll();

			if (now.count > 10)
				break; // 10번 이하로만 굴리기

			if (map[now.rx][now.ry] == 'O' && map[now.bx][now.by] != 'O') {
				result = now.count;
				break;
			}

			/* 4방향 탐색 */
			for (int d = 0; d < 4; d++) {
				int nrx = now.rx;
				int nry = now.ry;
				int nbx = now.bx;
				int nby = now.by;

				// 공 굴리기
				int[] red = move(nrx, nry, d);
				nrx = red[0];
				nry = red[1];
				int[] blue = move(nbx, nby, d);
				nbx = blue[0];
				nby = blue[1];

				// 빨간공과 파란공 위치가 같으면 위치를 수정해준다.
				if (nrx == nbx && nry == nby) {
					if (map[nrx][nry] != 'O') {
						int rdist = Math.abs(nrx - now.rx) + Math.abs(nry - now.ry);
						int bdist = Math.abs(nbx - now.bx) + Math.abs(nby - now.by);

						if (rdist > bdist) { // 빨간공이 파란공보다 뒤에 있다.
							nrx -= dir[d][0];
							nry -= dir[d][1];
						} else {
							nbx -= dir[d][0];
							nby -= dir[d][1];
						}
					}
				}

				/* 4. 방문 안한 Point를 큐에 넣고 방문처리 해준다. */
				if (!visited[nrx][nry][nbx][nby]) {
					Point newPoint = new Point();
					newPoint.rx = nrx;
					newPoint.ry = nry;
					newPoint.bx = nbx;
					newPoint.by = nby;
					newPoint.count = now.count + 1;

					queue.add(newPoint);
					visited[nrx][nry][nbx][nby] = true;
				}
			}
		}

		return result;
	}

	public static int[] move(int x, int y, int d) {
		while (true) {
			if (map[x][y] != '#' && map[x][y] != 'O') {
				x += dir[d][0];
				y += dir[d][1];
			} else {
				if (map[x][y] == '#') {// 'O'일 수 있으니까
					x -= dir[d][0];
					y -= dir[d][1];
				}

				break;
			}
		}

		return new int[] { x, y };
	}
}
