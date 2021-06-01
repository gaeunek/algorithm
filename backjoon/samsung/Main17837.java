package samsung;

import java.util.*;
import java.io.*;

// Dequeue를 사용하자 ㅠㅠ
// 진짜 너무 힘들었다 .......
// 시뮬레이션 문제할땐 충분히 생각하고 뭘 사용할지.. 문제 제대로 읽고.
public class Main17837 {
	static int N, K;
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static int[][] map, horse;
	static Deque<Integer>[][] horseMap;
	static final int WHITE = 0, RED = 1, BLUE = 2;

	static boolean check(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < N)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		horse = new int[K + 1][3];
		horseMap = new ArrayDeque[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				horseMap[i][j] = new ArrayDeque<Integer>();
			}
		}

		for (int k = 1; k <= K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;

			horse[k][0] = i;
			horse[k][1] = j;
			horse[k][2] = d;
			horseMap[i][j].add(k);
		}

		int time = 1;
		boolean flag = true;
		for (time = 1; time <= 1000; time++) {
			for (int k = 1; k <= K; k++) {
				int i = horse[k][0];
				int j = horse[k][1];
				int d = horse[k][2];
				
				if (horseMap[i][j].size() >= 4 || move(i, j, d, k)) {
					flag = false;
					break;
				}
			}

			if (!flag)
				break;
		}

		System.out.println(time > 1000 ? -1 : time);
	}

	public static boolean move(int i, int j, int d, int k) {
		int ni = i + dir[d][0];
		int nj = j + dir[d][1];
		Deque<Integer> temp = new ArrayDeque<>();
		boolean checked = false;
		int n;

		if (check(ni, nj) && map[ni][nj] <= RED) {
			if (map[ni][nj] == WHITE) {
				// 말 옮기기
				while (!horseMap[i][j].isEmpty()) {
					n = horseMap[i][j].poll();
					if (k == n || checked) {
						checked = true;
						horseMap[ni][nj].add(n);
						horse[n][0] = ni;
						horse[n][1] = nj;
					} else
						temp.add(n);
				}
			} else {
				while (!horseMap[i][j].isEmpty()) {
					n = horseMap[i][j].pollLast();
					horseMap[ni][nj].add(n);
					horse[n][0] = ni;
					horse[n][1] = nj;
					if (k == n)
						break;
				}
			}

			if (horseMap[ni][nj].size() >= 4)
				return true;

			// 옮긴 말 보다 아래 있던 말 다시 순서대로 저장
			while (!temp.isEmpty()) {
				horseMap[i][j].add(temp.poll());
			}

		} else { // 파란색이거나 범위 넘어가면
			while (!horseMap[i][j].isEmpty()) {
				n = horseMap[i][j].poll();
				if (n == k) {
					d = (d % 2 == 0 ? (d + 1) : (d + 3)) % 4;
					horse[n][2] = d; // 방향을 바꿔준다
					ni = i + dir[d][0];
					nj = j + dir[d][1];
				}
				temp.add(n);
			}

			while (!temp.isEmpty()) {
				horseMap[i][j].add(temp.poll());
			}
			if (check(ni, nj) && map[ni][nj] != BLUE) {
				return move(i, j, d, k);
			}
		}

		return false;
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(horseMap[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
