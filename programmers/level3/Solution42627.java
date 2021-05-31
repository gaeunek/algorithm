package level3;

//디스크 컨트롤러
import java.util.*;

public class Solution42627 {
	public int solution(int[][] jobs) {
		int answer = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

		Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

		int time = 0;
		int idx = 0;
		int len = jobs.length;

		while (idx < len || !pq.isEmpty()) {
			while (idx < len && jobs[idx][0] <= time) // 현재 시간 까지 들어오는 디스크들 pq에 넣음
				pq.offer(jobs[idx++]);

			if (pq.isEmpty())
				time = jobs[idx][0];
			else {
				int[] job = pq.poll();
				answer += time - job[0] + job[1];
				time += job[1];
			}
		}

		return answer / len;
	}

	public static void main(String[] args) {
		Solution42627 sol = new Solution42627();
		int[][] jobs = { { 0, 3 }, { 1, 9 }, { 2, 6 } };
		System.out.println(sol.solution(jobs));
	}
}
