package baekjoon;
// 범위를 잘 모르겠을 때는 가장 최댓값이 입력될 때를 가정해서 출력해본다.
//이거로 int형인지 long형인지 알 수 있지만 프로그래머스는 이미 알려준다.
//이친수
import java.util.*;
public class Main2193 {
	static long[] memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		memo = new long[N+1];
		System.out.println(recur(N));
	}
	
	static long recur(int n) {
		if(n <= 1) return n;
		if(memo[n] != 0) return memo[n];
		memo[n] = recur(n-1) + recur(n-2);
		return memo[n];
	}
}
