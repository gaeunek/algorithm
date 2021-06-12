package tree;

import java.util.*;
import java.io.*;

public class Main2263 {
	static int[] inorder, postorder, preorder;
	static int n, index;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		inorder = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < n; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}

		postorder = new int[n];

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < n; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}

		preorder = new int[n];

		index = 0;

		getPreOrder(0, n - 1, 0, n - 1);
		
		StringBuilder sb = new StringBuilder();
		for(int num :preorder) {
			sb.append(num).append(" ");
		}
		
		System.out.println(sb.toString());
	}

	public static void getPreOrder(int is, int ie, int ps, int pe) {
		if (is <= ie && ps <= pe) {
			preorder[index++] = postorder[pe];
			
			int i;
			for (i = is; i <= ie; i++) {
				if (inorder[i] == postorder[pe]) {
					break;
				}
			}

			// 왼쪽
			getPreOrder(is, i - 1, ps, ps + (i - 1) - is);

			// 오른쪽
			getPreOrder(i + 1, ie, ps + i - is, pe - 1);
		}
	}
}
