package level3;

class Solution12907 {
	static final int MOD = 1000000007;

	public int solution(int n, int[] money) {
		int[][] dp = new int[money.length + 1][n + 1];

		dp[0][0] = 1;

		for (int i = 1; i <= money.length; i++) {
			for (int j = 0; j <= n; j++) {
				if (j > money[i - 1]) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - money[i - 1]] % MOD;
				} else if (j == money[i - 1]) {
					dp[i][j] = dp[i - 1][j] + 1 % MOD;
				} else {
					dp[i][j] = dp[i - 1][j] % MOD;
				}
			}
		}

		return dp[money.length][n];
	}

	public static void main(String[] args) {
		Solution12907 sol = new Solution12907();
		System.out.println(sol.solution(5, new int[] { 1, 2, 5 }));
	}
}
