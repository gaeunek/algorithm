package samsung;

import java.io.*;
import java.util.*;

public class Main17143 {
	static int R, C, M, getSharkWeight;
	static Shark[][] map;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	static boolean check(int i, int j) {
		if (i >= 0 && i < R && j >= 0 && j < C)
			return true;
		return false;
	}

	static class Shark {
		int i, j, s, d, z;

		public Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}

		public Shark(int i, int j, int s, int d, int z) {
			this.i = i;
			this.j = j;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Shark [s=" + s + ", d=" + d + ", z=" + z + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Shark[R][C];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()) - 1; // 방향
			int z = Integer.parseInt(st.nextToken()); // 크기

			map[r][c] = new Shark(s, d, z);
		}

		getSharkWeight = 0;
		for (int j = 0; j < C; j++) {
			getShark(j);
			moveShark();
		}

		System.out.println(getSharkWeight);
	}

	/* 상어 이동 */
	public static void moveShark() {
		Queue<Shark> queue = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != null) {
					int d = map[i][j].d;
					int speed = map[i][j].s;
					int ni = i, nj = j;

					while (speed-- > 0) {
						ni += dir[d][0];
						nj += dir[d][1];
						if (!check(ni, nj)) {
							ni -= dir[d][0];
							nj -= dir[d][1];
							d = (d % 2 == 0 ? (d + 1) : (d + 3)) % 4;
							speed++;
						}
					}

					queue.add(new Shark(ni, nj, map[i][j].s, d, map[i][j].z));
					map[i][j] = null;
				}
			}
		}

		/* 한칸에 여러 상어가 존재한다면 가장 큰 상어만 남긴다 */
		while (!queue.isEmpty()) {
			Shark shark = queue.poll();
			if (map[shark.i][shark.j] == null)
				map[shark.i][shark.j] = shark;
			else if (map[shark.i][shark.j] != null && map[shark.i][shark.j].z < shark.z)
				map[shark.i][shark.j] = shark;

		}
	}

	/* 낚시왕 열에서 가장 가까운 상어 잡기 */
	public static void getShark(int j) {
		for (int i = 0; i < R; i++) {
			if (map[i][j] != null) {
				getSharkWeight += map[i][j].z;
				map[i][j] = null;
				break;
			}
		}
	}

	public static void print(Shark[][] arr) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != null) {
					System.out.println("[" + (i + 1) + ", " + (j + 1) + "] : " + arr[i][j]);
				}
			}
		}
		System.out.println();
	}
}
