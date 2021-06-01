package samsung;

import java.io.*;
import java.util.*;

public class Main14890_2 {
	static int N, L, answer;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (go(i, 0, 0))
				answer++; // 가로

			if (go(0, i, 1))
				answer++; // 세로
		}
		
		System.out.println(answer);
	}

	public static boolean go(int x, int y, int dir) {
		boolean[] visited = new boolean[N];
		int[] height = new int[N];
		
		// 0이면 가로 탐색이니 한 줄 값을 height 배열에 넣는다고 생각
		for (int i = 0; i < N; i++) {
			height[i] = (dir == 0) ? map[x][y + i] : map[x + i][y];
		}

		for (int i = 0; i < N - 1; i++) { 
			if (Math.abs(height[i] - height[i + 1]) > 1)
				return false;

			if (height[i] > height[i + 1]) {
				for (int j = i + 1; j <= i + L; j++) {
					if (j >= N || visited[j] || height[j] != height[i + 1])
						return false;
					visited[j] = true;
				}
			} else if (height[i] < height[i + 1]) {
				for (int j = i; j > i - L; j--) {
					if (j < 0 || visited[j] || height[j] != height[i])
						return false;
					visited[j] = true;
				}
			}
		}

		return true;
	}
}
