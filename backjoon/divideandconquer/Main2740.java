package divideandconquer;

import java.io.*;
import java.util.*;

public class Main2740 {
	static int N, M, K;
	static int[][] A, B, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		B = new int[M][K];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < K; j++) {
				B[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = new int[N][K];
		
		StringBuilder sb = new StringBuilder();

		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				for (int k = 0; k < K; k++) {
					answer[n][k] += A[n][m] * B[m][k];
				}
			}
		}


		for (int i = 0; i < N; i++) {
			for (int j = 0; j < K; j++) {
				sb.append(answer[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
}