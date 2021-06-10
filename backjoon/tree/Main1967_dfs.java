package tree;

import java.util.*;
import java.io.*;

public class Main1967_dfs {
	static int n;
	static List<Edge>[] edgeList;
	static int[] dist;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		edgeList = new ArrayList[n + 1];
		dist = new int[n + 1];
		visited = new boolean[n + 1];

		for (int i = 0; i <= n; i++) {
			edgeList[i] = new ArrayList<>();
		}

		StringTokenizer st;

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edgeList[v].add(new Edge(u, cost));
			edgeList[u].add(new Edge(v, cost));
		}

		visited[1] = true;
		dfs(1);
		
		int max = 0, max_idx = 0;
		
		for (int i = 1; i <= n; i++) {
			if(dist[i] > max) {
				max = dist[i];
				max_idx = i;
			}
		}
		
		Arrays.fill(visited, false);
		Arrays.fill(dist, 0);
		
		visited[max_idx] = true;
		dfs(max_idx);
		
		max = 0;
		
		for(int num : dist) {
			max = Math.max(num, max);
		}
		
		System.out.println(max);
	}

	public static void dfs(int u) {
		for(Edge edge : edgeList[u]) {
			if(!visited[edge.v]) {
				visited[edge.v] = true;
				dist[edge.v] = dist[u] + edge.cost;
				dfs(edge.v);
			}
		}
	}

	public static class Edge {
		int v, cost;

		public Edge(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
}
