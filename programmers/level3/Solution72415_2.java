package level3;

public class Solution72415_2 {
	public int solution(int[][] board, int r, int c) {
		int answer = 0;

		boolean isExists[] = new boolean[7];
		int[][] pos = new int[7][4]; // 카드의 위치를 담은 2차원 배열
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != 0) {
					if (isExists[board[i][j]]) {
						pos[board[i][j]][2] = i;
						pos[board[i][j]][3] = j;
					} else {
						pos[board[i][j]][0] = i;
						pos[board[i][j]][1] = j;
					}
					isExists[board[i][j]] = true;
				}
			}

		return recurse(r, c, isExists, pos, board);
	}

	int recurse(int r, int c, boolean isExists[], int[][] pos, int[][] board) {
		int min = Integer.MAX_VALUE >> 1;
		boolean flag = true;
		for (int n = 1; n <= 6; n++) {
			if (isExists[n]) {
				flag = false;
				int temp = 0;
				int temp0 = 0;
				int temp1 = minRoute(isExists, board, r, c, pos[n][0], pos[n][1], 100000);
				int temp2 = minRoute(isExists, board, r, c, pos[n][2], pos[n][3], 100000);
				int temp3 = minRoute(isExists, board, pos[n][0], pos[n][1], pos[n][2], pos[n][3], 100000);
				int temp4 = minRoute(isExists, board, pos[n][2], pos[n][3], pos[n][0], pos[n][1], 100000);
				boolean[] copied = isExists.clone();

				copied[n] = false;
				temp = recurse(pos[n][2], pos[n][3], copied, pos, board) + temp1 + temp3 + 2;
				temp0 = recurse(pos[n][0], pos[n][1], copied, pos, board) + temp2 + temp4 + 2;
				min = Math.min(Math.min(min, temp), temp0);
			}
		}
		if (flag)
			return 0;
		return min;
	}

	int minRoute(boolean isExists[], int[][] board, int r, int c, int tr, int tc, int m) {
		int min = Math.min(m, Math.abs(r - tr) + Math.abs(c - tc));
		if (tr == r && tc == c)
			return 0;
		if (min <= 0)
			return 10000;
		int offsets[][] = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

		for (int[] offset : offsets) {
			if (isInRange(r + offset[0], c + offset[1]))
				min = Math.min(min, minRoute(isExists, board, r + offset[0], c + offset[1], tr, tc, min - 1) + 1);

			int temp_r = r + offset[0];
			int temp_c = c + offset[1];
			while (isInRange(temp_r + offset[0], temp_c + offset[1]) && !isExists[board[temp_r][temp_c]]) {
				temp_r += offset[0];
				temp_c += offset[1];
			}
			if (isInRange(temp_r, temp_c))
				min = Math.min(min, minRoute(isExists, board, temp_r, temp_c, tr, tc, min - 1) + 1);

		}
		return min;
	}

	boolean isInRange(int r, int c) {
		return r < 4 && r >= 0 && c < 4 && c >= 0;
	}
}
