package samsung;

import java.io.*;
import java.util.*;

// 메모리 초과 실패
public class Main3190 {
	static char[] X = new char[10001];
	static int[][] map, snake;
	static int N, K, L, answer;
	static int[][] dir = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static boolean check(int i, int j) {
		if (i >= 1 && i <= N && j >= 1 && j <= N)
			return true;
		return false;
	}
	
	static class Point {
		int i, j, time, d;
		int[][] snake;
	}
	
	static Point startPoint = new Point();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1; // 사과
		}

		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			X[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
		}

		snake = new int[N + 1][N + 1];

		snake[1][1] = 1;
		startPoint.i = 1;
		startPoint.j = 1;
		startPoint.time = 0;
		startPoint.d = 1;
		
		bfs();
		System.out.println(answer);
	}

	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(startPoint);
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			int hi = p.i, hj = p.j, time = p.time, d = p.d;
			int ni = 0, nj = 0;

			if (X[time] == 'D' || X[time] == 'L') {
				int[] rotate = rotate(hi, hj, d, X[time]);
				ni = rotate[0];
				nj = rotate[1];
				d = rotate[2];
				snake[hi][hj] = d;
			} else {
				ni = hi + dir[d][0];
				nj = hj + dir[d][1];
			}

			if (!check(ni, nj) || snake[ni][nj] > 0) {
				answer = time + 1;
				return;
			}

			// 사과가 있다!
			if (map[ni][nj] == 1) {
				map[ni][nj] = 0;
				snake[ni][nj] = d;
			} else {
				// 뱀 꼬리 삭제, 머리 추가
				int[][] moveSnake = new int[N + 1][N + 1];
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (snake[i][j] > 0) {
							int dist = snake[i][j];
							moveSnake[i + dir[dist][0]][j + dir[dist][1]] = dist;
						}
					}
				}

				snake = copy(moveSnake);
				
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (moveSnake[i][j] > 0) {
							int dist = moveSnake[i][j];
							int bi = i - dir[dist][0];
							int bj = j - dir[dist][1];

							if (moveSnake[bi][bj] > 0)
								snake[bi][bj] = moveSnake[i][j];
						}
					}
				}
			}
			
			Point newPoint = new Point();
			newPoint.i = ni;
			newPoint.j = nj;
			newPoint.time = time + 1;
			newPoint.d = d;
			queue.add(newPoint);
		}
	}

	public static int[][] copy(int[][] origin) {
		int[][] copy = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		return copy;
	}

	public static int[] rotate(int i, int j, int d, char c) {
		if (c == 'D') {
			switch (d) {
			case 1:
				return new int[] { i + 1, j, 2 };
			case 2:
				return new int[] { i, j - 1, 3 };
			case 3:
				return new int[] { i - 1, j, 4 };
			default:
				return new int[] { i, j + 1, 1 };
			}
		} else {
			switch (d) {
			case 1:
				return new int[] { i - 1, j, 4 };
			case 2:
				return new int[] { i, j + 1, 1 };
			case 3:
				return new int[] { i + 1, j, 2 };
			default:
				return new int[] { i, j - 1, 3 };
			}
		}
	}

	public static void print(int[][] map) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
