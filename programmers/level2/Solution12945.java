package level2;

//피보나치 수 아무리 숫자가 100000이하 int 범위 안에 들어와도 피보나치수같은 경우엔 연속된 숫자의 합으로 늘어나기 때문에
//int범위를 넘어가는 결과값이 나올 수 있다.
class Solution12945 {
	static long[] memo;
	public int solution(int n) {
		if(n <= 1) return n;
		
		//방법 1
//		int f1 = 0, f2 = 1, sum = 0;
//		for (int i = 2; i <= n; i++) {
//			sum = f1 + f2;
//			sum %= 1234567;
//			f1 = f2;
//			f2 = sum;
//		}
//		return sum;
		
		//방법 2
//		long[] dp = new long[n+1];
//		dp[1] = 1;
//		for (int i = 2; i <= n; i++) dp[i] = (dp[i-1] + dp[i-2])%1234567L;
//		return (int)dp[n];
		
		//방법 3
		memo = new long[n+1];
		return fibo(n);
	}
	
	static int fibo(int n) {
		if(n <= 1) return 1;
		memo[n-1] = fibo(n-1);
		return (int)((memo[n-1] + memo[n-2])%1234567L);
	}
	
	public static void main(String[] args) {
		Solution12945 sol = new Solution12945();
		System.out.println(sol.solution(5));
	}
}
