package level2;

import java.util.*;
class Solution42890 {
	public int solution(String[][] relation) {
		int answer = 0;

		Map<String, Integer>[] map = new HashMap[relation[0].length];
		
		for (int i = 0; i < map.length; i++) {
			map[i] = new HashMap<String, Integer>();
		}
		
		for (int i = 0; i < relation.length; i++) {
			for (int j = 0; j < relation[i].length; j++) {
				map[j].put(relation[i][j], map[j].getOrDefault(relation[i][j], 0)+1);
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			if(map[i].size() == relation.length) answer++;
		}
		
		return answer+1;
	}
}
