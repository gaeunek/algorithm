package baekjoon;

import java.io.*;
// 점프 (dp를 long타입으로 바꾸니 통과함)
// Integer.MAX_VALUE가 21억
// long 타입 int 타입 공부하기
public class Main1890 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		long[][] dp = new long[N][N];
		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		dp[0][0] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(dp[i][j] == 0 || i == N-1 && j == N-1) continue;
				
				if(i+map[i][j] < N) dp[i+map[i][j]][j] += dp[i][j];
				if(j+map[i][j] < N) dp[i][j+map[i][j]] += dp[i][j];
			}
		}
		
		System.out.println(dp[N-1][N-1]);
		System.out.println(Integer.MAX_VALUE);
	}
}
