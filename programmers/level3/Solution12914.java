package level3;
// 멀리 뛰기
class Solution12914 {
	static long[] memo;
	public long solution(int n) {
		memo = new long[n+1];
		memo[0] = 1;
		return func(n) % 1234567;
	}
	
	static long func(int n) {
		if(n <= 1) return n;
		memo[n-1] = func(n-1);
		return memo[n-1] % 1234567 + memo[n-2] % 1234567;
	}
	
	public static void main(String[] args) {
		Solution12914 sol = new Solution12914();
		System.out.println(sol.solution(4));
	}
}
