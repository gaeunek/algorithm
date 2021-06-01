package samsung;

import java.io.*;
import java.util.*;

public class Main17143_2 {
	static int R, C, M, getSharkWeight;
	static ArrayList<Shark> sharkList;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	static boolean check(int i, int j) {
		if (i >= 0 && i < R && j >= 0 && j < C)
			return true;
		return false;
	}

	static class Shark implements Comparable<Shark> {
		int i, j, s, d, z;

		public Shark(int i, int j, int s, int d, int z) {
			this.i = i;
			this.j = j;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			if (this.j == o.j && this.i == o.i)
				return o.z - this.z;
			else if (this.j == o.j)
				return this.i - o.i;
			return this.j - o.j;
		}

		@Override
		public String toString() {
			return "Shark [i=" + i + ", j=" + j + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sharkList = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()) - 1; // 방향
			int z = Integer.parseInt(st.nextToken()); // 크기
			sharkList.add(new Shark(r, c, s, d, z));
		}

		getSharkWeight = 0;
		for (int j = 0; j < C; j++) {
			Collections.sort(sharkList);
			getShark(j);
			moveShark();
		}

		System.out.println(getSharkWeight);
	}

	/* 상어 이동 */
	public static void moveShark() {
		int size = sharkList.size();
		for (int i = 0; i < size; i++) {
			Shark shark = sharkList.remove(0);

			int d = shark.d;
			int speed = shark.s;
			int ni = shark.i, nj = shark.j;

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

			Shark newShark = new Shark(ni, nj, shark.s, d, shark.z);
			sharkList.add(newShark);
		}
		
		Collections.sort(sharkList);
		int front_i = -1, front_j = -1;
		for (int i = 0; i < sharkList.size(); i++) {
			Shark shark = sharkList.get(i);
			if(front_i == -1 && front_j == -1) {
				front_i = shark.i;
				front_j = shark.j;
				continue;
			}else if(front_i == shark.i && front_j == shark.j) {
				sharkList.remove(i);
				i--;
				continue;
			}
			
			front_i = shark.i;
			front_j = shark.j;
		}
	}

	/* 낚시왕 열에서 가장 가까운 상어 잡기 */
	public static void getShark(int j) {
		for (int i = 0; i < sharkList.size(); i++) {
			if (sharkList.get(i).j == j) {
				Shark s = sharkList.remove(i);
				getSharkWeight += s.z;
				break;
			}
		}
	}
	
	public static void print() {
		for (int i = 0; i < sharkList.size(); i++) {
			System.out.println(sharkList.get(i));
		}
	}
}
