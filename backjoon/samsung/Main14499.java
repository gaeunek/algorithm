package samsung;

import java.io.*;
import java.util.*;

// 주사위 굴리기
public class Main14499 {
	static int N, M, K, x, y;
	static int[][] map;
	static int[][] dicePosition = { { 0, 1, 2, 3, 4, 5, 6 }, { 0, 4, 2, 1, 6, 5, 3 }, { 0, 3, 2, 6, 1, 5, 4 },
			{ 0, 5, 1, 3, 4, 6, 2 }, { 0, 2, 6, 3, 4, 1, 5 } };
	static int[] diceValue = new int[7];
	static int[] route;
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // 동서북남
	static boolean check(int i, int j) {
		if(i >= 0 && i < N && j >= 0 && j < M) return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		int d = 0;
		int[] nowPosition = dicePosition[d];
		for (int i = 0; i < K; i++) {
			int route = Integer.parseInt(st.nextToken());
			int[] nextPosition = new int[7];
			int ni = x + dir[route - 1][0];
			int nj = y + dir[route - 1][1];

			for (int j = 1; j < 7; j++) {
				int index = dicePosition[route][j];
				nextPosition[j] = nowPosition[index];
			}

			if(!check(ni, nj)) continue;
			if (map[ni][nj] == 0) {
				map[ni][nj] = diceValue[nextPosition[6]];
			} else {
				diceValue[nextPosition[6]] = map[ni][nj]; // 이동한 칸에 값이 0이 아니면 바닥면에 숫자 복사
				map[ni][nj] = 0;
			}

			System.out.println(diceValue[nextPosition[1]]);
			x = ni;
			y = nj;
			d = route;
			nowPosition = nextPosition;
		}
	}
}
