package level2;

import java.util.*;

public class Solution {
	static HashMap<Integer, Integer> map;
	static boolean[] used;
	static int[] arr;

	public int solution(String numbers) {
		arr = new int[numbers.length()];
		used = new boolean[numbers.length()];
		map = new HashMap<Integer, Integer>();

		for (int i = 0; i < arr.length; i++)
			arr[i] = numbers.charAt(i) - '0';

		combination("");
		System.out.println(map);
		return map.size();
	}

	static void combination(String num) {
		if (!num.equals("") || !num.isEmpty()) {
			int tmp = Integer.parseInt(num);
			boolean flag = false;

			if (tmp != 0 && tmp != 1) {
				for (int i = 2; i < tmp - 1; i++) {
					if (tmp % i == 0) {
						flag = true;
						break;
					}
				}

				if (!flag)
					map.put(tmp, map.getOrDefault(tmp, 0) + 1);
			}
		}

		for (int i = 0; i < used.length; i++) {
			if (!used[i]) {
				used[i] = true;
				combination(num + arr[i]);
				used[i] = false;
			}
		}
	}
}
