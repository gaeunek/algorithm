package swea;

import java.util.Arrays;
import java.util.Scanner;

public class Solution3307 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {
			int N = sc.nextInt();
			int[][] input = new int[2][N];
			for (int n = 0; n < N; n++) {
				input[0][n] = sc.nextInt();
			}

			for (int i = 0; i < N; i++) {
				int value = input[0][i];
				int count = input[1][i];
				for (int j = i + 1; j < N; j++) {
					if (value < input[0][j] && count + 1 > input[1][j]) {
						input[1][j] = count + 1;
					}
				}
			}
			Arrays.sort(input[1]);
			System.out.println("#" + tc + " " + (input[1][N-1]+1));
		}
	}
}
