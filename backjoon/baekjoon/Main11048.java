package baekjoon;

import java.io.*;

// 이동하기
public class Main11048 {
	static int N, M;
	static int[][] map, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		map = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		dp[0][0] = map[0][0];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(i-1 >= 0) dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + map[i][j]);
				if(j-1 >= 0) dp[i][j] = Math.max(dp[i][j], dp[i][j-1] + map[i][j]);
				if(i-1 >= 0 && j-1 >= 0) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + map[i][j]);
			}
		}
		
		System.out.println(dp[N-1][M-1]);
	}
}
