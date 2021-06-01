package dp1;

import java.io.*;

public class Main2579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n+1];

		for (int i = 1; i <= n; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		// DP문제 풀 때 중요한건 점화식을 어떻게 세우는냐이다.
		int[] dp = new int[n+1];
		dp[1] = nums[1];

		if(n == 1) {
			System.out.println(dp[1]);
			return;
		}
		
		dp[2] = dp[1] + nums[2];

		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 3] + nums[i - 1] + nums[i]);
		}
		
		System.out.println(dp[n]);

	}
}
