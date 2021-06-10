package tree;

import java.util.*;
import java.io.*;

// 시간초과
public class Main1167_dfs {
	static int n, answer;
	static ArrayList<Costs>[] graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		graph = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		
		for (int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int v = Integer.parseInt(st.nextToken());

			while (true) {
				int u = Integer.parseInt(st.nextToken());

				if (u == -1)
					break;
				
				int cost = Integer.parseInt(st.nextToken());
				
				graph[v].add(new Costs(u, cost));
			}
		}
		
		for (int i = 1; i <= n; i++) {
			visited[i] = true;
			dfs(i, 0);
			visited[i] = false;
		}
		
		System.out.println(answer);
	}
	
	public static void dfs(int u, int total_cost) {
		answer = Math.max(answer, total_cost);
		
		for (int i = 0; i < graph[u].size(); i++) {
			Costs now = graph[u].get(i);
			int v = now.v;
			int cost = now.cost;
			
			if(!visited[v]) {
				visited[v] = true;
				dfs(v, total_cost + cost);
				visited[v] = false;
			}
		}
	}
	
	public static class Costs {
		int v, cost;
		
		public Costs(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
}
