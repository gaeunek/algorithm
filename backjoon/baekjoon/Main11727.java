package baekjoon;

import java.util.Scanner;
//2×n 타일링2
public class Main11727 {
	static int[] memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		memo = new int[1001];
		memo[0] = 1;
		System.out.println(fibo(n) % 10007);
	}
	
	static int fibo(int n) {
		if(n <= 1) return n;
		memo[n-1] = fibo(n-1);
		return memo[n-1] % 10007 + memo[n-2] % 10007 + memo[n-2] % 10007;
	}
}
