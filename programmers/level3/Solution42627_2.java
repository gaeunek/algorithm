package level3;

import java.util.*;
class Solution42627_2 {
	public int solution(int[][] jobs) {
		int answer = 0;

		Arrays.sort(jobs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) return o1[1] - o2[1]; // 500, 6 500 7같은 케이스가 들어왔을 때를 생각
				return o1[0] - o2[0];
			}
		});

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		int index = 0, cnt = 0, time = 0;
		
		while (!pq.isEmpty() || index < jobs.length) {
			if(cnt == jobs.length) break;
			
			while ((index < jobs.length && time >= jobs[index][0]) || pq.isEmpty()) {
				pq.add(jobs[index++]);
			}
			
			int[] now = pq.poll();
			cnt++;
			int req = now[0];
			int pross = now[1];
			if(time >= req) {
				answer += time - req + pross;
				time += pross;
			} else {
				answer += pross;
				time = req + pross;
			}
		}
		
		return answer / jobs.length;
	}
	
	public static void main(String[] args) {
		Solution42627_2 sol = new Solution42627_2();
//		System.out.println(sol.solution(new int[][] {{0,3}, {1,9}, {2,6}}));
//		System.out.println(sol.solution(new int[][] {{0,3}, {0,4}, {0,5}, {0,7}, {0,9}}));
//		System.out.println(sol.solution(new int[][] {{0,1}, {1,2}, {500, 6}}));
		System.out.println(sol.solution(new int[][] {{0,1}, {50,7}, {50,2}}));
	}
}
