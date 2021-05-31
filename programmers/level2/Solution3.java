package level2;

import java.util.Stack;

// 쇠막대기
public class Solution3 {
	public int solution(String arrangement) {
		int answer = 0;
		char[] input = arrangement.toCharArray();
		Stack<Character> stack = new Stack<Character>();
		char save = '(';

		for (int i = 0; i < input.length; i++) {
			if (stack.isEmpty() || input[i] == '(') {
				stack.push(input[i]);
			} else if (input[i] == ')' && stack.peek() == '(') {
				if (save == input[i])
					answer++;
				else
					answer += stack.size() - 1;
				stack.pop();
			}
			
			save = input[i];
		}
		return answer;
	}
}
