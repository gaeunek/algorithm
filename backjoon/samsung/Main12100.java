package samsung;

import java.util.*;
import java.io.*;

public class Main12100 {
	static int N, answer;
	static int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

	static boolean check(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < N)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		dfs(map, 0);
		System.out.println(answer);
	}

	public static void dfs(int[][] map, int count) {
		if (count == 5) {
			// 최댓값 확인
			answer = Math.max(checkMaxValue(map), answer);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			merge(map, d, count);
		}
	}

	public static void move(int[][] map, int d, int count) {
		switch (d) {
		case 0:
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j > 0; j--) {
					if (map[i][j] > 0)
						continue;

					int nj = j;
					while (nj > 0) {
						nj += dir[d][1];
						if (map[i][nj] > 0) {
							map[i][j] = map[i][nj];
							map[i][nj] = 0;
							break;
						}
					}
				}
			}
			break;
		case 1:
			for (int j = 0; j < N; j++) {
				for (int i = N - 1; i > 0; i--) {
					if (map[i][j] > 0)
						continue;

					int ni = i;
					while (ni > 0) {
						ni += dir[d][0];
						if (map[ni][j] > 0) {
							map[i][j] = map[ni][j];
							map[ni][j] = 0;
							break;
						}
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (map[i][j] > 0)
						continue;

					int nj = j;
					while (nj < N - 1) {
						nj += dir[d][1];
						if (map[i][nj] > 0) {
							map[i][j] = map[i][nj];
							map[i][nj] = 0;
							break;
						}
					}
				}
			}
			break;
		default:
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N - 1; i++) {
					if (map[i][j] > 0)
						continue;

					int ni = i;
					while (ni < N - 1) {
						ni += dir[d][0];
						if (map[ni][j] > 0) {
							map[i][j] = map[ni][j];
							map[ni][j] = 0;
							break;
						}
					}
				}
			}
			break;
		}
		
		dfs(map, count + 1);
	}

	private static void merge(int[][] origin, int d, int count) {
		int[][] map = copy(origin);
		
		switch (d) {
		case 0:
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j > 0; j--) {
					if (map[i][j] == 0)
						continue;

					int nj = j;
					while (nj > 0) {
						nj += dir[d][1];
						if(map[i][nj] > 0) {
							if(map[i][nj] == map[i][j])	{
								map[i][j] += map[i][nj];
								map[i][nj] = 0;
							}
							break;
						}
					}
				}
			}
			break;
		case 1:
			for (int j = 0; j < N; j++) {
				for (int i = N - 1; i > 0; i--) {
					if (map[i][j] == 0)
						continue;

					int ni = i;
					while (ni > 0) {
						ni += dir[d][0];
						if(map[ni][j] > 0) {
							if(map[ni][j] == map[i][j])	{
								map[i][j] += map[ni][j];
								map[ni][j] = 0;
							}
							break;
						}
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (map[i][j] == 0)
						continue;

					int nj = j;
					while (nj < N - 1) {
						nj += dir[d][1];
						if(map[i][nj] > 0) {
							if(map[i][nj] == map[i][j])	{
								map[i][j] += map[i][nj];
								map[i][nj] = 0;
							}
							break;
						}
					}
				}
			}
			break;
		default:
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N - 1; i++) {
					if (map[i][j] == 0)
						continue;

					int ni = i;
					while (ni < N - 1) {
						ni += dir[d][0];
						if(map[ni][j] > 0) {
							if(map[ni][j] == map[i][j])	{
								map[i][j] += map[ni][j];
								map[ni][j] = 0;
							}
							break;
						}
					}
				}
			}
			break;
		}
		
		move(map, d, count);
	}

	private static int checkMaxValue(int[][] map) {
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(map[i][j], max);
			}
		}

		return max;
	}
	
	public static int[][] copy(int[][] origin){
		int[][] copy = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		
		return copy;
	}

	public static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
