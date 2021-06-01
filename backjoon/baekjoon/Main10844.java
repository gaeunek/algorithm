package baekjoon;

import java.util.*;
// 쉬운계단 수
public class Main10844 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] dp = new int[101][10];
		
		for (int i = 1; i < 10; i++) dp[1][i] = 1;
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				if(j-1 >= 0) dp[i][j] += dp[i-1][j-1] % 1000000000;
				if(j+1 < 10) dp[i][j] += dp[i-1][j+1] % 1000000000;
			}
		}
		
		long answer = 0;
		for (long i : dp[N]) {
			answer += i;
		}
		
		System.out.println(answer%1000000000);
	}
}
