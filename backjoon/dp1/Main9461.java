package dp1;

import java.io.*;
public class Main9461 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		long[] dp = new long[101];
		for (int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			
			dp[1] = dp[2] = dp[3] = 1;
			for (int i = 4; i <= n; i++) {
				dp[i] = dp[i-2] + dp[i-3];
			}
			
			System.out.println(dp[n]);
		}
	}
}
