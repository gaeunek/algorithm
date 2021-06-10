package tree;

import java.util.*;
import java.io.*;

public class Main1967_bfs {
	static int n;
	static List<Edge>[] edgeList;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		edgeList = new ArrayList[n + 1];
		dist = new int[n + 1];

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

		bfs(1);
		
		int max = 0, max_idx = 0;
		
		for (int i = 1; i <= n; i++) {
			if(dist[i] > max) {
				max = dist[i];
				max_idx = i;
			}
		}
		
		Arrays.fill(dist, 0);
		
		bfs(max_idx);
		
		max = 0;
		
		for(int num : dist) {
			max = Math.max(num, max);
		}
		
		System.out.println(max);
	}

	public static void bfs(int u) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[n + 1];

		queue.add(u);
		visited[u] = true;

		while (!queue.isEmpty()) {
			int v = queue.poll();

			for (Edge node : edgeList[v]) {
				if (!visited[node.v]) {
					visited[node.v] = true;
					dist[node.v] = dist[v] + node.cost;
					queue.add(node.v);
				}
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
