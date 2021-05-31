package kakao;

import java.util.*;
import java.util.Map.Entry;

// 모든 조합
class Solution72411_2 {
	static Map<String, Integer> map;

	public String[] solution(String[] orders, int[] course) {
		StringBuilder sb;
		map = new HashMap<>();
		// 입력값 정렬
		for (int i = 0; i < orders.length; i++) {
			char[] order = orders[i].toCharArray();
			Arrays.sort(order);

			comb(new boolean[order.length], order, "", 0);
		}

		int[] count = new int[11]; // course 배열의 각 원소는 2 이상 10이하인 자연수 이므로
		ArrayList<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

		for (Entry<String, Integer> e : list) {
			int len = e.getKey().length();
			count[len] = Math.max(count[len], e.getValue());
		}

		boolean[] check = new boolean[11];
		for (int i = 0; i < course.length; i++) {
			check[course[i]] = true;
		}

		ArrayList<String> result = new ArrayList<>();
		for (Entry<String, Integer> e : list) {
			int len = e.getKey().length();
			if (check[len] && count[len] >= 2 && count[len] == e.getValue())
				result.add(e.getKey());
		}

		String[] answer = new String[result.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = result.get(i);
		}
		
		Arrays.sort(answer);
		return answer;
	}

	public static void comb(boolean[] used, char[] order, String result, int index) {
		if (result.length() >= 2) {
			map.put(result, map.getOrDefault(result, 0) + 1);

			if (index == used.length)
				return;
		}

		for (int i = index; i < used.length; i++) {
			if (!used[i]) {
				used[i] = true;
				comb(used, order, result + order[i], i + 1);
				used[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Solution72411_2 sol = new Solution72411_2();
		System.out.println(Arrays.toString(
				sol.solution(new String[] { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" }, new int[] { 2, 3, 4 })));
//		System.out
//				.println(Arrays.toString(sol.solution(new String[] { "ABCD", "ABCD", "ABCD" }, new int[] { 2, 3, 4 })));
//		System.out.println(Arrays.toString(sol.solution(new String[] { "XYZ", "XWY", "WXA" }, new int[] { 2, 3, 4 })));
	}
}
