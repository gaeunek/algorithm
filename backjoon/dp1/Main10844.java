package dp1;

import java.io.*;
import java.util.Arrays;

public class Main10844 {
	static final int MOD = 1000000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[][] dp = new int[n + 1][10];
		for (int j = 1; j < 10; j++) {
			dp[1][j] = 1;
		}

		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < 10; j++) {
				if (j - 1 >= 0)
					dp[i][j] += dp[i - 1][j - 1] % MOD;
				if (j + 1 < 10)
					dp[i][j] += dp[i - 1][j + 1] % MOD;
			}
		}
		
		long sum = 0;
		for (int num : dp[n]) {
			sum += num;
		}
		
		System.out.println(sum % MOD);
	}
}
