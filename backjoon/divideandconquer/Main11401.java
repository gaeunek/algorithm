package divideandconquer;

import java.util.*;

// 메모리초과
public class Main11401 {
	static final int MOD = 1000000007;
	static long[][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();

		dp = new long[n + 1][n + 1];

		System.out.println(combination(n, k));
	}

	public static long combination(int n, int k) {
		if (n == 0 || n == 1 || k == 0 || n == k)
			return dp[n][k] = 1;

		if (dp[n][k] != 0)
			return dp[n][k];

		dp[n - 1][k - 1] = combination(n - 1, k - 1);
		dp[n - 1][k] = combination(n - 1, k);
		return dp[n][k] = dp[n - 1][k - 1] + dp[n - 1][k] % MOD;
	}
}
