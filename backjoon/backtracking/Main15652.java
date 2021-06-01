package backtracking;

import java.util.*;
import java.io.*;
public class Main15652 {
	static int N, M;
	static int[] res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		res = new int[M];
		dfs(0, 0);
		System.out.println(sb.toString());
	}
	
	public static void dfs(int st, int index) {
		if(index == M) {
			for (int i : res) {
				sb.append(i).append(" ");
			}
			
			sb.append("\n");
			return;
		}
		
		for (int i = st; i < N; i++) {
			res[index] = i+1;
			dfs(i, index+1);
		}
	}
}
