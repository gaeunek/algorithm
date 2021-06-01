package backtracking;

import java.util.*;
import java.io.*;

public class Main14888 {
	static int N;
	static int[] nums, opers;
	static int[] res;
	static int max, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int index = 0;
		opers = new int[N - 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			int end = Integer.parseInt(st.nextToken());
			if (end == 0)
				continue;

			for (int j = 0; j < end; j++) {
				opers[index++] = i;
			}
		}

		res = new int[N - 1];
		max = -1000000000;
		min = 1000000000;

		permutation(new boolean[N - 1], 0);

		System.out.println(max);
		System.out.println(min);
	}

	public static void permutation(boolean[] used, int index) {
		if (index == used.length) {
			int sum = nums[0];
			for (int i = 0; i < N - 1; i++) {
				sum = cal(sum, nums[i + 1], res[i]);
			}

			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}

		for (int i = 0; i < used.length; i++) {
			if (!used[i]) {
				used[i] = true;
				res[index] = opers[i];
				permutation(used, index + 1);
				res[index] = -1;
				used[i] = false;
			}
		}
	}

	public static int cal(int a, int b, int oper) {
		switch (oper) {
		case 0:
			return a + b;
		case 1:
			return a - b;
		case 2:
			return a * b;
		default:
			if (a < 0) {
				a *= -1;
				return (a / b) * -1;
			}
			return a / b;
		}
	}
}
