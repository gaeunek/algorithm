package level1;

import java.util.Arrays;

//체육복
class Solution42862 {
	public int solution(int n, int[] lost, int[] reserve) {
		int answer = 0;

		int[] results = new int[n + 1];

		Arrays.fill(results, 1);

		for (int i = 0; i < lost.length; i++)
			results[lost[i]] = 0;

		for (int i = 0; i < reserve.length; i++)
			results[reserve[i]]++;

		int prev = 0;
		for (int i = 1; i < results.length; i++) {
			if (i == 1) {
				prev = results[i];
				continue;
			}

			if (prev == 2 && results[i] == 0) {
				results[i - 1]--;
				results[i]++;
			} else if (prev == 0 && results[i] == 2) {
				results[i - 1]++;
				results[i]--;
			}

			prev = results[i];
		}

		for (int i = 1; i < results.length; i++) {
			if (results[i] >= 1)
				answer++;
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution42862 sol = new Solution42862();
		int[] lost = { 2, 4 };
		int[] reserve = { 1, 3, 5 };
		System.out.println(sol.solution(5, lost, reserve));
	}
}
