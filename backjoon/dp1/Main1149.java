package dp1;

import java.io.*;
import java.util.*;

public class Main1149 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[n][n];
		dp[0] = arr[0];
		
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				int prev = dp[i-1][j];
				
				for (int k = 0; k < 3; k++) {
					if(j == k) continue;
					
					if(dp[i][k] == 0) dp[i][k] = prev + arr[i][k];
					else dp[i][k] = Math.min(dp[i][k], prev + arr[i][k]);
				}
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			answer = Math.min(answer, dp[n - 1][i]);
		}

		System.out.println(answer);
	}
}
