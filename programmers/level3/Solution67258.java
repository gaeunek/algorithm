package level3;
// 보석 쇼핑
import java.util.*;

class Solution67258 {
	public int[] solution(String[] gems) {
		int[] answer = new int[2];
		
		Set<String> set = new HashSet<>();
		for (String str : gems) {
			set.add(str);
		}
		
		if(set.size() == 1) return new int[] {1, 1};
		
		Map<String, Integer> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		int size = 0;
		
		for (int i = 0; i < gems.length; i++) {
			if(!map.containsKey(gems[i])) {
				list.add(String.valueOf(i+1));
			} else {
				String str = String.valueOf(map.get(gems[i]));
				list.remove(str);
				list.add(String.valueOf(i+1));
			}
			
			map.put(gems[i], i+1);
			
			if(map.size() == set.size()) {
				int st = Integer.parseInt(list.get(0));
				int end = Integer.parseInt(list.get(list.size()-1));
				
				if(size == 0 || size > end - st) {
					size = end - st;
					answer[0] = st;
					answer[1] = end;
				}
			}
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		Solution67258 sol = new Solution67258();
		System.out.println(Arrays.toString(sol.solution(new String[] {"ZZZ", "YYY", "NNNN", "YYY", "BBB"})));
		System.out.println(Arrays.toString(sol.solution(new String[] {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
		System.out.println(Arrays.toString(sol.solution(new String[] {"DIA", "EM", "EM", "RUB", "DIA"}))); //[3, 5] [1, 4]
	}
}
