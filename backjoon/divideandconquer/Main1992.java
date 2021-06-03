package divideandconquer;

import java.io.*;

public class Main1992 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		char[][] board = new char[N][N];

		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}

		System.out.println(divide(board, 0, 0, N).toString());
	}

	public static StringBuilder divide(char[][] board, int x, int y, int size) {
		int result = check(board, x, y, size);

		StringBuilder sb = new StringBuilder();

		if (result == -1) {
			size /= 2;

			sb.append("(");
			sb.append(divide(board, x, y, size));
			sb.append(divide(board, x, y + size, size));
			sb.append(divide(board, x + size, y, size));
			sb.append(divide(board, x + size, y + size, size));
			return sb.append(")");
		}

		sb.append(result);
		return sb;
	}

	public static int check(char[][] board, int x, int y, int size) {
		int w = 0, b = 0;
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (board[i][j] == '0')
					w++;
				else
					b++;
			}
		}

		if (w == 0)
			return 1;
		else if (b == 0)
			return 0;
		else
			return -1;
	}
}
