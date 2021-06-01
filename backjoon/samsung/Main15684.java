package samsung;

import java.util.*;
import java.io.*;

// 가로 세로 H에 대해 제대로 이해하지 못하고 풀어서 2시간 정도 걸림
public class Main15684 {
	static int N, M, H, answer;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // col
		M = Integer.parseInt(st.nextToken()); 
		H = Integer.parseInt(st.nextToken()); // row

		map = new boolean[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}

		answer = 0;
		// 현재 상태 검토(사다리 타고 내려가보기)
		if (checkLadder()) {
			System.out.println(answer);
			return;
		}

		// false일 경우 1개씩 추가해본다.
		for (int i = 1; i <= 3; i++) {
			dfs(1, 0, i);
			if (answer != 0)
				break;
		}

		System.out.println(answer == 0 ? -1 : answer);
	}

	public static void dfs(int x, int cntLadder, int h) {
		if (cntLadder == h) {
			// 사다리 타고 내려가보기
			if (checkLadder()) {
				answer = h;
			}
			return;
		}

		for (int i = x; i <= H; i++) {
			for (int j = 1; j < N; j++) {
				if (!map[i][j]) {
					map[i][j] = true;
					dfs(i, cntLadder + 1, h);
					map[i][j] = false;
				}
			}
		}
	}

	public static boolean checkLadder() {
		for (int j = 1; j <= N; j++) {
			int newj = j;
			int i = 1;
			while (i <= H) {
				if (map[i][newj]) {
					newj++;
				} else if (map[i][newj - 1]) {
					newj--;
				}
				i++;
			}

			if (newj == j)
				continue;
			else
				return false;
		}
		return true;
	}

	public static void print() {
		for (int i = 0; i <= M + 1; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j])
					System.out.print(i + ", " + j + "\t");
//				System.out.print(map[i][j] + "\t");
			}
		}
		System.out.println();
	}
}
