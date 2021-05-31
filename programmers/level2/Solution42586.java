package level2;

import java.util.Stack;
//기능개발
public class Solution42586 {
	public int[] solution(int[] progresses, int[] speeds) {
		Stack<Task> stack = new Stack<>();

		for (int i = 0; i < progresses.length; i++) {
			int progresse = progresses[i];
			int speed = speeds[i];
			int day = 0;

			while (progresse < 100) {
				day++;
				progresse += speed;
			}

			if (stack.isEmpty() || stack.peek().day < day) {
				stack.push(new Task(day, 1));
			} else {
				Task now = stack.pop();
				now.cnt++;
				stack.push(now);
			}
		}
		int[] answer = new int[stack.size()];
		for (int i = answer.length - 1; i >= 0; i--) {
			answer[i] = stack.pop().day;
		}
		return answer;
	}

	static class Task {
		int cnt, day;

		public Task(int day, int cnt) {
			this.cnt = cnt;
			this.day = day;
		}
	}
}
