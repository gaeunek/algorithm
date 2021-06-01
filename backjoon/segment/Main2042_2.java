package segment;

import java.util.*;
import java.io.*;

public class Main2042_2 {
	static int N, M, K;
	static long[] nums, tree;

	private static String input = "5 2 2\r\n" + "1\r\n" + "2\r\n" + "3\r\n" + "4\r\n" + "5\r\n" + "1 3 6\r\n"
			+ "2 2 5\r\n" + "1 5 2\r\n" + "2 3 5";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
//		while((line=br.readLine())!=null) {
//			System.out.println(line);
//		}
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
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken()); // 1: b를 c로 바꿈, 2: b부터 c까지의 합
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) { // b를 c로 바꾼다.
				long diff = c - nums[b];
				nums[b] = c;

				update(b, diff, 1, 1, N);
			} else if(a == 2){
				sb.append(sum(b, (int) c, 1, 1, N));
				sb.append("\n");
			}
		}

		System.out.println(sb.toString());
	}

	public static long sum(int left, int right, int node, int start, int end) {
		if (left > end || right < start)
			return 0;
		
		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;
		return sum(left, right, node * 2, start, mid) + sum(left, right, node * 2 + 1, mid + 1, end);
	}

	public static void update(int index, long diff, int node, int start, int end) {
		if (index < start || index > end)
			return;

		tree[node] += diff;

		if (start != end) {
			int mid = (start + end) / 2;
			update(index, diff, node * 2, start, mid);
			update(index, diff, node * 2 + 1, mid + 1, end);
		}
	}

	public static long init(int node, int start, int end) {
		if (start == end)
			return tree[node] = nums[start];

		int mid = (start + end) / 2;

		return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
	}
}
