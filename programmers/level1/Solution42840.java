package level1;

import java.util.*;
import java.util.Map.Entry;
//모의고사
public class Solution42840 {
	static int result;

	public int[] solution(int[] answers) {
		int[] one = { 1, 2, 3, 4, 5 };
		int[] two = { 2, 1, 2, 3, 2, 4, 2, 5 };
		int[] three = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		result = 0;
		dfs(answers, one, 0, 0);
		map.put(1, result);
		result = 0;
		dfs(answers, two, 0, 0);
		map.put(2, result);
		result = 0;
		dfs(answers, three, 0, 0);
		map.put(3, result);

		List<Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<Integer, Integer>>() {

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});

		int max = 0;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				max = list.get(i).getValue();
				result.add(list.get(i).getKey());
				continue;
			}

			if (max == list.get(i).getValue())
				result.add(list.get(i).getKey());
			else
				break;
		}

		int[] answer = new int[result.size()];
		
		for (int i = 0; i < answer.length; i++)
			answer[i] = result.get(i);
		
		return answer;
	}

	static void dfs(int[] answers, int[] nums, int cnt, int i) {
		if (i == answers.length)
			return;

		if (cnt == nums.length)
			cnt = 0;

		if (answers[i] == nums[cnt])
			result++;

		dfs(answers, nums, cnt + 1, i + 1);
	}

	public static void main(String[] args) {
		Solution42840 sol = new Solution42840();
		int[] answers = { 1, 2, 3, 4, 5 };
		System.out.println(Arrays.toString(sol.solution(answers)));
	}
}
