package samsung;

import java.io.*;
import java.util.*;

public class Main17779 {
	static int N, people, answer;
	static int[][] map;

	static boolean isStandardPoint(int x, int y, int d1, int d2) {
		if (0 <= x && x < x + d1 + d2 && x + d1 + d2 < N && 0 <= y - d1 && y - d1 < y && y < y + d2 && y + d2 < N)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		people = 0;

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				people += map[i][j];
			}
		}

		answer = Integer.MAX_VALUE;

		// d의 최대 범위를 N/2 로 생각하고 계산해보자
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {

						if (isStandardPoint(x, y, d1, d2)) {
							division(x, y, d1, d2);
						}

					}
				}

			}
		}

		System.out.println(answer);
	}

	public static void division(int x, int y, int d1, int d2) {
		boolean[][] newMap = new boolean[N][N];
		// 5번 선거구
		for (int i = 0; i <= d1; i++) {
			newMap[x + i][y - i] = true;
			newMap[x + i + d2][y - i + d2] = true;
		}

		for (int i = 0; i <= d2; i++) {
			newMap[x + i][y + i] = true;
			newMap[x + i + d1][y + i - d1] = true;
		}
		int total_sum = 0;

		// 1번 선거구
		int sum = 0;
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j <= y; j++) {
				if (newMap[i][j])
					break;
				sum += map[i][j];
			}
		}
		total_sum += sum;
		int max = sum, min = sum;

		// 2번 선거구
		sum = 0;
		for (int i = 0; i <= x + d2; i++) {
			for (int j = N - 1; j > y; j--) {
				if (newMap[i][j])
					break;
				sum += map[i][j];
			}
		}
		total_sum += sum;
		max = Math.max(sum, max);
		min = Math.min(sum, min);

		// 3번 선거구
		sum = 0;
		for (int i = x + d1; i < N; i++) {
			for (int j = 0; j < y - d1 + d2; j++) {
				if (newMap[i][j])
					break;
				sum += map[i][j];
			}
		}
		total_sum += sum;
		max = Math.max(sum, max);
		min = Math.min(sum, min);

		// 4번 선거구
		sum = 0;
		for (int i = x + d2 + 1; i < N; i++) {
			for (int j = N - 1; j >= y - d1 + d2; j--) {
				if (newMap[i][j])
					break;
				sum += map[i][j];
			}
		}
		total_sum += sum;
		max = Math.max(sum, max);
		min = Math.min(sum, min);

		sum = people - total_sum;
		max = Math.max(sum, max);
		min = Math.min(sum, min);
		answer = Math.min(answer, max - min);
	}

	public static void print(int[][] newMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(newMap[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
