package samsung;

import java.io.*;

public class Main12100_2 {
	static int N, answer;
	static int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

		if (N == 1) {
			System.out.println(map[0][0]);
			return;
		}

		answer = 0;
		bfs(map, 0);
		System.out.println(answer);
	}

	public static void bfs(int[][] map, int count) {
		if (count == 5) {
			answer = Math.max(checkMaxValue(map), answer);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int[][] moveMap = move(map, d); // 0인 부분 일단 밀어서 메워주기
			moveMap = merge(moveMap, d);
			moveMap = move(moveMap, d);
			bfs(moveMap, count + 1);
		}
	}

	private static int[][] merge(int[][] map, int d) {
		int ni = 0, nj = 0;
		
		switch (d) {
		case 0:
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j > 0; j--) {
					ni = i + dir[d][0];
					nj = j + dir[d][1];
					
					if(map[i][j] == map[ni][nj]) {
						map[i][j] += map[ni][nj];
						map[ni][nj] = 0;
					}
				}
			}
			break;
		case 1:
			for (int j = 0; j < N; j++) {
				for (int i = N - 1; i > 0; i--) {
					ni = i + dir[d][0];
					nj = j + dir[d][1];
					
					if(map[i][j] == map[ni][nj]) {
						map[i][j] += map[ni][nj];
						map[ni][nj] = 0;
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					ni = i + dir[d][0];
					nj = j + dir[d][1];
					
					if(map[i][j] == map[ni][nj]) {
						map[i][j] += map[ni][nj];
						map[ni][nj] = 0;
					}
				}
			}
			break;
		default:
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N - 1; i++) {
					ni = i + dir[d][0];
					nj = j + dir[d][1];
					
					if(map[i][j] == map[ni][nj]) {
						map[i][j] += map[ni][nj];
						map[ni][nj] = 0;
					}
				}
			}
			break;
		}
		
		return map;
	}

	private static int[][] move(int[][] originMap, int d) {
		int[][] moveMap = new int[N][N];
		switch (d) {
		case 0: // 오른쪽
			for (int i = 0; i < N; i++) {
				int newj = N - 1;
				for (int j = N - 1; j >= 0; j--) {
					if (originMap[i][j] != 0) // originMap의 0이 아닌 값을 moveMap에 차곡차곡 쌓는다고 생각
						moveMap[i][newj--] = originMap[i][j];
				}
			}
			break;
		case 1: // 아래
			for (int j = 0; j < N; j++) {
				int newi = N - 1;
				for (int i = N - 1; i >= 0; i--) {
					if (originMap[i][j] != 0)
						moveMap[newi--][j] = originMap[i][j];
				}
			}
			break;
		case 2: // 왼쪽
			for (int i = 0; i < N; i++) {
				int newj = 0;
				for (int j = 0; j < N; j++) {
					if (originMap[i][j] != 0)
						moveMap[i][newj++] = originMap[i][j];
				}
			}
			break;
		default: // 위
			for (int j = 0; j < N; j++) {
				int newi = 0;
				for (int i = 0; i < N; i++) {
					if (originMap[i][j] != 0)
						moveMap[newi++][j] = originMap[i][j];
				}
			}
			break;
		}

		return moveMap;
	}

	private static int checkMaxValue(int[][] map) {
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, map[i][j]);
			}
		}

		return max;
	}
}
