package segment;

import java.util.*;
import java.io.*;

public class Main11659 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = (int) Math.pow(2, h + 1);

		int[] nums = new int[N + 1];

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int[] tree = new int[size];

		tree[1] = init(nums, tree, 1, 1, N);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			if (left == right)
				System.out.println(nums[left]);

			else
				System.out.println(sum(tree, 1, 1, N, left, right));
		}
	}

	public static int sum(int[] tree, int node, int start, int end, int left, int right) {
		if (left > end || right < start)
			return 0;

		if (left <= start && right >= end)
			return tree[node];

		int mid = (start + end) / 2;
		return sum(tree, node * 2, start, mid, left, right) + sum(tree, node * 2 + 1, mid + 1, end, left, right);
	}

	public static int init(int[] nums, int[] tree, int node, int start, int end) {
		if (start == end) { // 자식이 없는 리프노드
			return tree[node] = nums[start];
		}

		int mid = (start + end) / 2;

		return tree[node] = init(nums, tree, node * 2, start, mid) + init(nums, tree, node * 2 + 1, mid + 1, end);
	}
}
