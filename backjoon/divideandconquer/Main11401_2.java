package divideandconquer;

import java.util.*;

public class Main11401_2 {
	static final long P = 1000000007;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long N = sc.nextLong();
		long K = sc.nextLong();

		long A = factorial(N);
		long B = factorial(K) * factorial(N - K) % P;

		System.out.println(A * pow(B, P - 2) % P);
	}

	public static long factorial(long n) {
		long res = 1L;

		while (n > 1) {
//			res *= (n % P); //이 방법은 자료형을 넘기는 값이 존재할 수 있으므로 틀린 방법이다.
			res = (res * n) % P;
			n--;
		}

		return res;
	}

	public static long pow(long B, long exponent) {
		if (exponent == 1)
			return B % P;

		long temp = pow(B, exponent / 2);

		if (exponent % 2 == 1)
			return (temp * temp % P) * B % P;

		return temp * temp % P;
	}
}
