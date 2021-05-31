package level3;

class Solution43162 {
	static boolean[] visited;

	public int solution(int n, int[][] computers) {
		int answer = 0;

		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			if(visited[i])
				continue;
			
			for (int j = 0; j < n; j++) {
				if (computers[i][j] == 1 && !visited[j]) {
					visited[i] = true;
					answer += dfs(i, 1, n, computers);
				}
			}
		}
		return n - answer == 0 ? 1 : answer;
	}

	static int dfs(int node, int network, int n, int[][] computers) {
		for (int i = 0; i < n; i++) {
			if (!visited[i] && computers[i][node] == computers[node][i] && computers[i][node] == 1) {
				visited[i] = true;
				dfs(i, network+1, n, computers);
			}
		}
		return network;
	}
	
	public static void main(String[] args) {
		Solution43162 sol = new Solution43162();
		int[][] computers = {{1,1,0}, {1,1,0}, {0,0,1}};
		System.out.println(sol.solution(3, computers));
	}
}
