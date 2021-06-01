package segment;

import java.util.*;
import java.io.*;

public class Main2042 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long[] nums = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int segSize = (int) Math.pow(2, h + 1);

		long[] tree = new long[segSize];

		init(nums, tree, 1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) { // update
				long diff = c - nums[b];
				nums[b] = c;
				
				// 세그먼트 트리에 맞춰 index값을 변경해줄 필요가 없는 이유가
				// 어차피 start와 end는 최소 1, 최대 segSize이기 때문이다.
				update(nums, tree, 1, 1, N, b, diff);
			} else if(a == 2) {
				sb.append(sum(tree, 1, 1, N, b, (int)c));
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

	public static long sum(long[] tree, int node, int start, int end, int left, int right) {
		if (left > end || right < start)
			return 0;

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return sum(tree, node * 2, start, mid, left, right) + sum(tree, node * 2 + 1, mid + 1, end, left, right);
	}

	public static void update(long[] nums, long[] tree, int node, int start, int end, int index, long diff) {
		if (index < start || index > end)
			return;

		tree[node] += diff;

		if (start != end) { // 리프노드가 아니라면 자식 노드 값도 바꿔줘야하니
			int mid = (start + end) / 2;
			update(nums, tree, node * 2, start, mid, index, diff);
			update(nums, tree, node * 2 + 1, mid + 1, end, index, diff);
		} 
	}

	public static long init(long[] nums, long[] tree, int node, int start, int end) {
		if (start == end)
			return tree[node] = nums[start];

		int mid = (start + end) / 2;

		return tree[node] = init(nums, tree, node * 2, start, mid) + init(nums, tree, node * 2 + 1, mid + 1, end);
	}
}
