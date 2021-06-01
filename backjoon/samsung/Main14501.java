package samsung;

import java.io.*;
import java.util.*;

// 퇴사
public class Main14501 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] consulting = new int[N][2];
		StringTokenizer st;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			consulting[i][0] = Integer.parseInt(st.nextToken()); 
			consulting[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N + 1];
		for (int i = 0; i < N; i++) {
			int time = consulting[i][0];
			int cost = consulting[i][1];
			
			for (int j = i + time; j <= N; j++) {
				if(dp[j] < dp[i] + cost) {
					dp[j] = dp[i] + cost;
				}
			}
		}
		
		System.out.println(dp[N]);
	}
}
