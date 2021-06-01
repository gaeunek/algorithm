package samsung;

import java.io.*;
import java.util.*;

public class Main14502 {
	static int N, M, total, answer;
	static int[][] map;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static ArrayList<Point> zeroList = new ArrayList<>();
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
		total = N * M;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					total--;
				else if (map[i][j] == 2)
					virus.add(new Point(i, j));
				else
					zeroList.add(new Point(i, j));
			}
		}

		answer = 0;
		comb(0, new boolean[zeroList.size()], 0);
		System.out.println(answer);
	}

	public static void comb(int index, boolean[] used, int wall) {
		if (wall == 3) {
			int[][] newMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					newMap[i][j] = map[i][j];
				}
			}
			
			// 새로운 벽 3개를 추가한다
			for (int i = 0; i < used.length; i++) {
				if(used[i]) {
					Point p = zeroList.get(i);
					newMap[p.i][p.j] = 1;
				}
			}
			
			answer = Math.max(bfs(newMap), answer);
			return;
		}

		for (int i = index; i < used.length; i++) {
			if (!used[i]) {
				used[i] = true;
				comb(i + 1, used, wall + 1);
				used[i] = false;
			}
		}
	}
	
	public static int bfs(int[][] newMap) {
		Queue<Point> queue = new LinkedList<>();
		for (int i = 0; i < virus.size(); i++) {
			queue.add(virus.get(i));
		}
		
		boolean[][] visited = new boolean[N][M];
		int virusCount = queue.size();
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			visited[now.i][now.j] = true;
			
			for (int d = 0; d < 4; d++) {
				int ni = now.i + dir[d][0];
				int nj = now.j + dir[d][1];
				
				if(check(ni, nj) && !visited[ni][nj] && newMap[ni][nj] == 0) {
					visited[ni][nj] = true;
					virusCount++;
					queue.add(new Point(ni, nj));
				}
			}
		}
		
		return total - 3 - virusCount;
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
