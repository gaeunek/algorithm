package samsung;

import java.io.*;
import java.util.*;

public class Main16234 {
	static int N, L, R;
	static int[][] country, map;
	static ArrayList<int[]> openList;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static boolean check(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < N)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		country = new int[N][N];
		map = new int[N][N];
		openList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				country[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		while (true) {
			int number = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0)
						continue;

					bfs(i, j, number++);
				}
			}

			if (openList.size() == 0)
				break;

			for (int k = 0; k < openList.size(); k++) {
				int country_number = openList.get(k)[0];
				int value = openList.get(k)[1] / openList.get(k)[2];

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] == country_number)
							country[i][j] = value;
					}
				}
			}
			
			// map 초기화
			for (int i = 0; i < N; i++) {
				Arrays.fill(map[i], 0);
			}

			answer++;
			openList.clear();
		}

		System.out.println(answer);
	}

	public static void bfs(int i, int j, int number) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(i, j));
		
		int sum = 0, count = 1;
		map[i][j] = number;
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			sum += country[now.i][now.j];

			for (int d = 0; d < 4; d++) {
				int ni = now.i + dir[d][0];
				int nj = now.j + dir[d][1];

				if (check(ni, nj) && map[ni][nj] == 0) {
					int result = Math.abs(country[now.i][now.j] - country[ni][nj]);
					if (result >= L && result <= R) {
						map[ni][nj] = number;
						count++;
						queue.add(new Point(ni, nj));
					}
				}
			}
		}

		if (count > 1)
			openList.add(new int[] { number, sum, count});
	}

	public static void print(int[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
