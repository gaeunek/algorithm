package samsung;

import java.io.*;
import java.util.*;

// 로봇청소기
public class Main14503 {
	static int N, M, answer;
	static int[][] map;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean check(int i, int j) {
		if(i >= 0 && i < N && j >= 0 && j < M) return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		dfs(r, c, d);
		System.out.println(answer);
	}
	
	public static void dfs(int r, int c, int d) {
		if(map[r][c] == 0) {
			answer++;
			map[r][c] = 2;
		}
		
		// 왼쪽 탐색 후 청소할 거 있으면 회전 후 이동 없으면 방향만 회전
		int nd = (d + 3) % 4;
		int nr = r + dir[nd][0];
		int nc = c + dir[nd][1];
		if(map[nr][nc] == 0) dfs(nr, nc, nd);
		else {
			int count = 0;

			for (int k = 0; k < 4; k++) {
				d = (d + 3) % 4;
				int ni = r + dir[d][0];
				int nj = c + dir[d][1];
				
				if(check(ni, nj) && map[ni][nj] == 0) {
					dfs(ni, nj, d);
					break;
				}
				count++;
			}

			if(count == 4) {
				int bi = r - dir[d][0];
				int bj = c - dir[d][1];
				if(check(bi, bj) && map[bi][bj] != 1) dfs(bi, bj, d);
				else return;
			}
		}
	}
	
	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
