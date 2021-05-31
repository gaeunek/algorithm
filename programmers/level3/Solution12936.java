package level3;

import java.util.*;

class Solution12936 {
	public int[] solution(int n, long k) {
		int[] answer = new int[n];
		List<String> list = new ArrayList<>();
		long fact = 1;
		for (long i = 1; i <= n; i++) {
			fact *= i;
			list.add(String.valueOf(i));
		}
		
		k--;
		for (int i = 0; i < n; i++) {
			fact /= n-i;
			long value = k;
			if(k != 0) value = k/fact;
			answer[i] = Integer.parseInt(list.remove((int)value));
			k %= fact;
		}
		
		return answer;
	}

	public static void main(String[] args) {
		Solution12936 sol = new Solution12936();
		System.out.println(Arrays.toString(sol.solution(3, 5)));
	}
}
