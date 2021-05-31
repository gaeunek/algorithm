package jungol;

import java.util.*;
import java.io.*;
//정올 탑인데 정올에선 통과 X(시간이 1초) 백준 탑은 성공(시간이 3초)
public class Main1809 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] tops = new int[N][2]; //0번 인덱스엔 탑의 높이를, 1번 인덱스엔 인덱스 번호를

		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			tops[i] = new int[] {Integer.parseInt(input[i]), i};
		}

		Stack<int[]> stack = new Stack<>();
		int[] result = new int[N];
		for (int i = 0; i < N; i++) {
			int[] t = tops[i];

			while (!stack.isEmpty() && stack.peek()[0] <= t[0]) {
				stack.pop();
			}

			result[i] = !stack.isEmpty() ? stack.peek()[1] + 1 : 0;
			stack.add(t);
		}

		for (int i : result) {
			System.out.print(i + " ");
		}
	}	
}