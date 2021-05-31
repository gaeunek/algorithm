package kakao;

import java.util.*;

// two-pointer 알고리즘
class Solution67258 {
	public int[] solution(String[] gems) {
		int[] answer = new int[2];

		int st = 0, end = 0;
		Set<String> set = new HashSet<>();
		for (int i = 0; i < gems.length; i++) {
			set.add(gems[i]);
		}

		Map<String, Integer> map = new HashMap<>();

		int min = Integer.MAX_VALUE;

		while (st <= end) {

			if (map.size() == set.size()) {
				if (min > end - st) {
					answer[0] = st + 1;
					answer[1] = end;
					min = end - st;
				}

				map.replace(gems[st], map.get(gems[st]) - 1);
				if (map.get(gems[st]) == 0)
					map.remove(gems[st]);
				st++;
			} else if (end == gems.length) {
				break;
			} else if (map.size() < set.size()) {
				map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
				end++;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution67258 sol = new Solution67258();
//		System.out.println(Arrays.toString(
//				sol.solution(new String[] { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" })));
//		System.out.println(Arrays.toString(sol.solution(new String[] { "AA", "AB", "AC", "AA", "AC" })));
		System.out.println(Arrays.toString(sol.solution(new String[] { "ZZZ", "YYY", "NNNN", "YYY", "BBB" })));
	}
}
