package tree;

import java.io.*;
import java.util.*;

public class Main2263_2 {
	static int[] inorder, postorder, preorder, temp;
	static int n, index;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		inorder = new int[n + 1];
		temp = new int[n + 1];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 1; i <= n; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
			temp[inorder[i]] = i;
		}

		postorder = new int[n + 1];

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 1; i <= n; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}

		preorder = new int[n + 1];

		index = 1;

		getPreOrder(1, n, 1, n);

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append(preorder[i]).append(" ");
		}

		System.out.println(sb.toString());
	}

	public static void getPreOrder(int is, int ie, int ps, int pe) {
		if (is <= ie && ps <= pe) {
			preorder[index++] = postorder[pe];

			int i = temp[postorder[pe]];

			// 왼쪽
			getPreOrder(is, i - 1, ps, ps + (i - 1) - is);

			// 오른쪽
			getPreOrder(i + 1, ie, ps + i - is, pe - 1);
		}
	}
}
