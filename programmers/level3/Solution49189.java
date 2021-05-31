package level3;

import java.util.*;

//가장 먼 노드
class Solution49189 {
	public int solution(int n, int[][] edge) {
		int answer = 0;

		boolean[][] visit = new boolean[n + 1][n + 1];

		for (int i = 0; i < edge.length; i++) {
			visit[edge[i][0]][edge[i][1]] = true;
			visit[edge[i][1]][edge[i][0]] = true;
		}

		Queue<Integer> queue = new LinkedList<>();
		int[] count = new int[n + 1];
		queue.add(1);

		int max = 0;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int i = 2; i < n + 1; i++) {
				if (count[i] == 0 && visit[now][i]) {
					count[i] = count[now] + 1;
					queue.add(i);
					max = count[i] > max ? count[i] : max;
				}
			}
		}

		for (int i = 2; i < n + 1; i++) {
			if (max == count[i])
				answer++;
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution49189 sol = new Solution49189();
		int[][] vertex = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
		sol.solution(6, vertex);
	}
}
