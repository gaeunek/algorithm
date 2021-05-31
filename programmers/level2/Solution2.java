package level2;

import java.util.*;
//탑
public class Solution2 {
	public int[] solution(int[] heights) {
		int[] answer = new int[heights.length];

		Stack<Height> stack = new Stack<>();
		Stack<Height> hold = new Stack<>();

		for (int i = 0; i < answer.length; i++) {
			stack.push(new Height(heights[i], i));
		}

		while (!stack.isEmpty()) {
			Height now = stack.pop();

			if (stack.isEmpty()) {
				answer[now.idx] = 0;
				break;
			}

			if (stack.peek().h > now.h) {
				int size = hold.size();
				for (int s = 0; s < size; s++) {
					Height hold_now = hold.pop();

					if (stack.peek().h > hold_now.h) {
						answer[hold_now.idx] = stack.size();
					} else if (stack.peek().h < hold_now.h) {
						answer[hold_now.idx] = 0;
					} else {
						hold.add(hold_now);
					}
				}
				answer[now.idx] = stack.size();
			} else {
				hold.push(now);
			}
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

	public static void main(String[] args) {
		Solution2 sol = new Solution2();
		int[] heights = { 5, 3, 1, 2, 3 };
		System.out.println(Arrays.toString(sol.solution(heights)));
	}
}
