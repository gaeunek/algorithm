package samsung;

import java.util.*;
import java.io.*;

public class Main17822 {
	static int N, M, T, sum, count;
	static int[][] circle;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 원판의 크기
		M = Integer.parseInt(st.nextToken()); // 정수의 개수
		T = Integer.parseInt(st.nextToken()); // 회전수

		circle = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
				sum += circle[i][j];
			}
		}

		count = N * M;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");

			int x = Integer.parseInt(st.nextToken()); // 원판 번호(반지름)
			int d = Integer.parseInt(st.nextToken()); // 0: 시계방향, 1: 반시계방향
			int k = Integer.parseInt(st.nextToken()); // 이동 칸 수

			rotate(x, d, k);
			close();
		}

		System.out.println(sum);
	}

	public static void close() {
		boolean[][] deleted = new boolean[N][M];
		boolean isClose = false;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				boolean isDelete = false;
				int now = circle[i][j];
				int next_m = circle[i][(j + 1) % M];
				int back_m = circle[i][(j + (M - 1)) % M];
				
				if(now == 0) continue;
				
				if (now == next_m) {
					deleted[i][(j + 1) % M] = true;
					isDelete = true;
				}

				if (now == back_m) {
					deleted[i][(j + (M - 1)) % M] = true;
					isDelete = true;
				}

				int next_n, back_n;
				if (i > 0) {
					back_n = circle[(i + (N - 1)) % N][j];
					if (now == back_n ) {
						deleted[(i + (N - 1)) % N][j] = true;
						isDelete = true;
					}
				}

				if (i < N - 1) {
					next_n = circle[(i + 1) % N][j];
					if (now == next_n) {
						deleted[(i + 1) % N][j] = true;
						isDelete = true;
					}
				}
				
				if (isDelete) {
					if(!isClose) {
						isClose = true;
					}
					deleted[i][j] = true;
				}
			}
		}

		if (isClose) { // 인접하고 삭제가능한 수가 있다면 삭제해준다
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (deleted[i][j]) {
						sum -= circle[i][j];
						circle[i][j] = 0;
						count--;
					}
				}
			}
		} else { // 인접한 수가 없다면 평균을 계산해서 크면 -1 작으면 +1 해준다
			double avg = (double)sum / count;
			sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(circle[i][j] == 0) continue;
					
					if (circle[i][j] > avg)
						circle[i][j]--;
					else if (circle[i][j] < avg) // 이거 왜 문제에 안나옴 ㅡㅡ 크거나같을때인데
						circle[i][j]++;

					sum += circle[i][j];
				}
			}
		}
	}

	/* 원판 돌리기 */
	public static void rotate(int x, int d, int k) {
		int[] tmp = new int[M];
		
		for (int i = x; i <= N; i += x) {
			int newi = i - 1; // 배열이 0부터 시작이라
			for (int j = 0; j < M; j++) {
				if (d == 0)
					tmp[(j + k) % M] = circle[newi][j];
				else
					tmp[(j + (M - k)) % M] = circle[newi][j];
			}
			
			for (int j = 0; j < M; j++) {
				circle[newi][j] = tmp[j];
			}
			
			Arrays.fill(tmp, 0);
		}
	}
	
	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(circle[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
