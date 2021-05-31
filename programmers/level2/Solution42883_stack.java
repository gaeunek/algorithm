package level2;

import java.util.Stack;

//큰 수 만들기 (StringBuilder를 사용하는것이 중요하다)
class Solution42883_stack {
	public String solution(String number, int k) {
		char[] result = new char[number.length()-k];
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < number.length(); i++) {
			char num = number.charAt(i);

			while(!stack.isEmpty() && stack.peek()-'0' < num-'0' && k > 0) {
				stack.pop();
				--k; //후위로 하면 마지막 테케 패스 안되
			}
			
			stack.push(num);
		}
		
		
		for (int i = 0; i < result.length; i++) {
			result[i] = stack.get(i);
		}
		
		return new String(result);
	}
	
	public static void main(String[] args) {
		Solution42883_stack sol = new Solution42883_stack();
		System.out.println(sol.solution("4177252841", 4));
	}
}
