package dp1;

import java.io.*;

// 피보나치 함수
public class Main1003 {
	static int[] memo;
	static int[][] count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		memo = new int[41];
		count = new int[41][2];
		count[0][0] = 1;
		count[1][1] = 1;
		
		for (int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());

			fibonacci(n);
			System.out.println(count[n][0]+" "+count[n][1]);
		}

	}

	public static int fibonacci(int n) {
		if(n <= 1) {
			return n;
		}
		if (memo[n] != 0) return memo[n];
		
		memo[n] = fibonacci(n - 1) + fibonacci(n - 2);
		count[n][0] = count[n-1][0] + count[n-2][0];
		count[n][1] = count[n-1][1] + count[n-2][1];
		
		return memo[n];
	}
}
