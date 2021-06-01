package samsung;

import java.io.*;
import java.util.*;

// 포기 큐빙
public class Main5373 {
	static char[][] cube;
	static final int WHITE = 0, ORANGE = 1, BLUE = 2, GREEN = 3, RED = 4, YELLOW = 5;
//	static final int UP = 0, DOWN = 1, LEFT = 2, RIGTH = 3, BACK = 4, FRONT = 5;
	static int[][] dir = { { 3, 5, 3, 5 }, { 0, 2, 3, 5 }, { 3, 5, 6, 8 }, { 3, 5, 0, 2 }, { 6, 8, 3, 5 },
			{ 9, 11, 3, 5 } };
	static int[][] allRotateDir = { { 2, 6, 2, 6 }, { 9, 11, 3, 5 }, { 3, 5, 0, 2 }, { 3, 5, 6, 8 }, { 0, 2, 3, 5 },
			{ 9, 11, 3, 5 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		int N;
		StringTokenizer st;
		cube = new char[12][9];

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 9; j++) {
				for (int d = 0; d < 6; d++) {
					if (i >= dir[d][0] && i <= dir[d][1] && j >= dir[d][2] && j <= dir[d][3]) {
						switch (d) {
						case WHITE:
							cube[i][j] = 'w';
							break;
						case ORANGE:
							cube[i][j] = 'o';
							break;
						case BLUE:
							cube[i][j] = 'b';
							break;
						case GREEN:
							cube[i][j] = 'g';
							break;
						case RED:
							cube[i][j] = 'r';
							break;
						case YELLOW:
							cube[i][j] = 'Y';
							break;
						}
					}
				}
			}
		}
	}

	public static void print(char[][] arr) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
