package baekjoon;

import java.io.*;
import java.util.Arrays;

// 문자판
//depth추가하니까 9%넘겼으나 시간초과남
//-1로 초기화 하는 이유는 정답이 0인 경우에 모든 인덱스를 방문하다보니 터져버리니까 -1로 초기화해두는것이다.
public class Main2186 {
	static int N, M, K;
	static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static String target;
	static char[][] map;
	static int[][][] dp;
	static boolean check(int i, int j) {
		if(i >= 0 && i < N && j >= 0 && j < M) return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/* 입력 */
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}
		
		target = br.readLine();
		dp = new int[N][M][target.length()];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == target.charAt(0)) answer += dfs(i, j, 0); 
			}
		}
		
		System.out.println(answer);
	}
	
	static int dfs(int i, int j, int depth) {
		if(depth == target.length()-1) return dp[i][j][depth] = 1;
		if(dp[i][j][depth] != -1) return dp[i][j][depth];
		
		int cnt = 0; //이걸 안해주면 값이 -가 나온다. 방문처리 해주는것과 같다.
		for (int d = 0; d < 4; d++) {
			for (int k = 1; k <= K; k++) {
				int ni = i + dir[d][0] * k;
				int nj = j + dir[d][1] * k;
				
				if(check(ni, nj) && map[ni][nj] == target.charAt(depth+1)) {
					cnt += dfs(ni, nj, depth+1);
				}
			}
		}
		
		return dp[i][j][depth] = cnt;
	}
}
