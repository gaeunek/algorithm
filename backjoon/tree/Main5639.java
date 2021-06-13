package tree;

import java.util.*;
import java.io.*;

public class Main5639 {
	static List<Integer> preorder;
	static int[] postorder;
	static int index;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		preorder = new ArrayList<>();

		String str = "";

		// NullPointer 발생. EOF(End Of File, 데이터가 없을 때 까지 입력을 받는다)
		while ((str = br.readLine()) != null && str.length() > 0) {
			preorder.add(Integer.parseInt(str));
        }

		postorder = new int[preorder.size()];

		index = preorder.size() - 1;
		getPostOrder(0, preorder.size() - 1);

		StringBuilder sb = new StringBuilder();

		for (int num : postorder) {
			sb.append(num).append("\n");
		}

		System.out.println(sb.toString());
	}

	public static void getPostOrder(int start, int end) {
		if (start <= end) {
			postorder[index--] = preorder.get(start);

			int i, count = 0;
			for (i = start + 1; i <= end; i++) {
				if (preorder.get(start) <= preorder.get(i))
					break;

				count++;
			}

			getPostOrder(start + count + 1, end);
			getPostOrder(start + 1, start + count);// 왼쪽
		}
	}
}
