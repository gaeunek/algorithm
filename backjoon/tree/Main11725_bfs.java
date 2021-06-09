package tree;

import java.util.*;
import java.io.*;

public class Main11725_bfs {
	static int N;
	static ArrayList<Integer>[] graph;
	static Queue<Integer> queue;
	static boolean[] visited;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		graph = new ArrayList[N + 1];
		queue = new LinkedList<>();
		visited = new boolean[N + 1];
		parent = new int[N + 1];
		
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());

			graph[v].add(u);
			graph[u].add(v);
		}

		queue.add(1);

		while (!queue.isEmpty()) {
			int u = queue.poll();

			for (int v : graph[u]) {
				if (!visited[v]) {
					visited[v] = true;
					parent[v] = u;
					queue.add(v);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 2; i <= N; i++) {
			sb.append(parent[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
