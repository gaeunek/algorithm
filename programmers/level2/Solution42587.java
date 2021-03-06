package level2;

import java.util.*;
//프린터
public class Solution42587 {
	public int solution(int[] priorities, int location) {
		int answer = 0;

		Queue<Print> queue = new LinkedList<>();
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < priorities.length; i++) {
			queue.add(new Print(priorities[i], i));
			list.add(priorities[i]);
		}

		while (true) {
			Integer max = Collections.max(list);
			int index = 0;

			while (true) {
				Print now = queue.poll();
				if (now.priority == max) {
					index = now.location;
					list.remove(max);
					break;
				} else {
					queue.add(now);
				}
			}

			answer++;
			
			if (index == location)
				return answer;
		}
	}

	static class Print {
		int priority, location;

		public Print(int priority, int location) {
			this.location = location;
			this.priority = priority;
		}
	}
}
