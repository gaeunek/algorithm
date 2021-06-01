package samsung;

import java.io.*;
import java.util.*;

public class Main16236 {
	static int N, answer, shark_w, eat_count, shark_i, shark_j;
	static int[][] map;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static boolean[][] visited;

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
		map = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark_i = i;
					shark_j = j;
				}
			}
		}

		answer = 0;
		shark_w = 2;
		eat_count = 0;
		bfs();
		System.out.println(answer);
	}

	public static void bfs() {
		
		while(true) {
			Queue<Point> queue = new LinkedList<>();
			queue.add(new Point(shark_i, shark_j));
			visited = new boolean[N][N];
			visited[shark_i][shark_j] = true;
			
			int feedi = N, feedj = N;
			int dist = 0;
			boolean isEat = false;
			
			while(!queue.isEmpty()) {
				int qSize = queue.size();
				
				for (int s = 0; s < qSize; s++) {
					Point p = queue.poll();
					
					for (int d = 0; d < 4; d++) {
						int ni = p.i + dir[d][0];
						int nj = p.j + dir[d][1];
						
						if(check(ni, nj) && !visited[ni][nj]) {
							visited[ni][nj] = true;
							if(map[ni][nj] < shark_w && map[ni][nj] > 0) {
								if(feedi > ni) {
									feedi = ni;
									feedj = nj;
								} else if(feedi == ni && feedj > nj) {
									feedi = ni;
									feedj = nj;
								}
							} else if(map[ni][nj] == 0 || map[ni][nj] <= shark_w) queue.add(new Point(ni, nj));
						} 
					}
				}
				dist++;
				
				if(feedi != N && feedj != N) {
					isEat = true;
					map[shark_i][shark_j] = 0;
					shark_i = feedi;
					shark_j = feedj;
					answer += dist;
					eat_count++;
					if(eat_count == shark_w) {
						shark_w++;
						eat_count = 0;
					}
					break;
				}
			}
			
			if(!isEat) break;
		}
	}
}
