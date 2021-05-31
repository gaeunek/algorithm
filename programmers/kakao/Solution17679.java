package kakao;

// 7:25
class Solution17679 {
	static int[][] dir = { { 0, 1 }, { 1, 1 }, { 1, 0 } };
	static int answer;

	public int solution(int m, int n, String[] board) {
		// 4개 삭제 가능한것을 true, false로 체크한 후 제거
		// 새로운 map을 만들어서 쌓기
		char[][] map = new char[m][n];

		for (int i = 0; i < m; i++) {
			map[i] = board[i].toCharArray();
		}

		game(map, m, n);
		return answer;
	}

	public static void game(char[][] map, int m, int n) {
		boolean[][] visited = new boolean[m][n];
		boolean check = false;

		for (int i = 0; i < m - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				int count = 0;

				if(map[i][j] >= 'A' && map[i][j] <= 'Z') {
					
					for (int d = 0; d < 3; d++) {
						int ni = i + dir[d][0];
						int nj = j + dir[d][1];
						
						if (map[ni][nj] == map[i][j]) {
							count++;
						}
					}
					
					if (count == 3) {
						if (!check)
							check = true;
						
						visited[i][j] = true;
						for (int d = 0; d < 3; d++) {
							int ni = i + dir[d][0];
							int nj = j + dir[d][1];
							
							visited[ni][nj] = true;
						}
					}
					
				}
			}
		}

		if (!check)
			return;

		// true인 곳 . 으로 지우기
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] && map[i][j] != '.') { // 2X2 중복 가능이므로 이 조건을 걸어준다
					map[i][j] = '.';
					answer++;
				}
			}
		}

		// 블록 땡기기
		char[][] newMap = new char[m][n];
		for (int j = 0; j < n; j++) {
			int newi = m - 1;
			for (int i = m - 1; i >= 0; i--) {
				if (map[i][j] != '.')
					newMap[newi--][j] = map[i][j];
			}
		}

		game(newMap, m, n);
	}

	public static void print(char[][] map, int m, int n) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Solution17679 sol = new Solution17679();
		System.out.println(sol.solution(4, 5, new String[] { "CCBDE", "AAADE", "AAABF", "CCBBF" }));
	}
}
