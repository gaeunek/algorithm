package dp1;

import java.io.*;
import java.util.Arrays;

public class Main2156 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] A = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}

		if (N == 1) {
			System.out.println(A[1]);
			return;
		}

		long[] dp = new long[N + 1];
		dp[1] = A[1];
		dp[2] = dp[1] + A[2];

		// 안마시고 지나치는 경우도 있다. 예를 들어 1번 연속, 2번 연속마시는게 가능한 것처럼 0번 연속 = 안마시고 지나치므로
		// 이전의 최댓값과 비교하여 이전 값이 더 크면 바꿔준다.
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 2] + A[i], dp[i - 3] + A[i - 1] + A[i]);
			dp[i] = Math.max(dp[i - 1], dp[i]);
		}

		long max = 0;
		for (long i : dp) {
			max = Math.max(i, max);
		}

		System.out.println(max);
	}
}