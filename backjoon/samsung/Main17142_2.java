package samsung;

import java.io.*;
import java.util.*;

// 맵에 저장해서 바이러스 수를 매번 세어주는것보다 빈 공간을 count 해놓고 비교하는게 더 빠르다
public class Main17142_2 {
	static int N, M, totalZero, answer;
	static int[][] map;
	static Queue<Point> queue = new LinkedList<>();
	static ArrayList<Point> virusList = new ArrayList<>();
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

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

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					virusList.add(new Point(i, j));
				else if (map[i][j] == 0)
					totalZero++;
			}
		}

		answer = Integer.MAX_VALUE;
		permutation(new boolean[virusList.size()], 0, 0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	public static void permutation(boolean[] used, int count, int start) {
		if (count == M) {
			boolean[][] visited = new boolean[N][N];

			for (int i = 0; i < used.length; i++) {
				if (used[i]) {
					Point virus = virusList.get(i);
					queue.add(virus);
					visited[virus.i][virus.j] = true;
				}
			}

			bfs(visited);
			queue.clear();
			return;
		}

		for (int i = start; i < used.length; i++) {
			if (!used[i]) {
				used[i] = true;
				permutation(used, count + 1, i + 1);
				used[i] = false;
			}
		}
	}

	public static void bfs(boolean[][] visited) {
		int time = 0;
		int countZero = 0;

		while (true) {
			int qSize = queue.size();
			boolean isVirus = false;
			
			if (countZero == totalZero) {
				if (answer == -1)
					answer = time;
				else
					answer = Math.min(time, answer);
				return;
			}
			
			time++;

			for (int s = 0; s < qSize; s++) {
				Point p = queue.poll();
				visited[p.i][p.j] = true;

				for (int d = 0; d < 4; d++) {
					int ni = p.i + dir[d][0];
					int nj = p.j + dir[d][1];

					if (check(ni, nj) && map[ni][nj] != 1 && !visited[ni][nj]) {
						if (!isVirus)
							isVirus = true;
						if (map[ni][nj] == 0)
							countZero++;
						visited[ni][nj] = true;
						queue.add(new Point(ni, nj));
					}
				}
			}
			
			if (!isVirus)
				break;
		}

		if(answer == Integer.MAX_VALUE && totalZero - countZero > 0) answer = -1;
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
