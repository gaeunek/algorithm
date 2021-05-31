package level2;

import java.util.*;

//메뉴 리뉴얼
class Solution72411 {
	static Map<String, Integer> map;
	static ArrayList<String> list;

	public String[] solution(String[] orders, int[] course) {
		String[] answer = {};

		map = new HashMap<>();
		list = new ArrayList<>();
		
		for (int i = 0; i < orders.length; i++) {
			char[] order = orders[i].toCharArray();

			boolean[] used = new boolean[order.length];
			comb(order, used, 0);
		}

		Iterator<String> it = map.keySet().iterator();
		ArrayList<String> result = new ArrayList<>();

		int[] check = new int[11]; // 0 부터 10까지 여기서 0과 1은 사용 안함
		Arrays.fill(check, -1);
		for (int i = 0; i < course.length; i++) {
			check[course[i]]++;
		}
		
		while (it.hasNext()) {
			String key = it.next();
			int value = map.get(key);
			
			if (value >= 2 && check[key.length()] != -1) {
				result.add(key);
				check[key.length()] = Math.max(check[key.length()], value);
			}
		}

		ArrayList<String> temp = new ArrayList<>();

		for (int i = 0; i < result.size(); i++) {
			String key = result.get(i);
			int value = map.get(key);

			if (check[key.length()] == value)
				temp.add(key);
		}

		answer = new String[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			answer[i] = temp.get(i);
		}

		Arrays.sort(answer);
		return answer;
	}

	static void comb(char[] order, boolean[] used, int index) {
		if (list.size() >= 2) {
			Collections.sort(list);

			StringBuilder tmp = new StringBuilder();
			for (int j = 0; j < list.size(); j++) {
				tmp.append(list.get(j));
			}

			String result = tmp.toString();
			map.put(result, map.getOrDefault(result, 0) + 1);
		}
		
		if (index == order.length)
			return;

		for (int i = index; i < used.length; i++) {
			if (!used[i]) {
				String s = String.valueOf(order[i]);
				used[i] = true;
				list.add(s);
				comb(order, used, i + 1);
				list.remove(s);
				used[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Solution72411 sol = new Solution72411();
		System.out.println(Arrays.toString(
				sol.solution(new String[] { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" }, new int[] { 2, 3, 4 })));
		System.out.println(Arrays.toString(sol.solution(new String[] {"AB", "ABC", "ABCD", "ABCDE"}, new int[] {2, 3, 4})));
	}
}