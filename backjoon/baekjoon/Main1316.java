package baekjoon;

import java.util.*;
public class Main1316 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Stack<Character> stack = new Stack<>();
		int answer = 0;
		
		for (int i = 0; i < n; i++) {
			char[] input = sc.nextLine().toCharArray();

			boolean flag = true;
			for (char c : input) {
				if(stack.isEmpty() || stack.peek() != c) {
					if(stack.contains(c)) {
						flag = false;
						break;
					}
					stack.push(c);
				}
			}
			
			if(flag) answer++;
			stack.clear();
		}
		
		System.out.println(answer);
	}
}
