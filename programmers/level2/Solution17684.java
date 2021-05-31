package level2;

import java.util.*;
//[3차]압축
class Solution17684 {
	public int[] solution(String msg) {
		Map<String, Integer> map = new HashMap<>();
		for (char i = 'A', num = '1'; i <= 'Z'; i++, num++)
			map.put(String.valueOf(i), num - '0');

		char[] tmp = msg.toCharArray();
		List<Integer> result = new ArrayList<Integer>();
		String now = "", next = "";
		for (int i = 0; i < tmp.length; i++) {
			now += tmp[i];
			
			if(i == tmp.length-1) {
				result.add(map.get(now));
				break;
			}
			
			for (int j = i+1; j < tmp.length; j++) {
				next = String.valueOf(tmp[j]);
				
				if(map.get(now+next) == null) {
					map.put(now+next, map.size()+1);
					result.add(map.get(now));
					now = "";
					break;
				}else break;
			}
		}
		
		//map sort
//		List<Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
//		Collections.sort(entries, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
		
		int[] answer = new int[result.size()];
		for (int i = 0; i < result.size(); i++) answer[i] = result.get(i);
		return answer;
	}

	public static void main(String[] args) {
		Solution17684 sol = new Solution17684();
		System.out.println(sol.solution("TOBEORNOTTOBEORTOBEORNOT"));
	}
}
