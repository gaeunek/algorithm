package tree;

import java.util.*;
import java.io.*;


public class Main4803 {
	static boolean[][] graph;
	static boolean[] visited;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int n = 0, m = 0, count = 0;

		while (true) {
			count++;
			st = new StringTokenizer(br.readLine(), " ");

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			if (n == 0 && m == 0)
				break;

			sb.append("Case ").append(count).append(": ");

			graph = new boolean[n][n];
			visited = new boolean[n];

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;

				graph[a][b] = true;
				graph[b][a] = true;
			}

			answer = 0;

			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					visited[i] = true;
					bfs(i);
				}
			}

			if (answer == 0)
				sb.append("No trees.");
			else if (answer == 1)
				sb.append("There is one tree.");
			else
				sb.append("A forest of ").append(answer).append(" trees.");

			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	public static void bfs(int u) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(u);

		int edge = 0, node = 0;

		while (!queue.isEmpty()) {
			int nowV = queue.poll();
			node++;

			for (int v = 0; v < graph[nowV].length; v++) {
				if(graph[nowV][v]) {
					edge++;
					
					if (!visited[v]) {
						visited[v] = true;
						queue.add(v);
					}
				}
			}
		}

		if (edge / 2 == node - 1)
			answer++;
	}
}
