package level2;

import java.util.*;
//구명보트
class Solution42885 {
	public int solution(int[] people, int limit) {
		int answer = 0;
		
		Arrays.sort(people);
		int st = 0;
		for (int i = people.length-1; st <= i; i--) {
			if(people[i] + people[st] <= limit) st++;
			answer++;
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		Solution42885 sol = new Solution42885();
		int[] people = {1,1,4,4,5};
		sol.solution(people, 8);
	}
}
