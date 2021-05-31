package level2;

import java.util.*;

//가장 큰 수
public class Solution42746 {

	public String solution(int[] numbers) {
		String answer = "";
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i : numbers)
			list.add(i);

		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				String tmp1 = String.valueOf(o1), tmp2 = String.valueOf(o2);
				if (Integer.parseInt(tmp1 + tmp2) > Integer.parseInt(tmp2 + tmp1))
					return -1;
				else if (Integer.parseInt(tmp1 + tmp2) < Integer.parseInt(tmp2 + tmp1))
					return 1;
				else
					return 0;
			}
		});

		int num = 0;
		for (int i : list) {
			num += i;
			answer += i;
		}
		
		if (num == 0)
			return "0";
		
		return answer;
	}
}
