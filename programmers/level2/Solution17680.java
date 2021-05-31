package level2;
//캐시
import java.util.*;
class Solution17680 {
	public int solution(int cacheSize, String[] cities) {
		int answer = 0;
		
		if(cacheSize == 0) return cities.length * 5;
		
		Queue<String> queue = new LinkedList<String>();
		
		String s;
		for (int i = 0; i < cities.length; i++) {
			String city = cities[i].toLowerCase();
			
			if(!queue.contains(city)) {
				if(queue.size() >= cacheSize) queue.poll();
				queue.add(city);
				answer += 5;
			} else {
				List<String> list = new ArrayList<>(queue);
				list.remove(list.indexOf(city));
				queue.clear();
				queue = new LinkedList<String>(list);
				queue.add(city);
				answer++;
			}
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		Solution17680 sol = new Solution17680();
		System.out.println(sol.solution(3, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
		System.out.println(sol.solution(3, new String[] {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
		System.out.println(sol.solution(5, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
	}
}
