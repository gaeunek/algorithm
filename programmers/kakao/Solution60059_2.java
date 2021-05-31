package kakao;

// 자물쇠와 열쇠 약 40분 풀이(풀이법을 알고 있었음)
// 주의할 점, 자물쇠 배열과 열쇠 배열의 크기가 다를 수 있다.
// 패딩 배열을 만든다. 크기는 lock.length + key.length-1

class Solution60059_2 {
	static int n, m, k, size;

	public boolean solution(int[][] key, int[][] lock) {
		n = key.length; // 2차원 key 배열의 크기
		m = lock.length; // 2차원 lock 배열의 크기
		k = m + (n - 1);
		size = m + (n - 1) * 2;

		int[][] board = new int[size][size];

		// 자물쇠를 중앙에 위치 시킨다.
		// 시작 포인트는 (n-1, n-1)
		int newi = 0, newj;
		for (int i = n - 1; i < k; i++) {
			newj = 0;

			for (int j = n - 1; j < k; j++) {
				board[i][j] = lock[newi][newj++];
			}

			newi++;
		}

		for (int i = 0; i < 4; i++) {
			if (move(board, key))
				return true;
			key = rotate(key);
		}

		return false;
	}

	public static boolean move(int[][] board, int[][] key) {

		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {

				int[][] copy = copy(board);

				for (int r = 0; r < n; r++) {
					for (int c = 0; c < n; c++) {
						copy[i + r][j + c] += key[r][c];
					}
				}

				if (count(copy))
					return true;
			}
		}

		return false;
	}

	public static int[][] rotate(int[][] key) {
		int[][] arr = new int[n][n];

		int newi = 0, newj;
		for (int j = 0; j < n; j++) {
			newj = 0;
			for (int i = n - 1; i >= 0; i--) {
				arr[newi][newj++] = key[i][j];
			}
			newi++;
		}

		return arr;
	}

	public static boolean count(int[][] board) {
		for (int i = n - 1; i < k; i++) {
			for (int j = n - 1; j < k; j++) {
				if(board[i][j] != 1) return false;
			}
		}
		
		return true;
	}

	public static int[][] copy(int[][] board) {
		int[][] arr = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				arr[i][j] = board[i][j];
			}
		}

		return arr;
	}

	public static void print(int[][] board) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Solution60059_2 sol = new Solution60059_2();
		System.out.println(sol.solution(new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } },
				new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } }

		));
	}
}
