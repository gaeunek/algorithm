package baekjoon;

import java.util.*;
//수들의 합 2
public class Main2003 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int st = 0, end = 0, sum = 0, count = 0;

		while (true) {
			if (sum >= M) {
				sum -= arr[st++];
			} else if (end == N) {
				break;
			} else if (sum < M) {
				sum += arr[end++];
			}

			if (sum == M) {
				count++;
			}
		}

		System.out.println(count);
	}
}
