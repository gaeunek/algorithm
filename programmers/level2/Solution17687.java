package level2;

import java.util.*;
//n진수 게임
class Solution17687 {
	static Stack<String> stack;
	static String values = "0123456789ABCDEF";
	public String solution(int n, int t, int m, int p) {
		String answer = "";
		
		String[] game = new String[t*m];
		stack = new Stack<>();
		int num = 0, index = 0;
		while(index < t*m) {
			String res = cal(num++, n);
			if(res.length() == 1) {
				game[index++] = res;
				continue;
			}
			
			for (int i = 0; i < res.length(); i++) {
				if(index >= t*m) break;
				game[index++] = String.valueOf(res.charAt(i));
			}
		}
		
		for (int i = p-1, cnt = 0; i < t*m && cnt < t; i+=m, cnt++) answer += game[i];
		return answer;
	}
	
	static String cal(int num, int n) {
		int quotient = num / n;
		int remainder = num % n;
		if(quotient == 0) return String.valueOf(values.charAt(remainder));
		else if(quotient == 1) return String.valueOf(quotient) + values.charAt(remainder);
		return cal(num / n, n) + values.charAt(remainder);
	}
	
	public static void main(String[] args) {
		Solution17687 sol = new Solution17687();
		System.out.println(sol.solution(16, 1000, 100, 100));
	}
}
