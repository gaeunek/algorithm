package level2;

import java.util.*;
class Solution1845 {
	public int solution(int[] nums) {
		int answer = 0;

		Set<Integer> set = new HashSet<>();
		
		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}
		
		System.out.println(set);
		return answer;
	}
}
