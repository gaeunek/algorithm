package level3;
//이중우선순위큐
import java.util.*;

public class Solution42628 {
	public int[] solution(String[] operations) {
		int[] answer = new int[2];
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < operations.length; i++) {
			String[] tmp = operations[i].split(" ");
			if (tmp[0].equals("I"))
				list.add(Integer.parseInt(tmp[1]));
			else if (!list.isEmpty() && tmp[0].equals("D")) {
				if (Integer.parseInt(tmp[1]) >= 0)
					list.remove(Collections.max(list));
				else
					list.remove(Collections.min(list));
			}
		}

		if (list.isEmpty())
			answer[0] = answer[1] = 0;
		else {
			answer[0] = Collections.max(list);
			answer[1] = Collections.min(list);
		}

		return answer;
	}

	public static void main(String[] args) {
		String[] operations = { "I 1", "I 2", "I 3", "I 4", "I 5", "I 6", "I 7", "I 8", "I 9", "I 10", "D 1", "D -1",
				"D 1", "D -1", "I 1", "I 2", "I 3", "I 4", "I 5", "I 6", "I 7", "I 8", "I 9", "I 10", "D 1", "D -1",
				"D 1", "D -1" };
		Solution42628 sol = new Solution42628();
		System.out.println(Arrays.toString(sol.solution(operations)));
	}
}
