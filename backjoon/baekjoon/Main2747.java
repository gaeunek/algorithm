package baekjoon;

import java.util.*;

//피보나치 수
public class Main2747 {
	static int[] memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		memo = new int[N+1];
		System.out.println(fibo(N));
	}
	
	static int fibo(int n) {
		if(n <= 1) return 1;
		memo[n-1] = fibo(n-1);
		return memo[n-1] + memo[n-2];
	}
}
