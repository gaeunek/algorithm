package level3;

//순위
class Solution49191 {
	public int solution(int n, int[][] results) {
		int answer = 0;

		int[][] graph = new int[n + 1][n + 1];

		for (int i = 0; i < results.length; i++) {
			graph[results[i][0]][results[i][1]] = 1;
			graph[results[i][1]][results[i][0]] = -1;
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i != j && graph[i][k] == 1 && graph[k][j] == 1) {
						graph[i][j] = 1;
					}
				}
			}
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i != j && graph[i][k] == -1 && graph[k][j] == -1) {
						graph[i][j] = -1;
					}
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			int cnt = 0;
			for (int j = 1; j <= n; j++) {
				if(graph[i][j] != 0) cnt++;
			}
			if(cnt == n-1) answer++;
		}

		return answer;
	}
	
	public static void main(String[] args) {
		Solution49191 sol = new Solution49191();
		System.out.println(sol.solution(5, new int[][] {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
//		System.out.println(sol.solution(5, new int[][] {{3, 5}, {4, 2}, {4, 5}, {5, 1}, {5, 2}}));
//		System.out.println(sol.solution(8, new int[][] {{1, 2}, {2, 3}, {3, 4}, {5, 6}, {6, 7}, {7, 8}}));
	}
}
