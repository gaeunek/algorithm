package samsung;

import java.io.*;
import java.util.*;

//1시간 45분 
public class Main19237 {
	static int N, M, k;
	static Smell[][] map;
	static int[][] shark;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][][] prioritDir;

	static boolean check(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < N)
			return true;
		return false;
	}

	static class Smell {
		int shark_num, smell; // 냄새를 풍긴 상어 번호와 값

		public Smell(int shark_num, int smell) {
			this.shark_num = shark_num;
			this.smell = smell;
		}

		@Override
		public String toString() {
			return "Smell [shark_num=" + shark_num + ", smell=" + smell + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		// map에는 상어의 번호를 k는 냄새를 의미한다. 냄새는 1칸 이동할때마다 1개씩 줄어들고 이미 남긴 냄새도 1씩 줄어든다.
		// 같은 칸에 상어가 두마리 도달하면 작은 상어만 남기니까 숫자가 큰 상어붙터 움직이는 편이 좋다

		// 인접한 칸을 가는 방법
		// 아무 냄새가 없는 칸으로 간다 > 내 냄새가 있는 칸으로 간다.
		// 이때 갈 수 있는 방향이 여러개면 특정 우선순위를 따른다.

		// 1번 상어만 남을때까지 몇 초가 걸리는지??

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // map의 크기
		M = Integer.parseInt(st.nextToken()); // 상어 번호 수 4개면 1~4까지
		k = Integer.parseInt(st.nextToken()); // 냄새의 크기

		map = new Smell[N][N];
		shark = new int[M + 1][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int shark_num = Integer.parseInt(st.nextToken());
				if (shark_num > 0) {
					shark[shark_num][0] = i;
					shark[shark_num][1] = j;
					map[i][j] = new Smell(shark_num, k);
				}
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			shark[i][2] = Integer.parseInt(st.nextToken()) - 1;
		}

		prioritDir = new int[M + 1][4][4];
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) { // 4방향 위, 아래, 왼쪽, 오른쪽 순서
				st = new StringTokenizer(br.readLine(), " ");
				for (int k = 0; k < 4; k++) {
					prioritDir[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}

		// 반복
		// 최대 1000초 넘어가면 -1 출력
		int time;
		for (time = 1; time <= 1000; time++) {
			removeSmell();// NxN배열을 돌려 냄새들을 1씩 줄인다.
			moveShark();// 우선순위에 따라 이동한다(상어 번호가 큰것부터 이동, 작은 상어만 남기기 위해)
			if(checkExtraShark()) break;
//			print();
		}
		
		System.out.println(time > 1000 ? -1 : time);

	}
	
	public static void removeSmell() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] != null && map[i][j].smell == 0) map[i][j] = null;
				else if(map[i][j] != null) map[i][j].smell--;
			}
		}
	}

	// 11시 10분
	/* 이동 후 냄새 저장 */
	public static void moveShark() {
		for (int i = 1; i <= M; i++) {
			if(shark[i][0] == -1) continue;
			
			boolean isExist = false;
			int[] new_d = prioritDir[i][shark[i][2]];

			// 애초에 특정 우선순위로 dir을 돌려보면서 먼저 아무 냄새가 없는 칸인지 확인한다.
			for (int d = 0; d < 4; d++) {
				int ni = shark[i][0] + dir[new_d[d]][0];
				int nj = shark[i][1] + dir[new_d[d]][1];

				if (check(ni, nj) && map[ni][nj] == null) {
					isExist = true;
					shark[i][0] = ni;
					shark[i][1] = nj;
					shark[i][2] = new_d[d];
					break;
				}
			}

			// isExist값에 따라서 false면 dir을 한번 더 돌려서 내 냄새가 있는 칸으로 간다.
			if (!isExist) {
				for (int d = 0; d < 4; d++) {
					int ni = shark[i][0] + dir[new_d[d]][0];
					int nj = shark[i][1] + dir[new_d[d]][1];

					if (check(ni, nj) && map[ni][nj].shark_num == i) {
						isExist = true;
						shark[i][0] = ni;
						shark[i][1] = nj;
						shark[i][2] = new_d[d];
						break;
					}
				}
			}
		}
		
		for (int n = 1; n <= M; n++) {
			int i = shark[n][0];
			int j = shark[n][1];
			if(i == -1) continue;
			
			if(map[i][j] != null && map[i][j].shark_num != n) {
				shark[n][0] = -1;
				shark[n][1] = -1;
			} else map[i][j] = new Smell(n, k);
		}
	}
	
	public static boolean checkExtraShark() {
		for (int i = 2; i <= M; i++) {
			if(shark[i][0] != -1) return false;
		}
		
		if(shark[1][0] == -1) return false;
		return true;
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
