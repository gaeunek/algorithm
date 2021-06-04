package divideandconquer;

import java.util.*;
import java.io.*;

public class Main1780 {
	static int[][] board;
	static int minus, zero, one;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;

		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		minus = 0; // -1
		zero = 0; // 0
		one = 0; // 1

		divide(0, 0, N);

		StringBuilder sb = new StringBuilder();
		sb.append(minus).append("\n");
		sb.append(zero).append("\n");
		sb.append(one);

		System.out.println(sb.toString());
	}

	public static void divide(int x, int y, int size) {
		boolean result = check(x, y, size);

		if (result) {
			if (board[x][y] == -1)
				minus++;
			else if (board[x][y] == 0)
				zero++;
			else
				one++;
		} else {
			size /= 3;
			divide(x, y, size);
			divide(x + size, y, size);
			divide(x + size * 2, y, size);

			divide(x, y + size, size);
			divide(x + size, y + size, size);
			divide(x + size * 2, y + size, size);

			divide(x, y + size * 2, size);
			divide(x + size, y + size * 2, size);
			divide(x + size * 2, y + size * 2, size);
		}
	}

	public static boolean check(int x, int y, int size) {
		int front = board[x][y];
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (board[i][j] != front)
					return false;
			}
		}

		return true;
	}
}
