package samsung;

import java.io.*;
import java.util.*;

public class Main17142 {
	static int N, M, answer;
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
			}
		}

		answer = Integer.MAX_VALUE;
		permutation(new boolean[virusList.size()], 0, 0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
	public static void permutation(boolean[] used, int count, int start) {
		if (count == M) {
			boolean[][] visited = new boolean[N][N];
			int[][] newMap = new int[N][N];
			
			for (int i = 0; i < used.length; i++) {
				if (used[i]) {
					Point virus = virusList.get(i);
					queue.add(virus);
					visited[virus.i][virus.j] = true;
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					newMap[i][j] = map[i][j];
				}
			}
			
			bfs(visited, newMap);
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

	public static void bfs(boolean[][] visited, int[][] newMap) {
		int time = 0;
		boolean result = false;
		
		while (true) {
			int qSize = queue.size();
			boolean isVirus = false;
			result = checkArea(newMap); // 비활성화된 2가 구석에 있을때 그거까지 탐색하러 들어가다보니 값이 늘어난다.
			if(result) break;
			time++;
			
			for (int s = 0; s < qSize; s++) {
				Point p = queue.poll();
				visited[p.i][p.j] = true;

				for (int d = 0; d < 4; d++) {
					int ni = p.i + dir[d][0];
					int nj = p.j + dir[d][1];

					if (check(ni, nj) && newMap[ni][nj] != 1 && !visited[ni][nj]) {
						if(!isVirus) isVirus = true;
						visited[ni][nj] = true;
						newMap[ni][nj] = 2;
						queue.add(new Point(ni, nj));
					}
				}
			}
			
			if(!isVirus) break;
		}
		
		if (result) {
			if (answer == -1)
				answer = time;
			else
				answer = Math.min(time, answer);
		} else if (!result && answer == Integer.MAX_VALUE)
			answer = -1;

	}

	public static boolean checkArea(int[][] newMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (newMap[i][j] == 0)
					return false;
			}
		}

		return true;
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
