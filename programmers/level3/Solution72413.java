package level3;
//합승 택시 요금
class Solution72413 {
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;

		int[][] costs = new int[n + 1][n + 1];
		boolean[][] used = new boolean[n + 1][n + 1];

		for (int i = 0; i < fares.length; i++) {
			costs[fares[i][0]][fares[i][1]] = fares[i][2];
			costs[fares[i][1]][fares[i][0]] = fares[i][2];
			used[fares[i][0]][fares[i][1]] = used[fares[i][1]][fares[i][0]] = true;
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == j)
						continue;

					if (used[i][k] && used[k][j]) {
						if (costs[i][j] == 0)
							costs[i][j] = costs[i][k] + costs[k][j];
						else {
							costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
						}
						costs[j][i] = costs[i][j];
						used[i][j] = used[j][i] = true;
					}
				}
			}
		}
		
		int min = 0;
		for (int i = 1; i <= n; i++) {
			min = costs[i][a] + costs[i][b] + costs[s][i];
			if(min == 0) continue;
			answer = Math.min(min, answer);
		}
		
		//최솟값만 찾아서 처리하는 부분을 넣으면 틀린다 -> 왜?
		//나는 최솟값만 찾아서 S부터 그 경로까지의 값을 더했는데 어쩌면 이게 최소가 아닌게 될 수 도 있기 때문이다!
//		for (int i = 1; i <= n; i++) {
//			int sum = 0;
//
//			if (result[i] == min) {
//				sum += costs[s][i] + result[i];
//				answer = Math.min(answer, sum);
//			}
//		}

		return answer;
	}

	static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == Integer.MAX_VALUE)
					arr[i][j] = 0;
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Solution72413 sol = new Solution72413();
//		System.out.println(sol.solution(6, 4, 6, 2, new int[][] { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 },
//				{ 5, 1, 24 }, { 4, 6, 50 }, { 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } }));
		System.out.println(sol.solution(7, 3, 4, 1,
				new int[][] { { 5, 7, 9 }, { 4, 6, 4 }, { 3, 6, 1 }, { 3, 2, 3 }, { 2, 1, 6 } }));
	}
}
