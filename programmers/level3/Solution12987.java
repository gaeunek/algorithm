package level3;

import java.util.*;
//숫자 게임
public class Solution12987 {
	public int solution(int[] A, int[] B) {
		int answer = 0;
		Arrays.sort(A);
		Arrays.sort(B);

		int index = 0;
		for (int i = 0; i < B.length; i++) {
			if(B[i] <= A[index]) {
				continue;
			} else {
				answer++;
				index++;
			}
		}
		
		return answer;
	}

	public static void main(String[] args) {
		Solution12987 sol = new Solution12987();
		System.out.println(sol.solution(new int[] { 5, 1, 3, 7 }, new int[] { 2, 2, 6, 8 }));
	}
}
