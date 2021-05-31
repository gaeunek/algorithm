package kakao;

// 8:30
import java.util.*;

class Solution60061 {
	static boolean[][] pilar, beams;
	static int N;
	static List<int[]> list = new ArrayList<>();

	public int[][] solution(int n, int[][] build_frame) {
		int[][] answer = {};

		N = n;
		pilar = new boolean[N + 1][N + 1];
		beams = new boolean[N + 1][N + 1];

		// x는 col y는 row
		for (int i = 0; i < build_frame.length; i++) {
			int x = build_frame[i][0];
			int y = N - build_frame[i][1];
			int a = build_frame[i][2]; // 0 : 기둥, 1 : 보
			int b = build_frame[i][3]; // 0 : 삭제, 1 : 설치
			
			if(a == 0) { // 기둥
				/* 기둥 설치 */
				if(b == 1 && canBulidPilar(x, y)) pilar[y][x] = true;
				else if(b == 0) {
					pilar[y][x] = false;
					if(!check()) pilar[y][x] = true;
				}
			} else if(a == 1) {
				if(b == 1 && canBuildBeams(x, y)) beams[y][x] = true;
				else if(b == 0) {
					beams[y][x] = false;
					if(!check()) beams[y][x] = true;
				}
			}
		}

		result();

		answer = new int[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}

		Arrays.sort(answer, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0] && o1[1] == o2[1])
					return o1[2] - o2[2];
				else if (o1[0] == o2[0])
					return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
		});
		
		for (int i = 0; i < answer.length; i++) {
			System.out.println(Arrays.toString(answer[i]));
		}
		return answer;
	}

	public static boolean check() {
		// 기둥 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N + 1; j++) {
				if (pilar[i][j] && !canBulidPilar(i, j)) {
					return false;
				}
			}
		}

		// 보 확인
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < N; j++) {
				if (beams[i][j] && !canBuildBeams(i, j)) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean canBulidPilar(int x, int y) {
		// 기둥은 바닥 위 또는 보의 한쪽 끝 또는 또 다른 기둥 위에 있어야 한다.
		if (y == N || pilar[y + 1][x] || beams[y][x] || (x - 1 >= 0 && beams[y][x - 1]))
			return true;
		return false;
	}

	public static boolean canBuildBeams(int x, int y) {
		// 보는 한쪽 끝 부분이 기둥 위에 있거나, 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 한다.
		if (pilar[y + 1][x] || pilar[y + 1][x + 1] || (x - 1 >= 0 && x + 1 <= N && beams[y][x - 1] && beams[y][x + 1]))
			return true;
		return false;
	}

	public static void result() {
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				if (pilar[i][j])
					list.add(new int[] { j, N - i, 0 });
			}
		}

		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				if (beams[i][j])
					list.add(new int[] { j, N - i, 1 });
			}
		}
	}

	public static void main(String[] args) {
		Solution60061 sol = new Solution60061();
//		System.out.println(sol.solution(5, new int[][] { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 2, 1, 0, 1 }, { 2, 2, 1, 1 },
//				{ 5, 0, 0, 1 }, { 5, 1, 0, 1 }, { 4, 2, 1, 1 }, { 3, 2, 1, 1 } }));
		System.out.println(sol.solution(5, new int[][] { { 0, 0, 0, 1 }, { 2, 0, 0, 1 }, { 4, 0, 0, 1 }, { 0, 1, 1, 1 },
				{ 1, 1, 1, 1 }, { 2, 1, 1, 1 }, { 3, 1, 1, 1 }, { 2, 0, 0, 0 }, { 1, 1, 1, 0 }, { 2, 2, 0, 1 } }));
	}
}
