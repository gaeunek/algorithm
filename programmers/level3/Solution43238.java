package level3;

class Solution43238 {
	public long solution(int n, int[] times) {
		long answer = Long.MAX_VALUE;

		long start = 0;
		long end = (long) times[times.length - 1] * n; // 최대 걸리는 시간
		long mid = 0;
		while (start <= end) {
			long sum = 0;

			mid = (start + end) / 2; // 최대 걸리는 시간을 반절로
			for (int time : times)
				sum += mid / time;

			if (sum < n)
				start = mid + 1;
			else {
				if (answer > mid)
					answer = mid;
				end = mid - 1;
			}
		}
		return answer;
	}
}
