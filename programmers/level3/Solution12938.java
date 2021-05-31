package level3;

import java.util.*;

//최고의 집합
class Solution12938 {
	public int[] solution(int n, int s) {
        int[] answer = {};
        
        int value = s / n;
        if(value == 0) return new int[] {-1};
        int remain = s % n;
        
        answer = new int[n];
        for (int i = 0; i < n; i++) {
			answer[i] = value;
		}

        for (int i = n-1, j = remain; i >= 0 && j > 0; i--, j--) {
        	answer[i] += 1;
		}
        
        return answer;
    }
	
	public static void main(String[] args) {
		Solution12938 sol = new Solution12938();
		System.out.println(Arrays.toString(sol.solution(3,7)));
	}
}
