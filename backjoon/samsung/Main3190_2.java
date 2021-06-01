package samsung;

import java.io.*;
import java.util.*;

// 뱀 (시뮬레이션 문제)
public class Main3190_2 {
	static char[] X = new char[10001];
	static int N, K, L, time;
	static int[][] map;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 이 부분 계산 방식

	static boolean check(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < N)
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
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = 1; // 사과
		}

		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			X[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
		}

		bfs();
		System.out.println(time);
	}

	public static void bfs() {
		Deque<Point> snake = new ArrayDeque<Point>();
		Point startPoint = new Point(0, 0);
		snake.add(startPoint);

		int d = 0;
		time = 0;

		while (!snake.isEmpty()) {
			if (X[time] == 'L') {
				d = (d + 3) % 4;
			} else if (X[time] == 'D') {
				d = (d + 1) % 4;
			} // 직접 계산해서 식을 찾아야한다.

			time++;
			Point now = snake.peek(); // 현재 위치에서 이동 후 머리의 위치를 알기 위해 뺴지 않는다
			int ni = now.i + dir[d][0];
			int nj = now.j + dir[d][1];

			if (!check(ni, nj)) { // 벽에 부딪히면 종료
				return;
			}

			Point head = new Point(ni, nj);

			for (int k = 0; k < snake.size(); k++) {
				Point p = snake.pop();
				if (p.i == head.i && p.j == head.j)
					return;
				snake.add(p);
			}
			snake.push(head);

			if (map[ni][nj] == 1) { // 사과 없으면 꼬리 자르기
				map[ni][nj] = 0;
			} else {
				snake.pollLast(); // 꼬리를 뺀다.
			}
		}
	}
}
