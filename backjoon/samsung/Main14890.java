package samsung;

import java.io.*;
import java.util.*;

public class Main14890 {
	static int N, L, answer;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		
		checkSlopeBuildHorizontal();
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		checkSlopeBuildVertical();
		System.out.println(answer);
	}
	
	public static void checkSlopeBuildHorizontal() {
		int prevHeight = 0;
		for (int i = 0; i < N; i++) {
			int j = 0, k = 0;
			boolean flag = true;

			while (j < N - 1) {
				prevHeight = map[i][j++];
				
				if(Math.abs(prevHeight - map[i][j]) > 1) {
					flag = false;
					break;
				}
				
				if (prevHeight < map[i][j]) {
					for (k = j - L; k < j; k++) {
						if (k < 0 || visited[i][k] || map[i][k] != prevHeight) {
							flag = false;
							break;
						}
						
						visited[i][k] = true;
					}
					
					if (!flag)
						break;
				} else if (prevHeight > map[i][j]) {
					for (k = j; k < j + L; k++) {
						if (k >= N || visited[i][k] || map[i][k] != map[i][j]) {
							flag = false;
							break;
						}
						
						visited[i][k] = true;
					}
					
					if (!flag)
						break;
					j += L - 1;
				}

				if (!flag)
					break;
			}

			if (flag) {
				answer++;
			} else {
				for (k = 0; k < N; k++) {
					visited[i][k] = false;
				}
			}
		}
	}
	
	public static void checkSlopeBuildVertical() {
		int prevHeight = 0;
		for (int j = 0; j < N; j++) {
			int i = 0, k = 0;
			boolean flag = true;
			
			while (i < N - 1) {
				prevHeight = map[i++][j];
				
				if(Math.abs(prevHeight - map[i][j]) > 1) {
					flag = false;
					break;
				}
				
				if (prevHeight < map[i][j]) {
					for (k = i - L; k < i; k++) {
						if (k < 0 || visited[k][j] || map[k][j] != prevHeight) {
							flag = false;
							break;
						}
						
						visited[k][j] = true;
					}

					if (!flag)
						break;
				} else if (prevHeight > map[i][j]) {
					for (k = i; k < i + L; k++) {
						if (k >= N || visited[k][j] || map[k][j] != map[i][j]) {
							flag = false;
							break;
						}
						
						visited[k][j] = true;
					}

					if (!flag)
						break;
					i += L - 1;
				}

				if (!flag)
					break;
			}

			if (flag) {
				answer++;
			} else {
				for (k = 0; k < N; k++) {
					visited[k][j] = false;
				}
			}
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
