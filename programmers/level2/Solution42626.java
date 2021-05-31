package level2;
//더 맵게
import java.util.*;

public class Solution42626 {
	public int solution(int[] scoville, int K) {
		int answer = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		for (int i : scoville)
			pq.add(i);

		while (!pq.isEmpty()) {
			try {
				int first = pq.poll();
				if (first >= K)
					return answer;

				int second = pq.poll();

				int cal = first + (second * 2);
				answer++;

				pq.add(cal);
			} catch (Exception e) {
				return -1;
			}
		}
		return answer;
	}
}
