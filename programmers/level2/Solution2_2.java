package level2;

import java.util.*;

//탑
public class Solution2_2 {
	public int[] solution(int[] heights) {
		int[] answer = new int[heights.length];
		Height[] arr = new Height[heights.length];

		Stack<Height> stack = new Stack<>();

		for (int i = 0; i < answer.length; i++) {
			arr[i] = new Height(heights[i], i);
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			if (!stack.isEmpty() && arr[i].h > stack.peek().h) {
				int size = stack.size();

				for (int s = 0; s < size; s++) {
					Height now = stack.pop();

					if (arr[i].h > now.h) {
						answer[now.idx] = arr[i].idx + 1;
					} else {
						stack.add(now);
					}
				}
			}
			stack.push(arr[i]);
		}
		return answer;
	}

	static class Height {
		int h, idx;

		public Height(int h, int idx) {
			this.h = h;
			this.idx = idx;
		}
	}
}
