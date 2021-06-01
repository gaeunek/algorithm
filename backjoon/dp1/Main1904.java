package dp1;

import java.io.*;

public class Main1904 {
	static int[] dp;
	static final int MOD = 15746;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		dp = new int[n+1];
		dp[0] = dp[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % MOD;
		}
		
		System.out.println(dp[n]);
	}
}
