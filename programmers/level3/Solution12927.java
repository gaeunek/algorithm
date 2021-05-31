package level3;
//야근지수
import java.util.*;
class Solution12927 {
	public long solution(int n, int[] works) {
		long answer = 0; 

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i = 0; i < works.length; i++) {
			pq.add(works[i]);
		}
		
		while(n > 0) {
			int now = pq.poll();
			pq.add(now-1);
			n--;
		}
		
		while(!pq.isEmpty()) {
			int now = pq.poll();
			if(now <= 0) continue;
			answer += Math.pow(now, 2);
		}
		
        return answer;
    }
	
	public static void main(String[] args) {
		Solution12927 sol = new Solution12927();
		System.out.println(sol.solution(3, new int[]{1, 1}));
	}
}
