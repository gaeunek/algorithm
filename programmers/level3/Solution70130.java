package level3;

import java.util.*;
//스타 수열
class Solution70130 {
	public int solution(int[] a) {
		int answer = 0;
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i : a) map.put(i, map.getOrDefault(i, 0)+1);
		
		for (int key : map.keySet()) {
			if(map.get(key) * 2 <= answer) continue;
			
			int count = 0;
			for (int i = 0; i < a.length-1; i++) {
				if((a[i] == key || a[i+1] == key) && (a[i] != a[i+1])) {
					count += 2;
					i++;
				}
			}
			
			answer = Math.max(answer, count);
		}
			
		return answer;
	}
}
