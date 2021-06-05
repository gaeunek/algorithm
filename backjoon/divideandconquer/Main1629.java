package divideandconquer;

import java.util.*;
import java.io.*;

public class Main1629 {
	static int c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		System.out.println(pow(a, b));
	}

	public static long pow(long a, long exponent) {
		if (exponent == 1)
			return a % c;

		long temp = pow(a, exponent / 2);

		if (exponent % 2 == 1)
			return (temp * temp % c) * a % c;

		return temp * temp % c;
	}
}
