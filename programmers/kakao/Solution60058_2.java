package kakao;

import java.util.*;

public class Solution60058_2 {
	public String solution(String p) {
		return trans(p);
	}
	
	public static String trans(String w) {
		// 1. 입력이 빈 문자열인 경우, 빈 문자열 반환
		if (w.length() == 0)
			return w;

		// 2. 균형잡힌 괄호 문자열 u 와 v로 나눈다. 단, u는 더 이상 분리가 불가능하다.
		String u = "", v = "";
		int[] count = new int[2]; // 0은 '(', 1은 ')'
		for (int i = 0; i < w.length(); i++) {
			if (w.charAt(i) == '(')
				count[0]++;
			else
				count[1]++;

			if (Math.abs(count[0] - count[1]) == 0) {
				u = w.substring(0, i + 1);
				v = w.substring(i + 1, w.length());
				break;
			}
		}

		// 3. 올바른 괄호 문자열이라면 1부터 다시 시작
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < u.length(); i++) {
			if (stack.isEmpty() || u.charAt(i) == '(')
				stack.push(u.charAt(i));
			else if (!stack.isEmpty() && u.charAt(i) == ')')
				stack.pop();
		}

		if (stack.isEmpty()) {
			return u + trans(v);
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			
			sb.append(trans(v)).append(")");

			if(u.length() <= 1) u = "";
			else u = u.substring(1, u.length());
			
			
			if(u.length() <= 1) u = "";
			else u = u.substring(0, u.length() - 1);
			
			String new_u = "";
			if(!u.equals("")) {
				for (int i = 0; i < u.length(); i++) {
					new_u += u.charAt(i) == '(' ? ')' : '(';
				}
			}
			
			return sb.append(new_u).toString();
		}
	}
	
	public static void main(String[] args) {
		Solution60058_2 sol = new Solution60058_2();
		System.out.println(sol.solution("()))((()"));
		System.out.println(sol.solution("(()())()"));
		System.out.println(sol.solution(")("));
	}
}
