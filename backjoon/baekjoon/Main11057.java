package baekjoon;
// 오르막 수 (다시풀어보기)
import java.util.*;

public class Main11057 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] dp = new int[1001][10];
		for (int i = 0; i < 10; i++) dp[1][i] = 1; 
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k <= j; k++) {
					dp[i][j] += dp[i-1][k];
					dp[i][j] %= 10007;
				}
			}
		}
		
		int answer = 0;
		for (int i : dp[N]) {
			answer += i;
		}
		
		System.out.println(answer%10007);
	}
}
