package tree;

import java.util.*;
import java.io.*;

// 누가 부모고 자식인지 표시가 안되어있음.
public class Main11725_dfs {
	static int N;
	static int[] parent;
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
	
		parent = new int[N+1];
		visited = new boolean[N+1];
		graph = new ArrayList[N+1];
		
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			
			graph[v].add(u);
			graph[u].add(v);
		}
		
		visited[1] = true;
		
		dfs(1);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 2; i <= N; i++) {
			sb.append(parent[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void dfs(int u) {
		for(int v : graph[u]) {
			if(!visited[v]) {
				parent[v] = u;
				visited[v] = true;
				dfs(v);
			}
		}
	}
}
