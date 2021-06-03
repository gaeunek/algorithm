package divideandconquer;

import java.io.*;
import java.util.*;

public class Main2630 {
	static int white, blue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] board = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		white = 0;
		blue = 0;

		divide(board, 0, N, 0, N, N);
		
		System.out.println(white);
		System.out.println(blue);
	}

	public static void divide(int[][] board, int start_i, int end_i, int start_j, int end_j, int mid) {
		int result = check(board, start_i, end_i, start_j, end_j);

		if (result == -1) {
			mid /= 2;

			divide(board, start_i, start_i + mid, start_j, start_j + mid, mid);
			divide(board, start_i + mid, end_i, start_j, start_j + mid, mid);
			divide(board, start_i, start_i + mid, start_j + mid, end_j, mid);
			divide(board, start_i + mid, end_i, start_j + mid, end_j, mid);

		} else if (result == 0)
			white++;
		else
			blue++;
	}

	public static int check(int[][] board, int start_i, int end_i, int start_j, int end_j) {
		int w = 0, b = 0;
		for (int i = start_i; i < end_i; i++) {
			for (int j = start_j; j < end_j; j++) {
				if (board[i][j] == 0)
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
