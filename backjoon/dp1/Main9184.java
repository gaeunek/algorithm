package dp1;

import java.util.*;
import java.io.*;

public class Main9184 {
	static int[][][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		memo = new int[51][51][51]; // + 50씩 해주기

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (a == -1 && b == -1 && c == -1)
				break;

			bw.write("w("+a+", "+b+", "+c+") = "+w(a, b, c));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		
	}

	public static int w(int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0)
			return 1;
		
		if (memo[a][b][c] != 0) {
			return memo[a][b][c];
		}

		if (a > 20 || b > 20 || c > 20) {
			return memo[20][20][20] = w(20, 20, 20);
		} else if (a < b && b < c) {
			return memo[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
		} else {
			return memo[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
		}
	}
}
