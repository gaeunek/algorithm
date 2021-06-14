package tree;

import java.util.*;
import java.io.*;
/*
 * 9 9
1 2
2 3
3 4
4 5
3 5
6 7
7 8
6 8
8 9
0 0
 * */
public class Main4803 {
	static List<Integer>[] edgeList;
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

			edgeList = new ArrayList[n + 1];
			
			for (int i = 0; i <= n; i++) {
				edgeList[i] = new ArrayList<>();
			}
			
			visited = new boolean[n + 1];

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				edgeList[a].add(b);
			}
			
			answer = 0;

			for (int i = 1; i <= n; i++) {
				if(!visited[i]) {
					visited[i] = true;
					if (bfs(i)) {
						answer++;
					}
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

	public static boolean bfs(int u) {
		boolean result = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(u);
		
		while(!queue.isEmpty()) {
			int nowV = queue.poll();
			
			for(int v : edgeList[nowV]) {
				if(!visited[v]) {
					visited[v] = true;
					queue.add(v);
				} else {
					result = false;
				}
			}
		}
		
		return result;
	}
}
