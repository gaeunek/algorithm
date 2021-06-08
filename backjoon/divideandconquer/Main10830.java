package divideandconquer;

import java.util.*;
import java.io.*;

public class Main10830 {
	static int N;
	static final int MOD = 1000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());

		long[][] matrix = new long[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Long.parseLong(st.nextToken());
			}
		}
		
		long[][] answer = pow(matrix, B);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(answer[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

	public static long[][] pow(long[][] A, long exponent) {
		if (exponent == 1)
			return multiple(A);

		long[][] temp = pow(A, exponent / 2);

		if (exponent % 2 == 1)
			return multiple((multiple(temp, temp)), A);

		return multiple(temp, temp);
	}

	/* 오버로딩 */
	public static long[][] multiple(long[][] A) {
		long[][] result = new long[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result[i][j] = A[i][j] % MOD;
			}
		}

		return result;
	}

	public static long[][] multiple(long[][] A, long[][] C) {
		long[][] result = new long[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					result[i][k] += A[i][j] * C[j][k];
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result[i][j] %= MOD;
			}
		}

		return result;
	}
}
