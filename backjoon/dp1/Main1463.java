package dp1;

import java.io.*;
public class Main1463 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n+1];
		if(n <= 3) {
			System.out.println(n == 1 ? 0 : 1);
			return;
		}
		
		dp[2] = dp[3] = 1;
		
		for (int i = 4; i <= n; i++) {
			dp[i] = Integer.MAX_VALUE;
			
			if(i % 3 == 0) dp[i] = dp[i/3] + 1;
			if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2] + 1);
			
			dp[i] = Math.min(dp[i], dp[i-1] + 1);
		}
		
		System.out.println(dp[n]);
	}
}
