package level3;
//가장 긴 팰린드롬
class Solution12904 {
	static int N;
	public int solution(String s) {
		N = s.length();
		
		int odd = odd(s);
		int even = even(s);
		if(odd + even == 0) return 1;
		else if(odd >= even) return odd * 2 + 1;
		else return even * 2;
	}
	
	static int odd(String s) {
		int res = 0, w = 1;
		char left, right;
		
		for (int i = 1; i < N-1; i++) {
			left = s.charAt(i-w);
			right = s.charAt(i+w);
			
			if(left == right) {
				res = Math.max(res, w);
				if(i-w == 0 || i+w == N-1) {
					w = 1;
					continue;
				}
				w++;
				i--;
			} else w = 1;
		}
		
		return res;
	}
	
	static int even(String s) {
		int res = 0, w = 0;
		char left, right;
		
		for (int i = 0; i < N-1; i++) {
			left = s.charAt(i-w);
			right = s.charAt(i+w+1);
			
			if(left == right) {
				res = Math.max(res, w+1);
				
				if(i-w == 0 || i+w+1 == N-1) {
					w = 0;
					continue;
				}
				
				w++;
				i--;
			} else w = 0;
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		Solution12904 sol = new Solution12904();
//		System.out.println(sol.solution("abcdcba"));
//		System.out.println(sol.solution("abacde"));
//		System.out.println(sol.solution("abc"));
//		System.out.println(sol.solution("abccba"));
		System.out.println(sol.solution("ccccccc"));
	}
}
