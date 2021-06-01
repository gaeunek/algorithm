package samsung;

import java.io.*;
import java.util.*;

public class Main19236 {
	static int[][] dir = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };
	static int answer;

	static boolean check(int i, int j) {
		if (i >= 0 && i < 4 && j >= 0 && j < 4)
			return true;
		return false;
	}

	static class Info {
		int[][] map, fish;

		public Info(int[][] map, int[][] fish) {
			this.map = map;
			this.fish = fish;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] map = new int[4][4]; // 물고기 번호 저장
		int[][] fish = new int[17][3]; // 물고기 번호에 해당하는 정보(위치와 방향)을 저장

		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				fish[map[i][j]][0] = i;
				fish[map[i][j]][1] = j;
				fish[map[i][j]][2] = d;
			}
		}

		dfs(0, 0, 0, map, fish);
		System.out.println(answer);
	}

	public static void dfs(int shark_i, int shark_j, int eat, int[][] map, int[][] fish) {
		// 범위를 벗어나거나 빈칸인 곳이면 끝낸다.
		if (!check(shark_i, shark_j) || map[shark_i][shark_j] == 0) {
			answer = Math.max(eat, answer);
			return;
		}

		int[][] newMap = copy(map);
		int[][] newFish = copy(fish);

		// 상어가 물고기를 먹는다
		int n = newMap[shark_i][shark_j];
		eat += n;
		int d = newFish[n][2];

		newMap[shark_i][shark_j] = 0; // 먹힌 물고기의 위치를 0으로 바꿔준다
		
		/* 먹힌 물고기의 정보도 업데이트 해준다.
		 * newMap에 먹힌 물고기를 0으로 표시하는데 아래 과정없이 map만 바꿔주면 move 함수의 fish에는 먹힌 물고기의 예전 위치가 저장되어 있을것이고,
		 * 물고기 번호 순서대로 이동하는 move 함수 특성상 어떤 물고기가 먹힌 물고기인지 알려면 n에 해당하는 물고기 정보를 가져와야한다.
		 * 그런데 이미 먹힌 물고기의 위치는 먹힌 직후부터 변하지 않았고, 그 곳이 빈칸이기 때문에 다른 물고기들이 위치할 수 있다.
		 * 즉, 이미 먹힌 물고기인지  알기 위해 map[x][y] == 0 이라는 조건문을 달면 x, y에는 예전위치가 저장되어있고 이미 그 자리엔 다른 물고기가 와있기 때문에 당연히 이동을 실행하게 된다.
		 * 그러다 보면 이동이 꼬여버리기 때문에 아래 처리를 꼭 해줘야한다! */
		newFish[n][0] = -1; // 
		newFish[n][1] = -1;

		// 물고기가 이동한다
		Info info = move(shark_i, shark_j, newMap, newFish);
		newMap = info.map;
		newFish = info.fish;

		
		// 상어가 이동하는 방향 중 하나의 물고기를 선택해 먹는다
		int ni = shark_i, nj = shark_j;
		while (check(ni, nj)) {
			ni += dir[d][0];
			nj += dir[d][1];
			dfs(ni, nj, eat, newMap, newFish);
		}
	}

	/* 물고기 이동 */
	public static Info move(int shark_i, int shark_j, int[][] map, int[][] fish) {
		for (int n = 1; n <= 16; n++) {
			int x = fish[n][0];
			int y = fish[n][1];
			int d = fish[n][2];

			if (x == -1 && y == -1)  // 이미 먹힌 물고기인지 확인
				continue;

			int ni = x, nj = y;
			while (true) {
				ni = x + dir[d][0];
				nj = y + dir[d][1];

				if (check(ni, nj) && (ni != shark_i || nj != shark_j)) { // 가려는 곳이 범위내에 있으며 상어가 있는곳도 아니라면
					int temp = map[ni][nj]; // 이동할 위치에 있는 물고기 번호
					map[ni][nj] = map[x][y]; // 위치 바꿔주기
					map[x][y] = temp;
					
					if(temp != 0) {
						fish[temp][0] = x;
						fish[temp][1] = y;
					}

					fish[n][0] = ni;
					fish[n][1] = nj;
					fish[n][2] = d;

					break;
				}

				d = (d + 9) % 8;
				if (d == fish[n][2])
					break;
			}

		}

		return new Info(map, fish);
	}

	public static int[][] copy(int[][] origin) {
		int[][] copy = new int[origin.length][origin[0].length];
		for (int i = 0; i < origin.length; i++) {
			for (int j = 0; j < origin[0].length; j++) {
				copy[i][j] = origin[i][j];
			}
		}

		return copy;
	}
}
