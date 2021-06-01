package segment;

import java.util.*;
import java.io.*;

public class Main2357 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] nums = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = (int) Math.pow(2, h + 1);

		int[] maxTree = new int[size];
		int[] minTree = new int[size];

		init(minTree, nums, 1, 1, N, 1);
		init(maxTree, nums, 1, 1, N, 2);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(find(minTree, nums, 1, 1, N, a, b, 1));
			sb.append(" ");
			sb.append(find(maxTree, nums, 1, 1, N, a, b, 2));
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	public static int find(int[] tree, int[] nums, int node, int start, int end, int left, int right, int flag) {
		if (left > end || right < start)
			return flag == 1 ? Integer.MAX_VALUE : 0;

		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;

		if (flag == 1)
			return Math.min(find(tree, nums, node * 2, start, mid, left, right, flag),
					find(tree, nums, node * 2 + 1, mid + 1, end, left, right, flag));
		else
			return Math.max(find(tree, nums, node * 2, start, mid, left, right, flag),
					find(tree, nums, node * 2 + 1, mid + 1, end, left, right, flag));
	}

	public static int init(int[] tree, int[] nums, int node, int start, int end, int flag) {
		if (start == end)
			return tree[node] = nums[start];

		int mid = (start + end) / 2;

		if (flag == 1)
			return tree[node] = Math.min(init(tree, nums, node * 2, start, mid, flag),
					init(tree, nums, node * 2 + 1, mid + 1, end, flag));
		else
			return tree[node] = Math.max(init(tree, nums, node * 2, start, mid, flag),
					init(tree, nums, node * 2 + 1, mid + 1, end, flag));
	}
}