package segment;

import java.util.*;
import java.io.*;

public class Main11505 {
	static int N, M, K;
	static long[] nums, tree;
	static final long MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = (int) Math.pow(2, h + 1);

		tree = new long[size];

		init(1, 1, N);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) {
				update(1, 1, N, b, c);
				nums[b] = c;
			} else {
				sb.append(mul(1, 1, N, b, (int) c));
				sb.append("\n");
			}
		}

		System.out.println(sb.toString());
	}

	public static long mul(int node, int start, int end, int left, int right) {
		if (left > end || right < start)
			return 1;

		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;
		return mul(node * 2, start, mid, left, right) * mul(node * 2 + 1, mid + 1, end, left, right) % MOD;
	}

	/* 리프노드에서 루트로 올라가면서 갱신한다. */
	public static long update(int node, int start, int end, int index, long c) {
		if (index < start || end < index)
			return tree[node];
		
		if(start == end)
			return tree[node] = c;

		int mid = (start + end) / 2;
		return tree[node] = update(node * 2, start, mid, index, c) * update(node * 2 + 1, mid + 1, end, index, c) % MOD;
	}

	public static long init(int node, int start, int end) {
		if (start == end) // 리프노드
			return tree[node] = nums[start];

		int mid = (start + end) / 2;

		return tree[node] = init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end) % MOD;
	}
}
