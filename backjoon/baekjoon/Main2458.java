package baekjoon;

import java.util.*;

//키 순서
public class Main2458 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		boolean[][] results = new boolean[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			results[b][a] = true;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(i != j && results[i][k] && results[k][j]) results[i][j] = true;
				}
			}
		}
		
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			boolean flag = true;
			for (int j = 1; j <=N; j++) {
				if(i != j && !(results[i][j] || results[j][i])) {
					flag = false;
					break;
				}
			}
			
			if(flag)
				answer++;
		}

		System.out.println(answer);
	}
}
