package level2;
//n개의 최소공배수?
import java.util.*;
class Solution12953 {
	static Stack<Integer> stack;
	public int solution(int[] arr) {
		stack = new Stack<>();
		
		for (int i : arr) stack.add(i);
		
		int a, b, result;
		while(stack.size() > 1) {
			a = stack.pop();
			b = stack.pop();
			result = lcm(a, b);
			stack.add(result);
		}
		
		return stack.pop();
	}
	static int gcd(int a, int b) {
		if(a % b == 0) return b;
		return gcd(b, a%b);
	}
	
	static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
}
