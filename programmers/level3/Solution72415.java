package level3;

import java.util.*;

// 12가지의 조합을 구한다.
// 1칸 이동 vs ctrl + 방향 이동 시 더 가까운 곳을 계산한다. 이때 다른 카드를 만나면 그 이동값을 -1로 return 한다.
// 이동 결정난 장소는 visited처리 해준다.
// 더 가까운 쪽의 위치를 queue에 넣고 현재까지의 버튼 조작수를 넣는다.
// 목적지 까지 도착했으면 visted 배열 초기화해준다

class Solution72415 {
	static boolean[] exist_card; // 1~6까지의 카드 중 존재하는 카드만 조합에 넣는다.
	static int[][][] cards; // 카드가 없는곳은 null 값임
	static int[] start;
	static int count_card, answer, R, C; // existCard 개수
	static Set<String> set;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static boolean isRange(int i, int j) {
		if (i >= 0 && i < 4 && j >= 0 && j < 4)
			return true;
		return false;
	}

	public int solution(int[][] board, int r, int c) {
		R = r;
		C = c;
		start = new int[] { r, c };
		count_card = 0;
		answer = Integer.MAX_VALUE;

		exist_card = new boolean[7];
		cards = new int[7][2][];
		set = new HashSet<>();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] > 0) {
					int card_num = board[i][j];
					if (cards[card_num][0] == null)
						cards[card_num][0] = new int[] { i, j };
					else
						cards[card_num][1] = new int[] { i, j };

					if (!exist_card[card_num]) {
						exist_card[card_num] = true;
						count_card++;
					}
				}
			}
		}

		permutation(new boolean[7], "");

		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String s = it.next();
			int[] arr = new int[s.length()];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = s.charAt(i) - '0';
			}

			ArrayList<int[]> list = new ArrayList<>();

			if (board[R][C] == 0) {
				list.add(0, start);
			}

			comb(board, arr, list, new boolean[2], 0);
		}

		return answer;
	}

	/* 카드 모든 방향으로의 조합 찾기 */
	public static void comb(int[][] board, int[] arr, ArrayList<int[]> list, boolean[] used, int index) {
		if (arr.length == index) {

			int[] st = list.get(0);

			if (st[0] != R || st[1] != C)
				return;

			bfs(board, list);

			return;
		}

		for (int i = 0; i < 2; i++) {
			int[] first = cards[arr[index]][i];
			int[] second = cards[arr[index]][i == 0 ? 1 : 0];
			list.add(first);
			list.add(second);
			comb(board, arr, list, new boolean[2], index + 1);
			list.remove(first);
			list.remove(second);
		}
	}

	/* 최소경로 찾기 */
	public static void bfs(int[][] origin_board, ArrayList<int[]> list) {
		int result = 0;
		int[][] board = new int[4][4];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = origin_board[i][j];
			}
		}

		Queue<int[]> queue = new LinkedList<>();

		for (int k = 0; k < list.size() - 1; k++) {
			int[] tmp = new int[3];
			for (int i = 0; i < list.get(k).length; i++) {
				tmp[i] = list.get(k)[i];
			}

			tmp[2] = board[tmp[0]][tmp[1]] > 0 ? 1 : 0;
			board[tmp[0]][tmp[1]] = 0;
			queue.add(tmp);

			int now_move = Integer.MAX_VALUE;
			int[] target = list.get(k + 1);
			boolean[][] visited = new boolean[4][4];

			while (!queue.isEmpty()) {
				int[] now = queue.poll();
				int i = now[0];
				int j = now[1];
				int move = now[2];
//				System.out.println(Arrays.toString(now));
				visited[i][j] = true;

				// 목적지 도착
				if (i == target[0] && j == target[1]) {
					now_move = Math.min(now_move, move);
					continue;
				}

				for (int d = 0; d < 4; d++) {
					int ni = i + dir[d][0];
					int nj = j + dir[d][1];
					
					int dist1 = Integer.MAX_VALUE, dist2 = Integer.MAX_VALUE;
					if (!isRange(ni, nj)) // 범위 넘어서면 볼것도 없이 패스
						continue;

					if (!visited[ni][nj]) {
						dist1 = getDistance(ni, nj, target[0], target[1]);
					}

					int newi = ni, newj = nj;
					while (isRange(newi, newj) && board[newi][newj] == 0) {
						newi += dir[d][0];
						newj += dir[d][1];
					}

					if (!isRange(newi, newj)) {
						newi -= dir[d][0];
						newj -= dir[d][1];
					}

					if (!visited[newi][newj])
						dist2 = getDistance(newi, newj, target[0], target[1]);
					
					if(dist1 == Integer.MAX_VALUE && dist1 == dist2) continue;

					if (dist1 >= dist2) {
						queue.add(new int[] { newi, newj, move + 1 });
						visited[newi][newj] = true;
					} else {
						queue.add(new int[] { ni, nj, move + 1 });
						visited[ni][nj] = true;
					}
				}
			}

			result += now_move;
		}

		answer = Math.min(result + 1, answer); // 마지막 enter포함
	}

	/* 6장의 카드 순열 */
	public static void permutation(boolean[] used, String res) {
		if (res.length() == count_card) {
			set.add(res);
			return;
		}

		for (int i = 1; i < used.length; i++) {
			if (!used[i] && exist_card[i]) {
				used[i] = true;
				permutation(used, res + i);
				used[i] = false;
			}
		}
	}

	public static int getDistance(int i, int j, int ni, int nj) {
		return Math.abs(ni - i) + Math.abs(nj - j);
	}

	public static void main(String[] args) {
		Solution72415 sol = new Solution72415();
		System.out.println(
				sol.solution(new int[][] { { 1, 0, 0, 3 }, { 2, 0, 0, 0 }, { 0, 0, 0, 2 }, { 3, 0, 1, 0 } }, 1, 0));
		System.out.println(
				sol.solution(new int[][] { { 3, 0, 0, 2 }, { 0, 0, 1, 0 }, { 0, 1, 0, 0 }, { 2, 0, 0, 3 } }, 0, 1));
		System.out.println(
				sol.solution(new int[][] { { 1, 0, 0, 0 }, { 2, 3, 0, 0 }, { 0, 3, 2, 0 }, { 0, 0, 0, 1 } }, 0, 0));
		System.out.println(
				sol.solution(new int[][] { { 0, 1, 0, 0 }, { 0, 2, 0, 0 }, { 0, 2, 0, 0 }, { 0, 1, 0, 0 } }, 0, 0));
		System.out.println(
				sol.solution(new int[][] { { 0, 0, 0, 1 }, { 0, 0, 2, 0 }, { 0, 1, 0, 0 }, { 2, 0, 0, 0 } }, 0, 0));
		System.out.println(
				sol.solution(new int[][] { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 1 } }, 0, 0));
	}
}
