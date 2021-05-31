package level3;

// 등굣길
class Solution42898 {
	public int solution(int m, int n, int[][] puddles) {
		int[][] map = new int[n][m];

		for (int i = 0; i < puddles.length; i++)
			map[puddles[i][1] - 1][puddles[i][0] - 1] = -1;

		map[0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] < 0) {
					map[i][j] = 0;
					continue;
				}
				
				if(i > 0) map[i][j] += map[i-1][j];
				if(j > 0) map[i][j] += map[i][j-1];
			}
		}
		
		return map[n-1][m-1] % 1000000007;
	}
	
	public static void main(String[] args) {
		Solution42898 sol = new Solution42898();
//		System.out.println(sol.solution(4, 3, new int[][] { { 2, 2 } })); // 4
//		System.out.println(sol.solution(4, 1, new int[][] { { 2, 1 } })); // 2
//		System.out.println(sol.solution(3, 1, new int[][] {{2,1}})); // 1
	}
}
