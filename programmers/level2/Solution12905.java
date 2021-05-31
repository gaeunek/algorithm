package level2;
//가장 큰 정사각형 찾기
class Solution12905 {
	static int N, M;

	static boolean check(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < M)
			return true;
		else
			return false;
	}

	public int solution(int[][] board) {
		int answer = 0;
		N = board.length;
		M = board[0].length;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 0)
					continue;

				cnt++;
				
				if (!check(i-1, j) || !check(i-1, j-1) || !check(i, j-1))
					continue;

				int min = Math.min(board[i-1][j], board[i-1][j-1]);
				min = board[i][j-1] < min ? board[i][j-1] : min;
				board[i][j] += min;

				answer = (board[i][j] * board[i][j]) > answer ? (board[i][j] * board[i][j]) : answer;
			}
		}
		if (answer == 0 && cnt != 0)
			return 1;
		
		return answer;
	}

	public static void main(String[] args) {
		Solution12905 sol = new Solution12905();
		System.out
				.println(sol.solution(new int[][] { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 } }));
		System.out.println(sol.solution(new int[][] { { 0, 0, 1, 1 }, { 1, 1, 1, 1 } }));
		System.out.println(sol.solution(new int[][] { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } }));
	}
}
