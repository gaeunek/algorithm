package level3;

//하노이의 탑
import java.util.*;

class Solution12946 {
	static List<int[]> result;

	public int[][] solution(int n) {
		result = new ArrayList<>();

		hanoi(n, 1, 3, 2);

		int[][] answer = new int[result.size()][2];

		for (int i = 0; i < result.size(); i++) {
			int[] arr = result.get(i);
			answer[i][0] = arr[0];
			answer[i][1] = arr[1];
		}

		return answer;
	}

	static void hanoi(int n, int st, int end, int mid) {
		int[] move = { st, end };

		if (n == 1) {
			result.add(move);
		} else {
			hanoi(n - 1, st, mid, end);
			result.add(move);
			hanoi(n - 1, mid, end, st);
		}
	}

	public static void main(String[] args) {
		Solution12946 sol = new Solution12946();
		sol.solution(3);
	}
}
