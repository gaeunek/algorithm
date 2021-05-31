package kakao;

import java.util.Arrays;
import java.util.PriorityQueue;

// 합승 택시 요금 (다익스트라 풀이)
class Solution72413 {
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;
		boolean[] visited;
		boolean[][] edge = new boolean[n + 1][n + 1];
		int[][] line = new int[n + 1][n + 1];

		for (int i = 0; i < fares.length; i++) {
			int node1 = fares[i][0];
			int node2 = fares[i][1];
			int value = fares[i][2];

			edge[node1][node2] = true;
			edge[node2][node1] = true;

			line[node1][node2] = value;
			line[node2][node1] = value;
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(s, 0));

		int[] start = new int[n + 1];
		Arrays.fill(start, Integer.MAX_VALUE);

		visited = new boolean[n + 1];

		// start
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int number = now.number;
			int value = now.value;
			
			if(visited[number]) continue;
			
			visited[number] = true;
			start[number] = value;

			for (int i = 1; i <= n; i++) {
				if (edge[number][i] && !visited[i] && i != number) {
					pq.add(new Node(i, value + line[number][i]));
				}
			}
		}

		int[] arrA = new int[n + 1];
		Arrays.fill(arrA, Integer.MAX_VALUE);

		Arrays.fill(visited, false);

		// arrA
		pq.add(new Node(a, 0));
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int number = now.number;
			int value = now.value;
			
			if(visited[number]) continue;
			
			visited[number] = true;
			arrA[number] = value;

			for (int i = 1; i <= n; i++) {
				if (edge[number][i] && !visited[i] && i != number) {
					pq.add(new Node(i, value + line[number][i]));
				}
			}
		}

		// arrB
		int[] arrB = new int[n + 1];
		Arrays.fill(arrB, Integer.MAX_VALUE);

		Arrays.fill(visited, false);

		pq.add(new Node(b, 0));
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int number = now.number;
			int value = now.value;
			
			if(visited[number]) continue;
			
			visited[number] = true;
			arrB[number] = value;

			for (int i = 1; i <= n; i++) {
				if (edge[number][i] && !visited[i] && i != number) {
					pq.add(new Node(i, value + line[number][i]));
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			answer = Math.min(start[i] + arrA[i] + arrB[i], answer);
		}
		return answer;
	}

	static class Node implements Comparable<Node> {
		int number, value;

		public Node(int number, int value) {
			this.number = number;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return this.value - o.value;
		}
	}

	public static void main(String[] args) {
		Solution72413 sol = new Solution72413();
		System.out.println(sol.solution(6, 4, 6, 2, new int[][] { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 },
				{ 5, 1, 24 }, { 4, 6, 50 }, { 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } }
		));
		System.out.println(sol.solution(7, 3, 4, 1,
				new int[][] { { 5, 7, 9 }, { 4, 6, 4 }, { 3, 6, 1 }, { 3, 2, 3 }, { 2, 1, 6 } }));
	}
}
