package tree;

import java.util.*;

import java.io.*;

public class Main1167_bfs {
	static int n;
	static List<Edge>[] graph;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		graph = new ArrayList[n + 1];

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

				graph[v].add(new Edge(u, cost));
			}
		}

		dist = new int[n + 1];
		
		bfs(1);
		
		int max = 0, max_idx = 0;
		
		for (int i = 1; i <= n; i++) {
			if(max < dist[i]) {
				max = dist[i];
				max_idx = i;
			}
		}
		
		Arrays.fill(dist, 0);
		
		bfs(max_idx);
		
		max = 0;
		
		for (int i = 1; i <= n; i++) {
			if(max < dist[i]) {
				max = dist[i];
			}
		}
		
		System.out.println(max);
	}

	public static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[n + 1];

		queue.add(v);
		visited[v] = true;

		while (!queue.isEmpty()) {
			int u = queue.poll();

			for (Edge edge : graph[u]) {
				if (!visited[edge.v]) {
					visited[edge.v] = true;
					queue.add(edge.v);
					dist[edge.v] = dist[u] + edge.cost;
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

		@Override
		public String toString() {
			return "Edge [v=" + v + ", cost=" + cost + "]";
		}
	}
}
