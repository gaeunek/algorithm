package backtracking;

import java.util.*;
import java.io.*;

// 스타트와 링크
public class Main14889 {
	static int N;
	static int[][] arr;
	static boolean[] check;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		check = new boolean[N];
		check[0] = true;
		answer = Integer.MAX_VALUE;
		
		dfs(1, 1);
		
		System.out.println(answer);
	}
	
	public static void dfs(int st, int count) {
		if(count == N/2) {
			int[] teamStart = new int[N/2];
			int[] teamLink = new int[N/2];
			
			int s = 0, l = 0;
			for (int i = 0; i < N; i++) {
				if(check[i]) teamStart[s++] = i;
				else teamLink[l++] = i;
			}
			
			answer = Math.min(Math.abs(sum(teamStart) - sum(teamLink)), answer);
			return;
		}
		
		for (int i = st; i < N; i++) {
			check[i] = true;
			dfs(i+1, count+1);
			check[i] = false;
		}
	}
	
	public static int sum(int[] team) {
		int sum = 0;
		
		for (int i = 0; i < team.length; i++) {
			for (int j = 0; j < team.length; j++) {
				if(i != j) sum += arr[team[i]][team[j]];
			}
		}
		
		return sum;
	}
}
