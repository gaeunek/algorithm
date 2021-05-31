package kakao;

import java.util.*;
import java.util.Map.Entry;

class Solution72411 {
	static Map<String, Integer> map;

	public String[] solution(String[] orders, int[] course) {
		map = new HashMap<>();
		StringBuilder sb;

		// 입력값 orders 정렬
		for (int i = 0; i < orders.length; i++) {
			char[] order = orders[i].toCharArray();
			sb = new StringBuilder();
			Arrays.sort(order);

			for (int j = 0; j < order.length; j++) {
				sb.append(order[j]);
			}

			orders[i] = sb.toString();
		}

		for (int i = 0; i < orders.length - 1; i++) {
			String order = orders[i];

			for (int j = i + 1; j < orders.length; j++) {
				sb = new StringBuilder();

				for (int k = 0; k < order.length(); k++) {
					if (orders[j].contains(String.valueOf(order.charAt(k)))) {
						sb.append(order.charAt(k));
					}
				}

				comb(new boolean[sb.length()], sb.toString(), 0, "");
			}
		}

		ArrayList<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (o1.getKey().length() == o2.getKey().length())
					return o2.getValue() - o1.getValue();
				return o1.getKey().length() - o2.getKey().length();
			}
		});

		int index = 0;
		int max = 0;
		ArrayList<String> answer_list = new ArrayList<>();
		System.out.println(list);

		for (Entry<String, Integer> e : list) {
			if (index == course.length)
				break;

			if (e.getKey().length() == course[index]) {
				if (max == 0 || max == e.getValue()) {
					max = e.getValue();
					answer_list.add(e.getKey());
				} else {
					continue;
				}
			} else {
				index++;
				max = e.getValue();
				answer_list.add(e.getKey());
			}
		}

		String[] answer = new String[answer_list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = answer_list.get(i);
		}

		Arrays.sort(answer);
		return answer;
	}

	public static void comb(boolean[] used, String s, int index, String result) {
		if (result.length() >= 2) {
			map.put(result, map.getOrDefault(result, 0) + 1);
		}

		for (int i = index; i < used.length; i++) {
			if (!used[i]) {
				used[i] = true;
				comb(used, s, i + 1, result + s.charAt(i));
				used[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Solution72411 sol = new Solution72411();
//		System.out.println(
//				sol.solution(new String[] { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" }, new int[] { 2, 3, 4 }));
//		System.out
//				.println(Arrays.toString(sol.solution(new String[] { "ABCD", "ABCD", "ABCD" }, new int[] { 2, 3, 4 })));
		System.out.println(Arrays.toString(sol.solution(new String[] { "XYZ", "XWY", "WXA" }, new int[] { 2, 3, 4 })));
	}
}
